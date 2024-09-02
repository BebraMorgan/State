package ru.totalcraftmc.statesplugin.services;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.dao.*;
import ru.totalcraftmc.statesplugin.entities.*;
import ru.totalcraftmc.statesplugin.services.builder.CityBuilder;

public class CityService {
    private final PlayerDAO playerDAO = new PlayerDAO();
    private final CityDAO cityDAO = new CityDAO();
    private final BalanceDAO balanceDAO = new BalanceDAO();
    private final SectorDAO sectorDAO = new SectorDAO();
    private final StateDAO stateDAO = new StateDAO();

    public City create(String name, StatePlayer creator, Location location, Sector sector) {
        return new CityBuilder().name(name).location(location).createBalance().mayor(creator).sector(sector).build();
    }

    public void destroy(City city) {
        StatePlayer mayor = city.getMayor();
        city.setMayor(null);
        mayor.setCity(null);
        city.getPlayers().forEach(player -> {
            if (player.isAssistant()) city.getAssistants().remove(player);
            player.setCity(null);
            playerDAO.update(player);

        });
        city.getPlayers().clear();

        if (city.getState() != null) {
            State state = city.getState();
            state.getCities().remove(city);
            city.setState(null);
            stateDAO.update(state);
        }

        Balance balance = city.getBalance();
        city.setBalance(null);
        balanceDAO.delete(balance);

        city.getSectors().forEach(sector -> {
            sector.setOwner(null);
            sectorDAO.update(sector);
        });
        city.getSectors().clear();
        cityDAO.update(city);
        cityDAO.delete(city);
    }

    public void kick(City city, StatePlayer player) {
        city.getPlayers().remove(player);
        player.setCity(null);
        cityDAO.update(city);
        playerDAO.update(player);
    }

}
