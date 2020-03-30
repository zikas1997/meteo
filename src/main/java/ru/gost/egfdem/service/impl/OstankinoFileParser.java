package ru.gost.egfdem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gost.egfdem.entity.ParameterEntity;
import ru.gost.egfdem.entity.RadiationEntity;
import ru.gost.egfdem.entity.ResearchEntity;
import ru.gost.egfdem.entity.StationEntity;
import ru.gost.egfdem.service.FileParser;
import ru.gost.egfdem.service.PersistenceService;

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
@Service("ostankinoParser")
public class OstankinoFileParser implements FileParser {

    @Autowired
    private PersistenceService persistenceService;

    @Override
    public void parseFile(InputStream file, String fileName) {
        List<ResearchEntity> list = new ArrayList<>();
        String parameterName = fileName.substring(0, fileName.indexOf("-"));
        String heightStr = fileName.substring(fileName.indexOf("-")+1, fileName.indexOf("."));
        Double height = Double.valueOf(heightStr);
        ParameterEntity parameter = persistenceService.getParameterByCode(parameterName);
        StationEntity station = persistenceService.getOstankinoStation();
        try (Stream<String> stream = new BufferedReader(new InputStreamReader(file)).lines()) {
            list = stream.map(s -> {
                try {
                    ResearchEntity research = new ResearchEntity();
                    research.setStation(station);
                    research.setParameter(parameter);
                    research.setHeight(height);
                    String[] researchData = s.split(";");
                    String dateTime =researchData[1].trim()+" "+researchData[0].trim();
                    research.setDate(LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
                    research.setResearchValue(Double.valueOf(researchData[2].trim()));
                    research.setModifyValue(Double.valueOf(researchData[2].trim()));
                    return research;
                }
                catch (Exception e){
                    log.error(e.getMessage());
                }
                return null;
            }).collect(Collectors.toList());
            persistenceService.saveOstankinoData(list);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
