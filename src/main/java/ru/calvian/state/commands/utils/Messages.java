package ru.calvian.state.commands.utils;

public class Messages {
    private static final String PREF = "[States Core] ";
    public static final String CITY_LEFT = PREF + "Вы покинули город %s";

    public static final String ONLY_PLAYERS = PREF + "Эту команду могут использовать только игроки.";
    public static final String NO_CITY = PREF + "Вы не состоите ни в одном городе!";
    public static final String NO_STATE = PREF + "Вы не состоите ни в одном государстве!";
    public static final String CITY_JOINED = PREF + "Вы вступили в город %s";
    public static final String CITY_NOT_FOUND = PREF + "Город не найден!";
    public static final String STATE_NOT_FOUND = PREF + "Государство не найдено!";
    public static final String CITY_NOT_INVITED = PREF + "Вы не были приглашены в %s";
    public static final String ALREADY_IN_CITY = PREF + "Вы уже состоите в городе %s";
    public static final String NOT_MAYOR = PREF + "Вы не являетесь мэром этого города!";
    public static final String PLAYER_ALREADY_INVITED = PREF + "Игрок %s уже приглашен в ваш город!";
    public static final String PLAYER_ALREADY_IN_CITY = PREF + "Игрок %s уже состоит в городе!";
    public static final String PLAYER_CITY_INVITED = PREF + "Приглашение игроку %s отправлено!";
    public static final String YOU_WAS_INVITED = PREF + "Вы были приглашены в город %s\n Принять приглашение: \\player join %s";
    public static final String CITY_CREATED = PREF + "Игрок %s возвел город \"%s\"";
    public static final String CITY_DESTROYED = PREF + "Город %s разрушен!";

}
