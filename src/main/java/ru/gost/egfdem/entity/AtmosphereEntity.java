package ru.gost.egfdem.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "atmosphere")
@Data
public class AtmosphereEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_time")
    LocalDateTime dateTime;

    Double height;

    Double temperature;
}
