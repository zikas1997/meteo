package ru.gost.egfdem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import ru.gost.egfdem.service.FileParser;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/meteo")
@Slf4j
public class MeteorologyController {

    @Autowired
    @Qualifier("atmosphereParser")
    private FileParser atmosphereParser;

    @Autowired
    @Qualifier("radiationParser")
    private FileParser radiationParser;

    @Autowired
    @Qualifier("ostankinoParser")
    private FileParser ostankinoParser;

    @GetMapping
    public String init() {
        return "LoaderPage";
    }

    /**
     * Загрузка файла
     *
     * @param file файл
     */
    @PostMapping("/atmosphere/save")
    public ModelAndView atmosphereSave(@RequestParam(value = "atmosphereFile", required = false) MultipartFile file) {
        ModelAndView result = new ModelAndView();
        result.setView(new MappingJackson2JsonView());
        boolean uploaded = false;
        try {
            uploaded = true;
            atmosphereParser.parseFile(file.getInputStream(), file.getOriginalFilename());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (uploaded) {
            result.addObject("success", "Файл " + file.getOriginalFilename() + " успешно добавлен");
        } else result.addObject("error", "Не удалось загрузить файл");
        return result;
    }

    @PostMapping("/radiation/save")
    public ModelAndView radiationSave(@RequestParam(value = "radiationFile", required = false) MultipartFile file) {
        ModelAndView result = new ModelAndView();
        result.setView(new MappingJackson2JsonView());
        boolean uploaded = false;
        try {
            uploaded = true;
            radiationParser.parseFile(file.getInputStream(), file.getOriginalFilename());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (uploaded) {
            result.addObject("success", "Файл " + file.getOriginalFilename() + " успешно добавлен");
        } else result.addObject("error", "Не удалось загрузить файл");
        return result;
    }

    @PostMapping("/ostankino/save")
    public ModelAndView ostankinoSave(@RequestParam(value = "ostankinoFile", required = false) MultipartFile file) {
        ModelAndView result = new ModelAndView();
        result.setView(new MappingJackson2JsonView());
        boolean uploaded = false;
        try {
            uploaded = true;
            ostankinoParser.parseFile(file.getInputStream(), file.getOriginalFilename());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (uploaded) {
            result.addObject("success", "Файл " + file.getOriginalFilename() + " успешно добавлен");
        } else result.addObject("error", "Не удалось загрузить файл");
        return result;
    }
}
