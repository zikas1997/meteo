package ru.gost.egfdem.service;

import ru.gost.egfdem.entity.*;

import java.util.List;

public interface PersistenceService {
    boolean saveAtmosphereData(List<AtmosphereEntity> atmosphereList);

    boolean saveRadiationData(List<RadiationEntity> radiationList);

    boolean saveOstankinoData(List<ResearchEntity> researchList);

    ParameterEntity getParameterByCode(String code);

    StationEntity getOstankinoStation();
}
