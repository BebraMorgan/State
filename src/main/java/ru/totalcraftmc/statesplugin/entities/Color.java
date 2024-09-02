package ru.totalcraftmc.statesplugin.entities;


public enum Color {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF),
    CYAN(0x42f4f1),
    MAGENTA(0xFF00FF),
    YELLOW(0xFFFF00),
    GRAY(0x525150);


    private final int value;

    Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
