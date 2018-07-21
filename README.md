# DIURNAL

A Minecraft plugin and webapp to post a Spigot Minecraft server's world time on a webpage. Works with all multiple-world plugins.

## Requirements

- Working web server
- Spigot server (confirmed working on 1.12.2 but will likely work in older/newer versions)
- The jar will need to be recompiled with a Windows filename to make the plugin work on Windows. Currently, it only supports \*nixes.

## Instructions

0. If you want the output file to be somewhere that isn't /tmp/diurnal, you will have to compile the jar yourself. Change config.yml in the source files, then follow the steps on the Spigot wiki to compile an "artifact" and output a jar file.
1. Install out/artifacts/diurnal\_jar/diurnal.jar (or your own recompiled version) into your Spigot server's plugins folder, and restart your Spigot server.
2. Make sure the file /tmp/diurnal exists, and is valid JSON.
3. Configure your web server to serve a directory.
4. Paste weatherwall/index.html into the directory, named whatever you want.
5. Symlink /tmp/diurnal to a file on your website's root named diurnal.json, which can be done using the ln command on \*nixes.
6. Replace the URL in index.html (fluffybread.net) with your URL. Make sure the URL retrieves the JSON file in /tmp/diurnal.
