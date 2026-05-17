package net.chamosmp.kuraac.Util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

import java.util.function.Supplier;

public class ConsoleLogger {


    // -------------------------------------------------- //


    public static void console(String message) {
        MiniMessage mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize("<light_purple>KuraAC</light_purple>| " + message);
        Bukkit.getConsoleSender().sendMessage(parsed);
    }


    public static void info(String message) {
        Bukkit.getLogger().info(message);
    }


    public static void warn(String message) {
        Bukkit.getLogger().warning(message);
    }


    public static void error(String message) {
        Bukkit.getLogger().severe(message);
    }


    // -------------------------------------------------- //
}
