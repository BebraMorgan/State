package ru.calvian.state.events.balance;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.calvian.state.entities.Balance;
import ru.calvian.state.events.AbstractEvent;

@Getter
public class WithdrawEvent extends AbstractEvent {
    private final Player player;
    private final Balance balance;
    private final String resource;
    private final String count;

    public WithdrawEvent(Player player, Balance balance, String resource, String count) {
        this.player = player;
        this.balance = balance;
        this.resource = resource;
        this.count = count;
    }
}