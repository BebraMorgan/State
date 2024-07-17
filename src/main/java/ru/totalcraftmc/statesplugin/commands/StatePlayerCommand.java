package ru.totalcraftmc.statesplugin.commands;

import ru.totalcraftmc.statesplugin.commands.subcommands.common.Bank;
import ru.totalcraftmc.statesplugin.commands.subcommands.player.Info;
import ru.totalcraftmc.statesplugin.commands.utils.AbstractCommand;

import java.util.stream.Stream;

public class StatePlayerCommand extends AbstractCommand {
    public StatePlayerCommand() {
        super("player", Stream.of(new Bank(), new Info()));
    }
}
