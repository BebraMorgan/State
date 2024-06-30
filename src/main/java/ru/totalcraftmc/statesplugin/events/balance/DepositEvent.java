package ru.totalcraftmc.statesplugin.events.balance;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.entities.Balance;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class DepositEvent extends AbstractEvent {
    private final Player player;
    private final Balance balance;
    private final String resource;
    private final String count;

    public DepositEvent(Player player, Balance balance, String resource, String count) {
        this.player = player;
        this.balance = balance;
        this.resource = resource;
        this.count = count;
    }
}
