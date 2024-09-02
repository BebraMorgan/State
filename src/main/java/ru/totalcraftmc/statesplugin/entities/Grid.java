package ru.totalcraftmc.statesplugin.entities;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.dynmap.markers.AreaMarker;
import org.dynmap.markers.MarkerSet;
import ru.totalcraftmc.statesplugin.StatesPlugin;
import ru.totalcraftmc.statesplugin.dao.SectorDAO;

import java.util.Arrays;
import java.util.List;

public class Grid {

    @Override
    public String toString() {
        return "Grid{" +
                "markerSet=" + markerSet +
                ", size=" + size +
                ", cols=" + cols +
                ", rows=" + rows +
                ", world='" + world + '\'' +
                ", grid=" + Arrays.toString(grid[0]) +
                '}';
    }

    private final Sector[][] grid;
    private final String world;
    private final int rows;
    private final int cols;
    private final int size;
    private MarkerSet markerSet;

    public Grid(String world, int rows, int cols, int size, MarkerSet markerSet) {
        this.world = world;
        this.rows = rows;
        this.cols = cols;
        this.size = size;
        this.markerSet = markerSet;
        grid = new Sector[rows][cols];
    }

    public void create() {
        SectorDAO sectorDAO = new SectorDAO();
        List<Sector> sectors = sectorDAO.all();
        if (sectors.isEmpty()) {
            Chunk spawnChunk = StatesPlugin.getInstance().getServer().getWorld(world).getSpawnLocation().getChunk();
            int spawnX = spawnChunk.getX();
            int spawnZ = spawnChunk.getZ();
            double[] x = {spawnX, spawnX + size};
            double[] z = {-255, 0};
            for (int i = 0; i < rows; i++) {
                x[0] = spawnX;
                x[1] = spawnX + size;
                z[0] += size;
                z[1] += size;
                for (int j = 0; j < cols; j++) {
                    grid[i][j] = new Sector(null, x.clone(), z.clone());
                    sectorDAO.insert(grid[i][j]);
                    x[0] += size;
                    x[1] += size;
                }
            }
        } else {
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < rows; j++) {
                    grid[i][j] = sectors.get(i * cols + j);
                }
            }
        }
    }

    public Sector findSector(Location location) {
        return findSector(location.getBlockX(), location.getBlockZ());
    }

    public Sector findSector(double x, double z) {
        for (Sector[] row : grid) {
            for (Sector sector : row) {
                if ((sector.getX()[0] <= x && x <= sector.getX()[1]) &&
                        (sector.getZ()[0] <= z && z <= sector.getZ()[1])) {
                    System.out.println(sector.getId());
                    return sector;
                }
            }
        }
        return null;
    }

    private String description(City city) {
        StringBuilder players = new StringBuilder();
        StringBuilder assistants = new StringBuilder();
        String state = city.getState() != null ?  city.getState().getName() : "нет";
        if (city.getPlayers().isEmpty()) players.append("Нет");
        else {
            for (StatePlayer player : city.getPlayers()) {
                players.append(player.getName()).append(", ");
            }
        }
        if (city.getAssistants().isEmpty()) assistants.append("нет");
        else {
            for (StatePlayer assistant : city.getAssistants()) {
                assistants.append(assistant.getName()).append(", ");
            }
        }
        return "<h1>" + city.getName() + "</h1>" +
                "<ul>" +
                    "<li><h3> Государство: " + state + "</h3></li>" +
                    "<li><h3> Мэр: " + city.getMayor().getName() + "</h3></li>" +
                    "<li><h3> Жители: " + players.toString() +  "</h3></li>" +
                    "<li><h3> Ассистенты: " + assistants.toString() + "</h3></li>" +
                "</ul>";
    }

    public void draw() {
        markerSet.deleteMarkerSet();
        markerSet = null;
        markerSet = StatesPlugin.getInstance().createMarkerSet();
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (grid[i][j].getOwner() != null) {
                    AreaMarker marker = markerSet.createAreaMarker(
                            grid[i][j].getOwner().getName() + j + "|" + i, description(grid[i][j].getOwner()), true,
                            world, grid[i][j].getX(), grid[i][j].getZ(), false);
                    if (grid[i][j].getOwner().getState() == null) {
                        marker.setFillStyle(0.5, Color.GRAY.getValue());
                    }
                    else {
                        if (grid[i][j].getOwner().getState().getColor() != 0) {
                            marker.setFillStyle(0.5, grid[i][j].getOwner().getState().getColor());
                        }
                    }
                    marker.setLineStyle(3, 1, 0x0000);
                }
            }
        }
    }
}
