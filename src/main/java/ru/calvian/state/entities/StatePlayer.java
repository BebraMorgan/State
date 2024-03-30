package ru.calvian.state.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.calvian.state.entities.dictionaries.DictPlayerRoles;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "state_players")
public class StatePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT UNSIGNED")
    private int id;

    @ManyToOne
    private DictPlayerRoles role;

    private String nick;

    private int confirmed;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @ManyToOne
    private Balance balance;

    public Player getPlayer() {
        return Bukkit.getPlayer(nick);
    }

    public State getState() {
        if (city == null) return null;
        return city.getState();
    }

    public boolean isResident() {
        return city != null;
    }

    public boolean isAssistant() {
        if(!isResident()) return false;
        return city.getAssistants().contains(this);
    }

    public boolean isMayor() {
        if(!isResident()) return false;
        return this == city.getMayor();
    }
    public Alliance getAlliance() {
        if (getState() == null) return null;
        return getState().getAlliance();
    }
}

