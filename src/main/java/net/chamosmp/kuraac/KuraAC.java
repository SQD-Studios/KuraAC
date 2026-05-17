package net.chamosmp.kuraac;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.chamosmp.kuraac.Commands.KuraMainCommands;
import net.chamosmp.kuraac.Util.ConsoleLogger;
import net.chamosmp.kuraac.Util.UpdateCheck;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public final class KuraAC extends JavaPlugin {







    @Override
    public void onEnable() {
        // Plugin startup logic
        String minecraftVersion = Bukkit.getServer().getMinecraftVersion();

        ConsoleLogger.console("Loaded on minecraft version " + minecraftVersion);
        //ConsoleLogger.console("KuraAC| ");
        String pluginVer = getPluginMeta().getVersion();
        try {
            new UpdateCheck().ModrinthVersionCheck(pluginVer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // Register commands
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            Commands commands = event.registrar();
            KuraMainCommands.register(commands);
        });

        // Configuration file prep

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
