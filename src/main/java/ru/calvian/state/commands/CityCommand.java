package ru.calvian.state.commands;

import org.bukkit.Bukkit;
import ru.calvian.state.commands.utils.BalanceCommand;
import ru.calvian.state.commands.utils.Messages;
import ru.calvian.state.commands.utils.PlayerCommand;
import ru.calvian.state.entities.Balance;
import ru.calvian.state.entities.City;
import ru.calvian.state.entities.StatePlayer;
import ru.calvian.state.events.city.*;
import ru.calvian.state.events.player.PlayerCityJoinEvent;
import ru.calvian.state.events.player.PlayerCityLeaveEvent;
import ru.calvian.state.repositories.CityRepository;
import ru.calvian.state.repositories.StatePlayerRepository;

import java.util.List;

public class CityCommand extends PlayerCommand {
    private final StatePlayerRepository playerRepository = new StatePlayerRepository();
    private final CityRepository cityRepository = new CityRepository();

    public CityCommand() {
        super("city");
    }

    @Override
    public void performExecute(String[] args) {
        StatePlayer statePlayer = playerRepository.findByNick(player.getName()).get(0);
        if (args.length == 0) {
            if (statePlayer.getCity() == null) {
                player.sendMessage(Messages.NO_CITY);
                return;
            }
            info(statePlayer.getCity().getName());
            return;
        }
        switch (args[0]) {
            case "create" -> create(args);
            case "destroy" -> destroy();
            case "bank" -> new BalanceCommand(player, statePlayer.getCity().getBalance(), args).bank();
            case "rename" -> rename(args[1]);
            case "invite" -> invite(args);
            case "kick" -> kick(args);
            case "join" -> join(args);
            case "leave" -> leave();
            case "mayor" -> mayor(args);
            case "assistant" -> assistant(args);
            case "info" ->
                    info(args[1] == null && statePlayer.getCity() != null ? statePlayer.getCity().getName() : args[1]);
        }
    }

    private void assistant(String[] args) {
        StatePlayer statePlayer = playerRepository.findByNick(player.getName()).get(0);
        if (statePlayer.getCity() == null) {
            player.sendMessage(Messages.NO_CITY);
            return;
        }
        if (args.length == 1) {
            player.sendMessage("Ассистенты в вашем городе: ");
            statePlayer.getCity().getAssistants().forEach(assistant -> player.sendMessage(assistant.getNick() + ","));
        }
        if (args[2] == null) return;
        if (args[1].equals("assign")) {
            Bukkit.getPluginManager().callEvent(new AssistantAssignEvent(player, args[2]));
        }
    }

    private void mayor(String[] args) {
        StatePlayer statePlayer = playerRepository.findByNick(player.getName()).get(0);
        if (statePlayer.getCity() == null) {
            player.sendMessage(Messages.NO_CITY);
            return;
        }
        if (args[1] == null) {
            player.sendMessage("Мэр вашего города: " + statePlayer.getCity().getMayor().getNick());
            return;
        }
        if (args.length < 3) return;
        if (args[1].equals("assign")) {
            Bukkit.getPluginManager().callEvent(new MayorSetEvent(player, args[2]));
        }

    }

    private void rename(String name) {
        if (name == null) return;
        Bukkit.getPluginManager().callEvent(new CityRenameEvent(player, name));
    }


    private void join(String[] args) {
        StatePlayer statePlayer = playerRepository.findByNick(player.getName()).get(0);
        if (args.length < 2) {
            statePlayer.getPlayer().sendMessage("Использование: /c join <город>");
            return;
        }
        City city = cityRepository.findByName(args[1]).get(0);
        if (city == null) return;
        Bukkit.getPluginManager().callEvent(new PlayerCityJoinEvent(city, statePlayer));
    }

    private void leave() {
        StatePlayer statePlayer = playerRepository.findByNick(player.getName()).get(0);
        Bukkit.getPluginManager().callEvent(new PlayerCityLeaveEvent(statePlayer));
    }

    private void invite(String[] args) {
        if (args.length < 2) return;
        Bukkit.getPluginManager().callEvent(new CityInviteEvent(args[1], player));
    }

    private void kick(String[] args) {
        if (args.length < 2) return;
        Bukkit.getPluginManager().callEvent(new CityKickEvent(args[1], player));
    }

    private void create(String[] args) {
        if (args.length < 2) return;
        Bukkit.getPluginManager().callEvent(new CityCreateEvent(args[1], player));
    }

    private void destroy() {
        Bukkit.getPluginManager().callEvent(new CityDestroyEvent(player));
    }

    public void info(String name) {
        List<City> cities = cityRepository.findByName(name);
        if (cities.isEmpty()) {
            player.sendMessage(Messages.CITY_NOT_FOUND);
        }
        City city = cities.get(0);
        StringBuilder info = new StringBuilder();
        info.append("Город: ").append(city.getName()).append("\n").append("Мэр: ").append(city.getMayor().getNick()).append("\n").append("Ассистенты: ").append("\n");
        city.getAssistants().forEach(assistant -> info.append(assistant.getNick()).append(","));
        info.append("\n").append("Жители: ").append("\n");
        city.getPlayers().forEach(resident -> info.append(resident.getNick()).append(','));
        Balance balance = city.getBalance();
        info.append("Баланс:\nЖелезо: ").append(balance.getIron()).append("\n").append("Алмазы: ").append(balance.getDiamonds()).append("\n").append("Незерит: ").append(balance.getNetherite()).append("\n");
        player.sendMessage(info.toString());
    }
}
