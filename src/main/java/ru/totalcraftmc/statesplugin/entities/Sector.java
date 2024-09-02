package ru.totalcraftmc.statesplugin.entities;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sectors")
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private City owner;
    @ElementCollection
    @OrderColumn
    private double[] x;
    @ElementCollection
    @OrderColumn
    private double[] z;
    public Sector(City city, double[] x, double[] z) {
        this.owner = city;
        this.x = x;
        this.z = z;
    }
}
