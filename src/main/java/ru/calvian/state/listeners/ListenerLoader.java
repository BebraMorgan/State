package ru.calvian.state.listeners;

import org.bukkit.Bukkit;
import ru.calvian.state.States;

public class ListenerLoader {
    public ListenerLoader() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), States.instance);
        Bukkit.getPluginManager().registerEvents(new BalanceListener(), States.instance);
    }
}
