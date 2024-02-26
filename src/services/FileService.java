package services;

import exceptions.SmallFileException;
import exceptions.WrongFileException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private Path sourcePath;
    private Path examplePath;
    private Path destinationPath;


    public Path getSourcePath() {
        return sourcePath;
    }

    public Path getExamplePath() {
        return examplePath;
    }

    public Path getDestinationPath() {
        return destinationPath;
    }

    public void createPaths(String stringSource, String stringDestination) {
        sourcePath = Path.of(stringSource);
        if (Files.notExists(sourcePath)) {
            try {
                throw new WrongFileException("ОШИБКА: файла - источника с именем " + stringSource + " не существует!");
            } catch (WrongFileException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        destinationPath = Path.of(stringDestination);
        if (destinationPath.endsWith(Path.of(".bash_profile")) || destinationPath.endsWith(Path.of("hosts"))) {
            try {
                throw new WrongFileException("Воу-воу-воу, полегче, мамкин хацкер! Файл " + stringDestination + "изменять нельзя!!!");
            } catch (WrongFileException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }

    }

    public void createPaths(String stringSource, String example, String stringDestination) {
        this.createPaths(stringSource, stringDestination);
        examplePath = Path.of(example);
        if (Files.notExists(examplePath)) {
            try {
                throw new WrongFileException("ОШИБКА: файла - примера с именем " + example + " не существует!");
            } catch (WrongFileException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }

    public ArrayList<String> readFromFile(Path path) {
        ArrayList<String> readList = null;
        try {
            readList = new ArrayList<>(Files.readAllLines(path));
            if (path.equals(sourcePath) && readList.isEmpty()) {
                throw new SmallFileException("Файл " + path.getFileName() + " пустой!");
            } else if (path.equals(examplePath) && readList.size() < 5) {
                throw new SmallFileException("Файл " + path.getFileName() + " слишком маленький для анализа!");
            }
        } catch (IOException | SmallFileException e) {
            String message = e instanceof SmallFileException ? e.getMessage() : "Не удалось прочитать файл " + path.getFileName();
            System.out.println(message);
            System.exit(0);
        }
        return readList;
    }

    public void writeToFile(String text) {
        try {
            Files.writeString(destinationPath, text);
        } catch (IOException e) {
            System.out.println("Не удалось записать текст в файл " + destinationPath.getFileName());
            System.exit(0);
        }
    }
}
