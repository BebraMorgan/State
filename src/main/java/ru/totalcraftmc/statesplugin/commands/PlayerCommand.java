package ru.totalcraftmc.statesplugin.commands;

import ru.totalcraftmc.statesplugin.commands.subcommands.common.Bank;
import ru.totalcraftmc.statesplugin.commands.subcommands.player.Info;
import ru.totalcraftmc.statesplugin.commands.utils.AbstractCommand;

import java.util.stream.Stream;

public class PlayerCommand extends AbstractCommand {
    public PlayerCommand() {
        super("player", Stream.of(new Bank(), new Info()));
    }
}
