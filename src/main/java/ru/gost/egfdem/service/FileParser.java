package ru.gost.egfdem.service;

import java.io.File;
import java.io.InputStream;

public interface FileParser {
    void parseFile(InputStream file, String fileName);
}
