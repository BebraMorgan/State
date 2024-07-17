package ru.totalcraftmc.statesplugin.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.totalcraftmc.statesplugin.policies.utils.Policy;

import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    public boolean isAssistant() {
        if(notResident()) return false;
        return city.getAssistants().contains(this);
    }

    public boolean isMayor() {
        if (notResident()) return false;
        return this == city.getMayor();
    }

    public Alliance getAlliance() {
        if (getState() == null) return null;
        return getState().getAlliance();
    }
    public boolean isStateLeader() {
        if (getState() == null) return false;
        return getState().getLeader().equals(this);
    }
    public boolean can(String ability, Object arg) {
        try {
            Policy policy = (Policy) Class
                    .forName("ru.totalcraftmc.statesplugin.policies."
                            + this.getClass().getSimpleName() + "Policy")
                    .getDeclaredConstructor().newInstance();
            Method method = policy.getClass().getDeclaredMethod(ability, arg.getClass());
            return (boolean) method.invoke(policy, arg);
        } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean cant(String ability, Object arg) {
        return !can(ability, arg);
    }

    public boolean isMinister() {
        if (getState() == null) return false;
        return getState().getMinisters().contains(this);
    }
}

