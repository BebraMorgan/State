package ru.calvian.state.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.calvian.state.events.AbstractEvent;

@Getter
public class CityRenameEvent extends AbstractEvent {
    private final Player player;
    private final String name;

    public CityRenameEvent(Player player, String name) {
        this.player = player;
        this.name = name;
    }
}
