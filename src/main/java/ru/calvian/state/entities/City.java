package ru.calvian.state.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class City {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Balance balance;

    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private State state;

    @OneToMany(mappedBy = "city")
    private List<StatePlayer> players = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "city_assistants",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "assistant_id"))
    private List<StatePlayer> assistants;

    private double x;

    private double y;

    @OneToOne
    private StatePlayer mayor;
}
