package ru.totalcraftmc.statesplugin.commands;

import ru.totalcraftmc.statesplugin.commands.subcommands.common.Bank;
import ru.totalcraftmc.statesplugin.commands.subcommands.player.Info;
import ru.totalcraftmc.statesplugin.commands.utils.AbstractCommand;

public class PlayerCommand extends AbstractCommand {
    public PlayerCommand() {
        super("player");
        commands.put("info", new Info());
        commands.put("bank", new Bank());
    }
}
