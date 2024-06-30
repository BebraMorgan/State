package ru.totalcraftmc.statesplugin.events.player;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class PlayerCityJoinEvent extends AbstractEvent {
    private final Player player;
    private final String name;

    public PlayerCityJoinEvent(String name, Player player) {
        this.player = player;
        this.name = name;
    }
}
