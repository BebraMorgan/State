package ru.totalcraftmc.statesplugin.commands.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

public interface CallsEvents {
    default void callEvent(Event event) {
        Bukkit.getPluginManager().callEvent(event);
    }
}
