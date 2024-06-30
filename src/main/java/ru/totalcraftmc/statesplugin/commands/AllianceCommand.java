package ru.totalcraftmc.statesplugin.commands;

import ru.totalcraftmc.statesplugin.commands.subcommands.alliance.*;
import ru.totalcraftmc.statesplugin.commands.subcommands.common.Bank;
import ru.totalcraftmc.statesplugin.commands.utils.AbstractCommand;

import java.util.stream.Stream;

public class AllianceCommand extends AbstractCommand {
    public AllianceCommand() {
        super("alliance", Stream.of(
                new Create(),
                new Destroy(),
                new Info(),
                new Invite(),
                new Join(),
                new Kick(),
                new Leader(),
                new Leave(),
                new Rename(),
                new Bank()
        ));

    }
}
