package ru.totalcraftmc.statesplugin.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class CityInviteEvent extends AbstractEvent {
    private final String name;
    private final Player player;

    public CityInviteEvent(String name, Player player) {
        this.name = name;
        this.player = player;
    }
}
