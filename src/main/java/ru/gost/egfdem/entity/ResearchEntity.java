package ru.gost.egfdem.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "research")
@Data
public class ResearchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id")
    private StationEntity station;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parameter_id")
    private ParameterEntity parameter;

    private Double height;

    @Column(name = "research_value")
    private Double researchValue;

    @Column(name = "modify_value")
    private Double modifyValue;

}
