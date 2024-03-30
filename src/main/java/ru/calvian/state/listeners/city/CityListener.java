package ru.calvian.state.listeners.city;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.calvian.state.commands.utils.Messages;
import ru.calvian.state.entities.Balance;
import ru.calvian.state.entities.City;
import ru.calvian.state.entities.StatePlayer;
import ru.calvian.state.entities.invites.CityInvite;
import ru.calvian.state.events.city.*;
import ru.calvian.state.repositories.*;

import java.util.List;

public class CityListener implements Listener {
    StatePlayerRepository playerRepository = new StatePlayerRepository();
    CityRepository cityRepository = new CityRepository();
    BalanceRepository balanceRepository = new BalanceRepository();
    CityInviteRepository cityInviteRepository = new CityInviteRepository();

    @EventHandler
    public void onCreate(CityCreateEvent event) {
        City city = new City();
        StatePlayer mayor = playerRepository.findByNick(event.getPlayer().getName()).get(0);
        Balance balance = new Balance();
        if (mayor.getCity() != null) {
            event.getPlayer().sendMessage(String.format(Messages.ALREADY_IN_CITY, mayor.getCity().getName()));
            return;
        }
        city.setX(event.getPlayer().getX());
        city.setY(event.getPlayer().getY());
        city.setName(event.getName());
        mayor.setRole(new DictPlayerRolesRepository().find(3));
        city.setBalance(balance);
        balanceRepository.insert(balance);
        cityRepository.insert(city);
        city.setMayor(mayor);
        mayor.setCity(city);
        city.getPlayers().add(mayor);
        cityRepository.update(city);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 0);
        }
        Bukkit.getServer().sendMessage(Component.text(String.format(Messages.CITY_CREATED, mayor.getNick(), city.getName())));
    }

    @EventHandler
    public void onDestroy(CityDestroyEvent event) {
        City city;
        if(event.getPlayer() != null) {
            StatePlayer statePlayer = playerRepository.findByNick(event.getPlayer().getName()).get(0);
            city = statePlayer.getCity();
            if (city == null) {
                event.getPlayer().sendMessage(Messages.NO_CITY);
                return;
            }
            if (!city.getMayor().equals(statePlayer)) {
                event.getPlayer().sendMessage(Messages.NOT_MAYOR);
                return;
            }
            city.getMayor().setRole(new DictPlayerRolesRepository().find(1));

            city.getPlayers().forEach(player -> {
                player.setCity(null);
                playerRepository.update(player);
            });
        } else {
            city = event.getCity();
            if(!city.getPlayers().isEmpty()) {
                city.getPlayers().forEach(player -> {
                    player.setCity(null);
                    playerRepository.update(player);
                });
            }
        }
        Bukkit.getServer().sendMessage(Component.text(String.format(Messages.CITY_DESTROYED, city.getName())));
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0);
        }
        Balance balance = city.getBalance();
        cityRepository.delete(city);
        balanceRepository.delete(balance);
    }


    @EventHandler
    public void onKick(CityKickEvent event) {
        StatePlayer player = playerRepository.findByNick(event.getPlayer().getName()).get(0);
        if (!player.isResident()) {
            event.getPlayer().sendMessage(Messages.NO_CITY);
            return;
        }
        if (!player.isMayor()) {
            event.getPlayer().sendMessage(Messages.NOT_MAYOR);
            return;
        }
        City city = player.getCity();
        List<StatePlayer> losers = playerRepository.findByNick(event.getName());
        if (losers.isEmpty()) {
            event.getPlayer().sendMessage("Игрок не найден!");
            return;
        }
        StatePlayer loser = losers.get(0);
        if (!city.getPlayers().contains(loser)) {
            event.getPlayer().sendMessage("Игрок не состоит в вашем городе");
            return;
        }
        city.getPlayers().remove(loser);
        loser.setCity(null);
        playerRepository.update(loser);
        cityRepository.update(city);
    }


    @EventHandler
    public void onInvite(CityInviteEvent event) {
        StatePlayer receiver = playerRepository.findByNick(event.getName()).get(0);
        StatePlayer sender = playerRepository.findByNick(event.getPlayer().getName()).get(0);
        if (sender.getCity() == null) {
            event.getPlayer().sendMessage(Messages.NO_CITY);
            return;
        }
        if (!sender.getCity().getMayor().equals(sender)) {
            event.getPlayer().sendMessage(Messages.NOT_MAYOR);
            return;
        }
        if (receiver.getCity() != null) {
            event.getPlayer().sendMessage(String.format(Messages.PLAYER_ALREADY_IN_CITY, event.getName()));
            return;
        }
        if (!cityInviteRepository.findByCity(sender.getCity().getId()).isEmpty()) {
            event.getPlayer().sendMessage(String.format(Messages.PLAYER_ALREADY_INVITED, event.getName()));
            return;
        }
        CityInvite invite = new CityInvite();
        invite.setCity(sender.getCity());
        invite.setPlayer(receiver);
        cityInviteRepository.insert(invite);
        event.getPlayer().sendMessage(String.format(Messages.PLAYER_CITY_INVITED, event.getName()));
        Bukkit.getPlayer(receiver.getNick()).sendMessage(String.format(Messages.YOU_WAS_INVITED, sender.getCity().getName(), sender.getCity().getName()));
    }


    @EventHandler
    public void onMayorSet(MayorSetEvent event) {
        Player player = event.getPlayer();
        StatePlayer statePlayer =playerRepository.findByNick(player.getName()).get(0);
        if (!statePlayer.isResident()) {
            player.sendMessage(Messages.NO_CITY);
            return;
        }
        if (!statePlayer.isMayor()) {
            player.sendMessage(Messages.NOT_MAYOR);
            return;
        }
        if(playerRepository.findByNick(event.getName()).isEmpty()) {
            player.sendMessage("Игрок не найден");
            return;
        }
        StatePlayer newMayor = playerRepository.findByNick(event.getName()).get(0);
        if (!statePlayer.getCity().equals(newMayor.getCity())) {
            player.sendMessage("Игрок не является членом вашего города");
            return;
        }
        statePlayer.getCity().setMayor(newMayor);
        cityRepository.update(statePlayer.getCity());
    }


    @EventHandler
    public void onAssistantAssign(AssistantAssignEvent event) {
        Player player = event.getPlayer();
        StatePlayer statePlayer = playerRepository.findByNick(player.getName()).get(0);
        if (!statePlayer.isResident()) {
            player.sendMessage(Messages.NO_CITY);
            return;
        }
        if (!statePlayer.isMayor()) {
            player.sendMessage(Messages.NOT_MAYOR);
            return;
        }
        StatePlayer assistant = playerRepository.findByNick(event.getName()).get(0);
        List<StatePlayer> assistants = statePlayer.getCity().getAssistants();
        if (!assistant.getCity().equals(statePlayer.getCity())) {
            player.sendMessage("Игрок не является членом вашего города");
            return;
        }
        if (assistants.contains(assistant)) {
            player.sendMessage("Игрок уже является ассистентом");
            return;
        }

        assistants.add(assistant);
        cityRepository.update(statePlayer.getCity());
        player.sendMessage("Игрок теперь ассистент");
    }


    @EventHandler
    public void onAssistantDismiss(AssistantDismissEvent event) {
        Player player = event.getPlayer();
        StatePlayer statePlayer = playerRepository.findByNick(player.getName()).get(0);
        if (!statePlayer.isResident()) {
            player.sendMessage(Messages.NO_CITY);
            return;
        }
        if (!statePlayer.isMayor()) {
            player.sendMessage(Messages.NOT_MAYOR);
            return;
        }
        StatePlayer assistant = playerRepository.findByNick(event.getName()).get(0);
        List<StatePlayer> assistants = statePlayer.getCity().getAssistants();
        if (!assistant.getCity().equals(statePlayer.getCity())) {
            player.sendMessage("Игрок не является членом вашего города");
            return;
        }
        if (!assistants.contains(assistant)) {
            player.sendMessage("Игрок не ассистент");
            return;
        }
        assistants.remove(assistant);
        cityRepository.update(statePlayer.getCity());
        player.sendMessage("Игрок больше не ассистент");
    }


    @EventHandler
    public void onRename(CityRenameEvent event) {
        Player player = event.getPlayer();
        StatePlayer statePlayer = playerRepository.findByNick(player.getName()).get(0);
        if (!statePlayer.isResident()) {
            player.sendMessage(Messages.NO_CITY);
            return;
        }
        if (!statePlayer.isMayor()) {
            player.sendMessage(Messages.NOT_MAYOR);
            return;
        }
        statePlayer.getCity().setName(event.getName());
        cityRepository.update(statePlayer.getCity());
        player.sendMessage("Имя успешно изменено");
    }

}
