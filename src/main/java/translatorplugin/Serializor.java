/*
 * Copyright (c) 2016 Bull/Atos.
 * All rights reserved.
 */
package translatorplugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.plexus.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * [Enter type description here].
 *
 * @author Bull/Atos
 */
public class Serializor {
    final static Logger LOGGER = LoggerFactory.getLogger(Serializor.class);

    public void processFile(Path folderPath) {
        String fileName = "translators.txt";
        Path translatorsFile = folderPath.resolve(fileName);
        if (translatorsFile.toFile().exists()) {
            parseFile(folderPath, translatorsFile);
        }
    }

    private void parseFile(Path folderPath, Path filePath) {
        try {
            String content = FileUtils.fileRead(filePath.toFile());
            LOGGER.debug("File content:\n" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String regExpression = "(.*),(.*),(.*),(.*);";
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            Pattern pattern = Pattern.compile(regExpression);
            Matcher matcher = pattern.matcher(line);

            stringBuilder.append("[");
            while (matcher.find()) {
                LOGGER.debug("new User Translator Definition {} {} {} {}", matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
                //[{ "lang1" :"klingon", "lang2" :" chom", "lang1Content" :" human", "lang2Content" :" purr"}]
                String s=
                        "{\"lang1\" : \"" + matcher.group(1) + "\"," +
                        "\"lang2\" : \"" + matcher.group(2) + "\"," +
                        "\"lang1Content\" : \"" + matcher.group(3) + "\"," +
                        "\"lang2Content\" : \"" + matcher.group(4) + "\"}"
                        ;

                stringBuilder.append(s);
            }

            stringBuilder.append("]");

            ObjectMapper objectMapper = new ObjectMapper();
            LOGGER.warn(stringBuilder.toString());
            try {
                File jsonFile = new File(folderPath + "/translators.json");
                objectMapper.writeValue(jsonFile, stringBuilder.toString());
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
