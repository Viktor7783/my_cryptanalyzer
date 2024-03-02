package services;

import exceptions.SmallFileException;

import static constants.Constants.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private FileValidationService fileValidationService;

    public FileService(FileValidationService fileValidationService) {
        this.fileValidationService = fileValidationService;
    }

    public List<String> readFromFile(String stringPath, boolean needToCheckFileSize) {
        Path sourcePath = Path.of(stringPath);
        fileValidationService.fileExistTest(sourcePath);
        ArrayList<String> readList = null;
        try {
            readList = new ArrayList<>(Files.readAllLines(sourcePath));
            if (needToCheckFileSize) fileValidationService.SmallFileTest(sourcePath, readList);
        } catch (IOException | SmallFileException e) {
            String message = e instanceof SmallFileException ? e.getMessage() : READ_FILE_ERROR + sourcePath.getFileName();
            System.out.println(message);
            System.exit(0);
        }
        return readList;
    }

    public void writeToFile(String stringDestinationPath, List<String> cryptoList) {
        Path destinationPath = Path.of(stringDestinationPath);
        fileValidationService.wrongFileTest(destinationPath);
        try {
            for (String textLine : cryptoList)
                Files.writeString(destinationPath, textLine, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println(WRITE_FILE_ERROR + destinationPath.getFileName());
            System.exit(0);
        }
    }
}
