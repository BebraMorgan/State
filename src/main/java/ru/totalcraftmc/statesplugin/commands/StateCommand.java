package ru.totalcraftmc.statesplugin.commands;

import ru.totalcraftmc.statesplugin.commands.subcommands.common.Bank;
import ru.totalcraftmc.statesplugin.commands.subcommands.state.*;
import ru.totalcraftmc.statesplugin.commands.utils.AbstractCommand;

import java.util.stream.Stream;

public class StateCommand extends AbstractCommand {
    public StateCommand() {
        super("state", Stream.of(
                new Info(),
                new Create(),
                new Destroy(),
                new Leader(),
                new Name(),
                new Ideology(),
                new Join(),
                new Leave(),
                new Invite(),
                new Kick(),
                new Bank(),
                new Minister()
        ));
    }

}
