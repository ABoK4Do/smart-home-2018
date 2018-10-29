package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSmartHomeLoader implements SmartHomeLoader {
    private String fileName;

    FileSmartHomeLoader() {
        fileName = "smart-home-1.js";
    }

    FileSmartHomeLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SmartHome loadSmartHome() throws IOException {
        // считываем состояние дома из файла
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(fileName)));
        return gson.fromJson(json, SmartHome.class);
    }
}
