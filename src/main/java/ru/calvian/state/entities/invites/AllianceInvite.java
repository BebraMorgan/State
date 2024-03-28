package ru.calvian.state.entities.invites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.calvian.state.entities.Alliance;
import ru.calvian.state.entities.State;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alliance_invites")
public class AllianceInvite {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Alliance alliance;

    @ManyToOne
    private State state;
}
