package net.fluffybread.diurnal;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import net.fluffybread.diurnal.TimeWatcher;

public class Diurnal extends JavaPlugin {
    FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        //when plugin activated
        Server server = getServer();
        server.getPluginManager().registerEvents(new DiurnalListener(), this); //for weather
        server.getScheduler().scheduleSyncRepeatingTask(this, new TimeWatcher(config, this), 0, config.getInt("refreshrate"));
    }

    @Override
    public void onDisable() {
        //on server stop/all plugin disable?
    }
}
