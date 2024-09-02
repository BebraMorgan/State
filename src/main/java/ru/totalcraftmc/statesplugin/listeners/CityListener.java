package ru.totalcraftmc.statesplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.totalcraftmc.statesplugin.StatesPlugin;
import ru.totalcraftmc.statesplugin.commands.utils.Messages;
import ru.totalcraftmc.statesplugin.dao.BalanceDAO;
import ru.totalcraftmc.statesplugin.dao.CityDAO;
import ru.totalcraftmc.statesplugin.dao.PlayerDAO;
import ru.totalcraftmc.statesplugin.dao.SectorDAO;
import ru.totalcraftmc.statesplugin.entities.City;
import ru.totalcraftmc.statesplugin.entities.Grid;
import ru.totalcraftmc.statesplugin.entities.Sector;
import ru.totalcraftmc.statesplugin.entities.StatePlayer;
import ru.totalcraftmc.statesplugin.events.city.*;
import ru.totalcraftmc.statesplugin.services.CityService;


public class CityListener implements Listener {

    private final PlayerDAO playerDAO = new PlayerDAO();
    private final CityDAO cityDAO = new CityDAO();
    private final BalanceDAO balanceDAO = new BalanceDAO();
    private final SectorDAO sectorDAO = new SectorDAO();
    private final Grid grid = StatesPlugin.getGrid();

    private final CityService cityService = new CityService();

    @EventHandler
    public void onCreate(CityCreateEvent event) {
        Player player = event.getPlayer();
        String name = event.getName();

        StatePlayer statePlayer = playerDAO.findByName(player.getName());
        if (statePlayer == null) return;
        if (statePlayer.cant("createCity")) {
            player.sendMessage(String.format(Messages.ALREADY_IN_CITY, statePlayer.getCity()));
            return;
        }

        Location location = player.getLocation();
        Sector sector = grid.findSector(location);
        if (sector.getOwner() != null) {
            player.sendMessage(String.format(Messages.SECTOR_ALREADY_CLAIMED, sector.getOwner().getName()));
            return;
        }

        cityService.create(name, statePlayer, location, sector);

        grid.draw();
    }
    @EventHandler
    public void onDestroy(CityDestroyEvent event) {
        Player player = event.getPlayer();
        StatePlayer statePlayer = playerDAO.findByName(player.getName());
        if (statePlayer.cant("destroyCity")) {
            player.sendMessage(Messages.CITY_NO_PERMS);
            return;
        }

        City city = statePlayer.getCity();

        cityService.destroy(city);

        grid.draw();
    }

    public void onRename(CityRenameEvent event) {
        Player player = event.getPlayer();
        String name = event.getName();
        StatePlayer statePlayer = playerDAO.findByName(player.getName());
        if (statePlayer == null) return;
        if (statePlayer.notResident()) player.sendMessage(Messages.NO_CITY);
        if (statePlayer.cant("renameCity")) {
            player.sendMessage(Messages.CITY_NO_PERMS);
            return;
        }

        City city = statePlayer.getCity();

        city.setName(name);

        cityDAO.update(city);

        grid.draw();
        player.sendMessage(Messages.RENAMED);
    }

    public void onKick(CityKickEvent event) {
        Player player = event.getPlayer();
        String name = event.getName();
        StatePlayer statePlayer = playerDAO.findByName(player.getName());
        StatePlayer lox = playerDAO.findByName(name);
        if (lox == null) {
            player.sendMessage(Messages.PLAYER_NOT_FOUND);
            return;
        }

        if (lox.equals(statePlayer)) {
            player.sendMessage("Вы не можете выгнать сами себя");
            return;
        }

        if (statePlayer.notResident()) {
            player.sendMessage(Messages.NO_CITY);
            return;
        }
        if (statePlayer.cant("kickFromCity")) {
            player.sendMessage(Messages.CITY_NO_PERMS);
            return;
        }


        if (lox.getCity() == null) {
            player.sendMessage(Messages.PLAYER_NOT_IN_CITY);
            return;
        }
        if (!lox.getCity().equals(statePlayer.getCity())) {
            player.sendMessage(Messages.PLAYER_NOT_IN_YOUR_CITY);
            return;
        }

        if (lox.isMayor()) {
            player.sendMessage("Вы не можете выгнать мэра");
        }


        cityService.kick(statePlayer.getCity(), lox);

        player.sendMessage("Игрок " + name + " выгнан из города.");
    }

    public void onMayorSet(MayorSetEvent event) {
        Player player = event.getPlayer();
        String name = event.getName();

        StatePlayer statePlayer = playerDAO.findByName(player.getName());
        if (statePlayer == null) return;
        if (statePlayer.notMayor()) {
            player.sendMessage(Messages.NOT_MAYOR);
            return;
        }

        StatePlayer newMayor = playerDAO.findByName(name);
        if (newMayor == null) return;
        if (newMayor.notResident()) return;
        if (!newMayor.getCity().equals(statePlayer.getCity())) return;

        statePlayer.getCity().setMayor(newMayor);
    }



}
