package ru.gost.egfdem.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "radiation")
@Data
public class RadiationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer complex;

    private String device;

    private Integer parameter;

    private LocalDateTime middleDate;

    private Double middleValue;

    private LocalDateTime minDate;

    private Double minValue;

    private LocalDateTime maxDate;

    private Double maxValue;
}
