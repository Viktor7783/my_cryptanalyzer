package services;

import exceptions.WrongNumberException;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.ALPHABET_ENG;
import static constants.Constants.ALPHABET_RUS;

public class CryptoModel {
    private FileService fileService;

    public CryptoModel(FileService fileService) {
        this.fileService = fileService;
    }

    public void encrypt(String source, String destination, int key, int languageNumber) {
        keyTest(key);
        languageTest(languageNumber);
        List<Character> AlphaBet = languageNumber == 1 ? ALPHABET_RUS : ALPHABET_ENG;
        fileService.createPaths(source, destination);
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> sourceList = fileService.readFromFile(fileService.getSourcePath());
        for (String textLine : sourceList) {
            for (int x = 0; x < textLine.length(); x++) {
                if (AlphaBet.contains(textLine.charAt(x))) {
                    stringBuilder.append(AlphaBet.get((AlphaBet.indexOf(textLine.charAt(x)) + key) % AlphaBet.size()));
                } else stringBuilder.append(textLine.charAt(x));
            }
        }
        fileService.writeToFile(String.valueOf(stringBuilder));
    }

    public void decrypt(String source, String destination, int key, int languageNumber) {
        keyTest(key);
        languageTest(languageNumber);
        List<Character> AlphaBet = languageNumber == 1 ? ALPHABET_RUS : ALPHABET_ENG;
        fileService.createPaths(source, destination);
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> sourceList = fileService.readFromFile(fileService.getSourcePath());
        for (String textLine : sourceList) {
            for (int x = 0; x < textLine.length(); x++) {
                if (AlphaBet.contains(textLine.charAt(x))) {
                    stringBuilder.append(AlphaBet.get((AlphaBet.indexOf(textLine.charAt(x)) - key) % AlphaBet.size()));
                } else stringBuilder.append(textLine.charAt(x));
            }
        }
        fileService.writeToFile(String.valueOf(stringBuilder));
    }

    public void bruteForceDecrypt(String source, String example, String destination) {

    }

    public void statisticalDecrypt() {

    }

    private void languageTest(int languageNumber) {
        if (languageNumber < 1 || languageNumber > 2) {
            try {
                throw new WrongNumberException("Ошибка: неверное число для выбора языка!\nВведите число:\n 1 - Русский\n2 - Английский");
            } catch (WrongNumberException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }

    private void keyTest(int key) {
        if (key < 0) {
            try {
                throw new WrongNumberException("Ошибка: Ключ не должен быть меньше нуля!");
            } catch (WrongNumberException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }
}
