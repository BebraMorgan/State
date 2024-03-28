package ru.calvian.state.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.calvian.state.events.AbstractEvent;

@Getter
public class MayorSetEvent extends AbstractEvent {

    private final String name;
    private final Player player;

    public MayorSetEvent(Player player, String name) {
        this.name = name;
        this.player = player;
    }
}
