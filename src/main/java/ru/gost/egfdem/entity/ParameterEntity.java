package ru.gost.egfdem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "parameter")
@Data
public class ParameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer number;

    @Column(name = "act_number")
    private Integer actNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "unit_id")
    private Integer unitId;

    private Double accuracy;

    private String type;

}
