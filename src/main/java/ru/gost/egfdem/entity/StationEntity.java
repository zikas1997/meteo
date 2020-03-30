package ru.gost.egfdem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "station")
@Data
public class StationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer number;

    private Integer type;

    private Double longitude;

    private Double latitude;

    @Column(name = "district_id")
    private Integer districtId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="station_parameter",
            joinColumns = {@JoinColumn(name="station_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="parameter_id", referencedColumnName="id")}
    )
    protected List<ParameterEntity> parameters = new ArrayList<ParameterEntity>();
}
