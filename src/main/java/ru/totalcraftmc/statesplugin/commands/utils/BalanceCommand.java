package ru.totalcraftmc.statesplugin.commands.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.entities.Balance;
import ru.totalcraftmc.statesplugin.events.balance.DepositEvent;
import ru.totalcraftmc.statesplugin.events.balance.WithdrawEvent;

public class BalanceCommand implements SubCommand, PlayerRequired, BalanceRequired {
    private Player player;
    private Balance balance;

    @Override
    public void execute(String[] args) {
        if (args.length < 3) return;
        String resource = args[2];
        String count = args[3];
        switch (args[1]) {
            case "withdraw" -> Bukkit.getPluginManager().callEvent(new WithdrawEvent(player, balance, resource, count));
            case "deposit" -> Bukkit.getPluginManager().callEvent(new DepositEvent(player, balance, resource, count));
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
