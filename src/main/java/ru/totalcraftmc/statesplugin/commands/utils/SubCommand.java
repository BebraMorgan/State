package ru.totalcraftmc.statesplugin.commands.utils;

import java.lang.reflect.InvocationTargetException;

public interface SubCommand {
    void execute(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    String getName();
}
