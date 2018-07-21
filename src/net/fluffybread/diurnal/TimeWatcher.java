package net.fluffybread.diurnal;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.oracle.javafx.jmx.json.JSONFactory;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import net.fluffybread.diurnal.Diurnal;
import org.bukkit.World;
import org.bukkit.Bukkit;
import com.fasterxml.jackson.core.JsonGenerator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class TimeWatcher implements Runnable {
    public FileConfiguration config;
    public JavaPlugin plugin;

    public TimeWatcher(FileConfiguration config, JavaPlugin plugin) {
        this.config = config;
        this.plugin = plugin;
    }


    /*public class WorldData {
        String name;
        int ticks;
        int weather;
        int weatherticks;
        public WorldData(String name, int ticks, int weather, int weatherticks) {
            this.name = name;
            this.ticks = ticks;
            this.weather = weather; //0 clear, 1 storm, 2 lightning
            this.weatherticks = weatherticks;
        }

    }*/

    @Override
    public void run() {
        List<World> worlds = Bukkit.getWorlds();
        /*JsonFactory factory = new JsonFactory();
        JsonGenerator generator;
        try {
            generator = factory.createGenerator(new File(config.getString("path")), JsonEncoding.UTF8);
        } catch (IOException exception) {
            // i dunno man, how the hell do i debug
            return;
        }
        //ArrayList<HashMap<String, Object>> arr = new ArrayList<HashMap<String,Object>>();
        try {
            generator.writeStartArray();
        } catch (IOException e1) {
            return;
        }*/
        String s = "[";
        for (int x = 0; x < worlds.size() ; x++ ) {
            World world = worlds.get(x);
            if (world.getEnvironment() == World.Environment.NORMAL) {
                String weather;
                if (world.isThundering()) {
                    weather = "thunder";
                } else if (world.hasStorm()) {
                    weather = "storm";
                } else {
                    weather = "clear";
                }
                s = s.concat("{\"name\": \""
                        + world.getName().replace("\"", "\\\"").replace("\\", "\\\\")
                        + "\",\"weather\": \""
                        + weather //treating this as safe
                        + "\",\"ticks\": "
                        + Long.toString(world.getTime())
                        + ",\"weatherticks\": "
                        + Long.toString(world.getWeatherDuration())
                        + ",\"timestamp\": "
                        + Long.toString(Instant.now().toEpochMilli())
                        + "},");
                //HashMap<String, Object> tmap = new HashMap<String, Object>();
                /*try {
                    generator.writeStartObject();
                    generator.writeStringField("name", world.getName());
                    generator.writeStringField("weather", weather);
                    generator.writeNumberField("ticks", world.getTime());
                    generator.writeNumberField("weatherticks", world.getWeatherDuration());
                    generator.writeEndObject();
                } catch (IOException exception_two) {
                    return;
                }*/
            }
        }
        String out = s.substring(0,s.length()-1) + "]";
        Path file = Paths.get(config.getString("path"));
        Charset charset = Charset.forName("UTF-8");
        try {
            BufferedWriter writer = Files.newBufferedWriter(file, charset);
            writer.write(out, 0, out.length());
            writer.close();
        } catch (IOException e) {
            plugin.getLogger().warning("Unable to write!");
        }
        /*try {
            generator.writeEndArray();
        } catch (IOException e2) {
            return;
        }*/
    }
}