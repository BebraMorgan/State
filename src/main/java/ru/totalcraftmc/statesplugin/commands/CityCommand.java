package ru.totalcraftmc.statesplugin.commands;

import ru.totalcraftmc.statesplugin.commands.subcommands.city.*;
import ru.totalcraftmc.statesplugin.commands.subcommands.common.Bank;
import ru.totalcraftmc.statesplugin.commands.utils.AbstractCommand;

public class CityCommand extends AbstractCommand {
    public CityCommand() {
        super("city");
        commands.put("create", new Create());
        commands.put("destroy", new Destroy());
        commands.put("rename", new Rename());
        commands.put("assistant", new Assistant());
        commands.put("mayor", new Mayor());
        commands.put("invite", new Invite());
        commands.put("kick", new Kick());
        commands.put("leave", new Leave());
        commands.put("join", new Join());
        commands.put("bank", new Bank());
        commands.put("info", new Info());
    }
}
