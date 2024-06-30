package ru.totalcraftmc.statesplugin.listeners;

import org.bukkit.Bukkit;
import ru.totalcraftmc.statesplugin.StatesPlugin;

public class MainListener {
    StatesPlugin plugin = StatesPlugin.getInstance();

    public MainListener() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), plugin);

    }

}
