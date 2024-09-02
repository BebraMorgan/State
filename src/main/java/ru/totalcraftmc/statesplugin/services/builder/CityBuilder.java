package ru.totalcraftmc.statesplugin.services.builder;

import org.bukkit.Location;
import ru.totalcraftmc.statesplugin.dao.BalanceDAO;
import ru.totalcraftmc.statesplugin.dao.CityDAO;
import ru.totalcraftmc.statesplugin.dao.PlayerDAO;
import ru.totalcraftmc.statesplugin.dao.SectorDAO;
import ru.totalcraftmc.statesplugin.entities.Balance;
import ru.totalcraftmc.statesplugin.entities.City;
import ru.totalcraftmc.statesplugin.entities.Sector;
import ru.totalcraftmc.statesplugin.entities.StatePlayer;

public class CityBuilder {

    private City city;
    private Balance balance;
    private Sector sector;
    private StatePlayer mayor;

    private final PlayerDAO playerDAO = new PlayerDAO();
    private final CityDAO cityDAO = new CityDAO();
    private final BalanceDAO balanceDAO = new BalanceDAO();
    private final SectorDAO sectorDAO = new SectorDAO();

    public CityBuilder() {
        city = new City();
    }

    public CityBuilder name(String name) {
        city.setName(name);
        return this;
    }

    public CityBuilder location(Location location) {
        city.setX(location.getBlockX());
        city.setZ(location.getBlockZ());
        return this;
    }

    public CityBuilder mayor(StatePlayer mayor) {
        this.mayor = mayor;
        city.getPlayers().add(mayor);
        city.setMayor(mayor);
        return this;
    }

    public CityBuilder createBalance() {
        balance = new Balance();
        balanceDAO.insert(balance);
        return this;
    }

    public CityBuilder balance(Balance balance) {
        this.balance = balance;
        return this;
    }

    public CityBuilder sector(Sector sector) {
        this.sector = sector;
        return this;
    }

    public City build() {
        city.setBalance(balance);
        cityDAO.insert(city);
        playerDAO.update(city.getMayor());
        sector.setOwner(city);
        city.getSectors().add(sector);
        sectorDAO.update(sector);
        cityDAO.update(city);
        return city;
    }



}
