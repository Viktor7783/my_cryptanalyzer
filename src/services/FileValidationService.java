package services;

import exceptions.SmallFileException;
import exceptions.WrongFileException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static constants.Constants.*;

public class FileValidationService {

    public void txtFileTest(Path path) {
        if (!path.getFileName().toString().endsWith(".txt")) {
            try {
                throw new WrongFileException(String.format(WRONG_FORMAT_ERROR_MESSAGE, path.getFileName()));
            } catch (WrongFileException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }

    public void fileExistTest(Path path) {
        if (Files.notExists(path)) {
            try {
                throw new WrongFileException(String.format(FILE_NOT_EXIST_ERROR, path.getFileName()));
            } catch (WrongFileException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }

    public void SmallFileTest(Path path, List<String> stringList) throws SmallFileException {
        if (stringList.isEmpty()) throw new SmallFileException(String.format(EMPTY_FILE_ERROR, path.getFileName()));
        long size = 0;
        for (String stringLine : stringList) {
            size += stringLine.length();
        }
        if (size < 1000) throw new SmallFileException(String.format(TOO_SMALL_FILE_ERROR, path.getFileName()));
    }

    public void wrongFileTest(Path path) {

        String os = System.getProperty("os.name").toLowerCase();
        String currentOs = "";

        if (os.contains("win")) currentOs = "Windows";
        else if (os.contains("nix") || os.contains("nux")) currentOs = "Linux";
        else if (os.contains("mac")) currentOs = "Mac OS";


        switch (currentOs) {
            case "Windows" -> fileTest(path, UNAVAILABLE_PATHS_FOR_WINDOWS);
            case "Linux" -> fileTest(path, UNAVAILABLE_PATHS_FOR_LINUX);
            case "Mac OS" -> fileTest(path, UNAVAILABLE_PATHS_FOR_MAC_OS);
        }
    }

    private void fileTest(Path testPath, List<String> wrongPathsList) {
        for (String stringPath : wrongPathsList) {
            if (testPath.toString().contains(stringPath)) {
                try {
                    throw new WrongFileException(String.format(CHANGE_FILE_ERROR, testPath.getFileName()));
                } catch (WrongFileException e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            }
        }
    }
}
