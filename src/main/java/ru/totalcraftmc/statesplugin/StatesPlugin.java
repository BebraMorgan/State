package ru.totalcraftmc.statesplugin;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;
import org.dynmap.markers.MarkerSet;
import org.hibernate.jpa.HibernatePersistenceProvider;
import ru.totalcraftmc.statesplugin.commands.AllianceCommand;
import ru.totalcraftmc.statesplugin.commands.CityCommand;
import ru.totalcraftmc.statesplugin.commands.StateCommand;
import ru.totalcraftmc.statesplugin.commands.StatePlayerCommand;
import ru.totalcraftmc.statesplugin.entities.Grid;
import ru.totalcraftmc.statesplugin.listeners.MainListener;

import javax.persistence.EntityManager;
import java.util.Collections;

public final class StatesPlugin extends JavaPlugin implements Listener {

    @Getter
    private static final EntityManager entityManager = new HibernatePersistenceProvider().createEntityManagerFactory("persistence", Collections.emptyMap()).createEntityManager();
    @Getter
    private static StatesPlugin instance;
    @Getter
    private DynmapAPI dapi = null;
    @Getter
    private static Grid grid;

    @Override
    public void onEnable() {
        instance = this;
        dapi = loadDynmap();
        if (dapi == null) {
            Bukkit.getServer().getPluginManager().disablePlugin(instance);
        }
        gridInit();
        new MainListener();
        commandsInit();
    }

    @Override
    public void onDisable() {
        entityManager.close();
    }

    private DynmapAPI loadDynmap() {
        return (DynmapAPI) Bukkit.getServer().getPluginManager().getPlugin("dynmap");
    }

    public MarkerSet createMarkerSet() {
        return dapi.getMarkerAPI().createMarkerSet("statesplugin", "States", dapi.getMarkerAPI().getMarkerIcons(), false);
    }

    private void gridInit() {
        MarkerSet markerset = createMarkerSet();
        String world = "world";
        grid = new Grid(world, 32, 32, 255, markerset);
        grid.create();
        grid.draw();
    }

    private void commandsInit() {
        new CityCommand();
        new StatePlayerCommand();
        new StateCommand();
        new AllianceCommand();
    }

}
