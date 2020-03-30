package ru.gost.egfdem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gost.egfdem.entity.RadiationEntity;
import ru.gost.egfdem.service.FileParser;
import ru.gost.egfdem.service.PersistenceService;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service("radiationParser")
public class RadiationFileParser implements FileParser {

    @Autowired
    private PersistenceService persistenceService;

    @Override
    public void parseFile(InputStream file, String fileName) {
        List<RadiationEntity> list = new ArrayList<>();
        try (Stream<String> stream = new BufferedReader(new InputStreamReader(file)).lines()) {
            list = stream.map(s -> {
                try {
                    RadiationEntity radiation = new RadiationEntity();
                    String[] radiationData = s.split(";");
                    radiation.setComplex(Integer.valueOf(radiationData[0].trim()));
                    radiation.setDevice(radiationData[1].trim());
                    radiation.setParameter(Integer.valueOf(radiationData[2].trim()));
                    radiation.setMiddleDate(LocalDateTime.parse(radiationData[3].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                    radiation.setMiddleValue(Double.valueOf(radiationData[4].trim().replace(",", ".")));
                    radiation.setMinDate(LocalDateTime.parse(radiationData[5].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                    radiation.setMinValue(Double.valueOf(radiationData[6].trim().replace(",", ".")));
                    radiation.setMaxDate(LocalDateTime.parse(radiationData[7].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                    radiation.setMaxValue(Double.valueOf(radiationData[8].trim().replace(",", ".")));
                    return radiation;
                }
                catch (Exception e){
                    log.error(e.getMessage());
                }
                return null;
            }).collect(Collectors.toList());
            persistenceService.saveRadiationData(list);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
