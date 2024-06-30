package ru.totalcraftmc.statesplugin.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @ManyToOne
    private Balance balance;


    public State getState() {
        if (city == null) return null;
        return city.getState();
    }

    public boolean notResident() {
        return city == null;
    }

    public boolean notStateLeader() {
        if (getState() == null) return true;
        return !getState().getLeader().equals(this);
    }

//    public boolean isAssistant() {
//        if(notResident()) return false;
//        return city.getAssistants().contains(this);
//    }

    public boolean isMayor() {
        if (notResident()) return false;
        return this == city.getMayor();
    }

    public Alliance getAlliance() {
        if (getState() == null) return null;
        return getState().getAlliance();
    }
}

