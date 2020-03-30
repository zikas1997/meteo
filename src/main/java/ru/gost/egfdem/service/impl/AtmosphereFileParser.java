package ru.gost.egfdem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gost.egfdem.entity.AtmosphereEntity;
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
@Service("atmosphereParser")
public class AtmosphereFileParser implements FileParser {

    @Autowired
    private PersistenceService persistenceService;

    @Override
    public void parseFile(InputStream file, String fileName) {
        try {
            List<String> list = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(file))) {
                String line;
                boolean mainData = false;
                while ((line = br.readLine()) != null) {
                    if (mainData || line.contains("data time")) {
                        list.add(line);
                        mainData = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String header = list.get(0);
            header = header.replaceAll("data time", "").replaceAll("OutsideTemperature", "").replaceAll("Quality", "").trim();
            String[] heightArr = header.split("\\s+");
            list.stream().skip(1).forEach(s -> {
                try {
                    List<AtmosphereEntity> atmosphereList = new ArrayList<>();
                    String[] data = s.split("\\s+");
                    String dateTimeStr = data[0].trim() + " " + data[1].trim();
                    LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                    for (int i=2; i<data.length-2;i++){
                        AtmosphereEntity atmosphere = new AtmosphereEntity();
                        atmosphere.setDateTime(localDateTime);
                        atmosphere.setHeight(Double.valueOf(heightArr[i-2].replaceAll(",", ".").trim()));
                        atmosphere.setTemperature(Double.valueOf(data[i].replaceAll(",", ".").trim()));
                        atmosphereList.add(atmosphere);
                    }
                    persistenceService.saveAtmosphereData(atmosphereList);
                }
                catch (Exception e){
                    log.error(e.getMessage());
                }
            });
        }
        catch (Exception e){
            log.error(e.getMessage());
        }

    }


}

