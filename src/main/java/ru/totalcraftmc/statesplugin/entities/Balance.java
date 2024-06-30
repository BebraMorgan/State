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
@Table(name = "balances")
public class Balance {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int diamonds;
    private int iron;
    private int netherite;


    public void depositIron(int count) {
        iron += count;
    }

    public void depositDiamond(int count) {
        diamonds += count;
    }

    public void depositNetherite(int count) {
        netherite += count;
    }

    public boolean withdrawIron(int count) {
        if (iron - count < 0) return false;
        iron -= count;
        return true;
    }

    public boolean withdrawDiamond(int count) {
        if (diamonds - count < 0) return false;
        diamonds -= count;
        return true;
    }

    public boolean withdrawNetherite(int count) {
        if (netherite - count < 0) return false;
        netherite -= count;
        return true;
    }
}
