package ru.totalcraftmc.statesplugin.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class CityKickEvent extends AbstractEvent {
    private final String name;
    private final Player player;

    public CityKickEvent(String name, Player player) {
        this.player = player;
        this.name = name;
    }
}
