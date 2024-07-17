package ru.totalcraftmc.statesplugin.events.alliance;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class AllianceCreateEvent extends AbstractEvent {
    private final String name;
    private final Player player;

    public AllianceCreateEvent(String name, Player player) {
        this.name = name;
        this.player = player;
    }
}
