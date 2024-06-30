package ru.totalcraftmc.statesplugin.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class MayorSetEvent extends AbstractEvent {

    private final String name;
    private final Player player;

    public MayorSetEvent(Player player, String name) {
        this.name = name;
        this.player = player;
    }
}
