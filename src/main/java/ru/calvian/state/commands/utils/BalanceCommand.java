package ru.calvian.state.commands.utils;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.calvian.state.entities.Balance;
import ru.calvian.state.events.balance.DepositEvent;
import ru.calvian.state.events.balance.WithdrawEvent;

@Getter
public class BalanceCommand {
    private final Player player;
    private final Balance balance;
    private final String[] args;

    public BalanceCommand(Player player, Balance balance, String[] args) {
        this.player = player;
        this.balance = balance;
        this.args = args;
    }

    public void bank() {
        if (args.length < 3) return;
        String resource = args[2];
        String count = args[3];
        switch (args[1]) {
            case "withdraw" -> Bukkit.getPluginManager().callEvent(new WithdrawEvent(player, balance, resource, count));
            case "deposit" -> Bukkit.getPluginManager().callEvent(new DepositEvent(player, balance, resource, count));
            default -> info();
        }
    }

    public void info() {
        String info = "Баланс:" + "\n" +
                "Железо: " + balance.getIron() + "\n" +
                "Алмазы: " + balance.getDiamonds() + "\n" +
                "Незерит: " + balance.getNetherite() + "\n";
        player.sendMessage(info);
    }

}
