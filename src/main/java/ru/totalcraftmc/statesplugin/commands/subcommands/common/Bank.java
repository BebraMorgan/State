package ru.totalcraftmc.statesplugin.commands.subcommands.common;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.commands.utils.BalanceRequired;
import ru.totalcraftmc.statesplugin.commands.utils.CallsEvents;
import ru.totalcraftmc.statesplugin.commands.utils.PlayerRequired;
import ru.totalcraftmc.statesplugin.commands.utils.SubCommand;
import ru.totalcraftmc.statesplugin.entities.Balance;
import ru.totalcraftmc.statesplugin.events.balance.DepositEvent;
import ru.totalcraftmc.statesplugin.events.balance.WithdrawEvent;

public class Bank implements SubCommand, PlayerRequired, CallsEvents, BalanceRequired {
    private Player player;
    private Balance balance;

    @Override
    public void execute(String[] args) {
        if (args.length < 3) return;
        String resource = args[2];
        String count = args[3];
        switch (args[1]) {
            case "withdraw" -> callEvent(new WithdrawEvent(player, balance, resource, count));
            case "deposit" -> callEvent(new DepositEvent(player, balance, resource, count));
        }
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}
