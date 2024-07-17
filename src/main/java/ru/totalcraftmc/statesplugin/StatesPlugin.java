package ru.totalcraftmc.statesplugin;

import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.hibernate.jpa.HibernatePersistenceProvider;
import ru.totalcraftmc.statesplugin.commands.AllianceCommand;
import ru.totalcraftmc.statesplugin.commands.CityCommand;
import ru.totalcraftmc.statesplugin.commands.StateCommand;
import ru.totalcraftmc.statesplugin.commands.StatePlayerCommand;
import ru.totalcraftmc.statesplugin.listeners.MainListener;

import javax.persistence.EntityManager;
import java.util.Collections;

public final class StatesPlugin extends JavaPlugin implements Listener {

    @Getter
    private static final EntityManager entityManager = new HibernatePersistenceProvider()
            .createEntityManagerFactory("persistence", Collections.emptyMap()).createEntityManager();
    @Getter
    private static StatesPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        new MainListener();
        new CityCommand();
        new StatePlayerCommand();
        new StateCommand();
        new AllianceCommand();
    }

    @Override
    public void onDisable() {
        entityManager.close();
    }

}
