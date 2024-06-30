package ru.totalcraftmc.statesplugin.events.state;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class LeaderSetEvent extends AbstractEvent {
    private final String name;
    private final Player player;

    public LeaderSetEvent(String name, Player player) {
        this.name = name;
        this.player = player;
    }
}