package ru.totalcraftmc.statesplugin.commands;

import ru.totalcraftmc.statesplugin.commands.subcommands.city.*;
import ru.totalcraftmc.statesplugin.commands.subcommands.common.Bank;
import ru.totalcraftmc.statesplugin.commands.utils.AbstractCommand;

import java.util.stream.Stream;

public class CityCommand extends AbstractCommand {
    public CityCommand() {
        super("city", Stream.of(
                new Create(),
                new Destroy(),
                new Rename(),
                new Assistant(),
                new Mayor(),
                new Invite(),
                new Kick(),
                new Leave(),
                new Join(),
                new Bank(),
                new Info()
        ));

    }
}
