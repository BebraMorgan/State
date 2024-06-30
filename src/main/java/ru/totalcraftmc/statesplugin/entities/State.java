package ru.totalcraftmc.statesplugin.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "states")
public class State {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String ideology;

    @ManyToOne
    private Balance balance;

    @ManyToOne
    private StatePlayer leader;

    @OneToMany
    @JoinTable(name = "state_ministers",
            joinColumns = @JoinColumn(name = "state_id"),
            inverseJoinColumns = @JoinColumn(name = "minister_id"))
    private List<StatePlayer> ministers;


    @ManyToOne
    private Alliance alliance;

    @OneToMany(mappedBy = "state")
    private List<City> cities;

}
