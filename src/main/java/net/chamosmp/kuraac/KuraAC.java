package net.chamosmp.kuraac;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.chamosmp.kuraac.Commands.KuraMainCommands;
import org.bukkit.plugin.java.JavaPlugin;

public final class KuraAC extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            Commands commands = event.registrar();
            KuraMainCommands.register(commands);
        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logi
    }
}
