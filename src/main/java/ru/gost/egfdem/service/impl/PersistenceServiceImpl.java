package ru.gost.egfdem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Service;
import ru.gost.egfdem.entity.*;
import ru.gost.egfdem.repository.ParameterRepository;
import ru.gost.egfdem.repository.StationRepository;
import ru.gost.egfdem.service.PersistenceService;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Period;
import java.util.List;

@Service
@Slf4j
public class PersistenceServiceImpl implements PersistenceService {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private StationRepository stationRepository;

    @Override
    public boolean saveAtmosphereData(List<AtmosphereEntity> atmosphereList) {
        try {
            template.batchUpdate(
                    "INSERT INTO public.atmosphere(\n" +
                            "\tdate_time, height, temperature)\n" +
                            "\tVALUES (?, ?, ?);",
                    atmosphereList,
                    100,
                    new ParameterizedPreparedStatementSetter<AtmosphereEntity>() {
                        public void setValues(PreparedStatement ps, AtmosphereEntity atmosphere)
                                throws SQLException {
                            ps.setTimestamp(1, Timestamp.valueOf(atmosphere.getDateTime()));
                            ps.setDouble(2, atmosphere.getHeight());
                            ps.setDouble(3, atmosphere.getTemperature());
                        }
                    });
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean saveRadiationData(List<RadiationEntity> radiationList) {
        try {
            template.batchUpdate(
                    "INSERT INTO public.radiation(\n" +
                            "\tcomplex, device, parameter,middleDate, middleValue, minDate, minValue, maxDate, maxValue)\n" +
                            "\tVALUES (?, ?, ?,?, ?, ?,?, ?, ?);",
                    radiationList,
                    100,
                    new ParameterizedPreparedStatementSetter<RadiationEntity>() {
                        public void setValues(PreparedStatement ps, RadiationEntity radiation)
                                throws SQLException {
                            ps.setInt(1, radiation.getComplex());
                            ps.setString(2, radiation.getDevice());
                            ps.setInt(3, radiation.getParameter());
                            ps.setTimestamp(4, Timestamp.valueOf(radiation.getMiddleDate()));
                            ps.setDouble(5, radiation.getMiddleValue());
                            ps.setTimestamp(6, Timestamp.valueOf(radiation.getMinDate()));
                            ps.setDouble(7, radiation.getMinValue());
                            ps.setTimestamp(8, Timestamp.valueOf(radiation.getMaxDate()));
                            ps.setDouble(9, radiation.getMaxValue());
                        }
                    });
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean saveOstankinoData(List<ResearchEntity> researchList) {
        try {
            template.batchUpdate(
                    "INSERT INTO public.research(\n" +
                            "\tdate, station_id, parameter_id, height, research_value, modify_value)\n" +
                            "\tVALUES (?, ?, ?, ?, ?, ?);",
                    researchList,
                    100,
                    new ParameterizedPreparedStatementSetter<ResearchEntity>() {
                        public void setValues(PreparedStatement ps, ResearchEntity research)
                                throws SQLException {
                            ps.setTimestamp(1, Timestamp.valueOf(research.getDate()));
                            ps.setLong(2, research.getStation().getId());
                            ps.setLong(3, research.getParameter().getId());
                            ps.setDouble(4, research.getHeight());
                            ps.setDouble(5, research.getResearchValue());
                            ps.setDouble(6, research.getModifyValue());
                        }
                    });
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public ParameterEntity getParameterByCode(String code){
        return parameterRepository.findByTitle(code);
    }

    @Override
    public StationEntity getOstankinoStation(){
        return stationRepository.findById(1l).get();
    }
}
