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
@Table(name = "alliances")
public class Alliance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT UNSIGNED")
    private int id;

    @ManyToOne
    private Balance balance;

    private String name;

    @OneToMany
    @JoinTable(name = "alliance_diplomats",
            joinColumns = @JoinColumn(name = "alliance_id"),
            inverseJoinColumns = @JoinColumn(name = "diplomat_id"))
    private List<StatePlayer> diplomats;

    @OneToMany(mappedBy = "alliance")
    private List<State> states;
}
