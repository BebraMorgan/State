package ru.calvian.state;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.hibernate.jpa.HibernatePersistenceProvider;
import ru.calvian.state.commands.CityCommand;
import ru.calvian.state.commands.StatePlayerCommand;
import ru.calvian.state.listeners.ListenerLoader;

import javax.persistence.EntityManager;
import java.util.Collections;

@Getter
public final class States extends JavaPlugin {
    public static States instance;
    public static EntityManager entityManager = new HibernatePersistenceProvider().createEntityManagerFactory("persistence", Collections.emptyMap()).createEntityManager();
    public boolean authenticate = false;

    @Override
    public void onEnable() {
        instance = this;
        new ListenerLoader();
        new StatePlayerCommand();
        new CityCommand();
    }

    @Override
    public void onDisable() {
        if (entityManager != null) {
            entityManager.close();
        }
    }
}
