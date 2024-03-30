package ru.calvian.state.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.calvian.state.events.AbstractEvent;

@Getter
public class AssistantDismissEvent extends AbstractEvent {
    private final Player player;
    private final String name;

    public AssistantDismissEvent(Player player, String name) {
        this.name = name;
        this.player = player;
    }
}
