package net.chamosmp.kuraac;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.chamosmp.kuraac.Commands.KuraMainCommands;
import net.chamosmp.kuraac.Util.ConsoleLogger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.Yaml;

public final class KuraAC extends JavaPlugin {




    @Override
    public void onEnable() {
        // Plugin startup logic
        String minecraftVersion = Bukkit.getServer().getMinecraftVersion();

        ConsoleLogger.console("<light_purple>KuraAC</light_purple> loaded on " + minecraftVersion );


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
