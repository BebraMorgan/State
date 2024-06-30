package ru.totalcraftmc.statesplugin.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class CityRenameEvent extends AbstractEvent {
    private final Player player;
    private final String name;

    public CityRenameEvent(String name, Player player) {
        this.player = player;
        this.name = name;
    }
}
