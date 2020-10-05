package br.com.brainweb.interview.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Period;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;
    private Race race;

    @Column(name = "power_stats")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hero", referencedColumnName = "hero")
    private PowerStats powerStats;

    @Column(name = "created_at")
    private Period createdAt;

    @Column(name = "updated_at")
    private Period updatedAt;

    public Hero(String name) {
        this.name = name;
    }
}
