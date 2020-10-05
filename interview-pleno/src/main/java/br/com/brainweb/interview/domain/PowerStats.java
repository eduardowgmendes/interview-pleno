package br.com.brainweb.interview.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Period;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerStats implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private Integer strength;
    private Integer agility;
    private Integer dexterity;
    private Integer intelligence;
    private Period createdAt;
    private Period updatedAt;

    @OneToMany(mappedBy = "powerStats")
    private Hero hero;

}
