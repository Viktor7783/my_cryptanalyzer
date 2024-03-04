package services;

import exceptions.DecryptionException;
import exceptions.WrongNumberException;

import java.nio.file.Path;
import java.util.*;

import static constants.Constants.*;

public class CryptoModel {
    private final FileService fileService;

    public CryptoModel(FileService fileService) {
        this.fileService = fileService;
    }

    public void encrypt(String source, String destination, int key) {
        List<String> sourceList = fileService.readFromFile(source, false);
        List<String> cryptoList = cryptoCore(sourceList, key, ALPHABET_RUS_ENG);
        fileService.writeToFile(destination, cryptoList);
    }

    public void decrypt(String source, String destination, int key) {
        List<String> sourceList = fileService.readFromFile(source, false);
        List<String> cryptoList = cryptoCore(sourceList, key, ALPHABET_RUS_ENG_REVERSE);
        fileService.writeToFile(destination, cryptoList);
    }

    public void bruteForceDecrypt(String source, String example, String destination) {
        boolean isDecrypt = false;
        List<String> sourceList = fileService.readFromFile(source, true);
        List<String> exampleList = fileService.readFromFile(example, true);
        List<String> exampleMaxValueList = getSourceMaxValueList(exampleList);
        List<String> cryptoSourceList = new ArrayList<>();

        for (int i = 0, key = 0; i < ALPHABET_RUS_ENG_REVERSE.size(); i++, key++) {
            List<String> cryptoMaxValueList = getSourceMaxValueList(cryptoSourceList = cryptoCore(sourceList, key, ALPHABET_RUS_ENG_REVERSE));
            if (new HashSet<>(exampleMaxValueList).containsAll(cryptoMaxValueList)) {
                isDecrypt = true;
                break;
            }
        }
        if (isDecrypt) fileService.writeToFile(destination, cryptoSourceList);
        else {
            try {
                throw new DecryptionException(String.format(DECRYPT_ERROR_MESSAGE, Path.of(source).getFileName()));
            } catch (DecryptionException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }


    public void statisticalDecrypt(String source, String example, String destination) {
        boolean isDecrypt = false;
        List<String> sourceList = fileService.readFromFile(source, true);
        List<String> exampleList = fileService.readFromFile(example, true);

        List<String> sourceMaxList = getSourceMaxValueList(sourceList);
        List<String> exampleMaxList = getSourceMaxValueList(exampleList);
        int key = 0;
        for (int x = 0; x < ALPHABET_RUS_ENG_REVERSE.size(); x++, key++) {
            List<String> cryptoMaxList = cryptoCore(sourceMaxList, key, ALPHABET_RUS_ENG_REVERSE);
            if (new HashSet<>(exampleMaxList).containsAll(cryptoMaxList)) {
                isDecrypt = true;
                break;
            }
        }
        if (isDecrypt) fileService.writeToFile(destination, cryptoCore(sourceList, key, ALPHABET_RUS_ENG_REVERSE));
        else {
            try {
                throw new DecryptionException(String.format(DECRYPT_ERROR_MESSAGE, Path.of(source).getFileName()));
            } catch (DecryptionException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }

    private static List<String> getSourceMaxValueList(List<String> sourceList) {
        Map<String, Integer> sourceWordsMap = new HashMap<>();
        List<String> sourceMaxValueList = new ArrayList<>();

        for (String textLine : sourceList) {
            String[] wordsArray = textLine.split(" ");
            for (String word : wordsArray) {
                boolean isAlphabetWord = true;
                for (char c : word.toCharArray()) {
                    if (!ALPHABET_RUS_ENG_REVERSE.contains(c)) {
                        isAlphabetWord = false;
                        break;
                    }
                }
                if (isAlphabetWord) {
                    Integer valueMap = sourceWordsMap.get(word.toLowerCase());
                    sourceWordsMap.put(word.toLowerCase(), valueMap == null ? 1 : valueMap + 1);
                }

            }
        }

        Comparator<Map.Entry<String, Integer>> comparator = Map.Entry.comparingByValue();
        Set<Map.Entry<String, Integer>> set = new HashSet<>(sourceWordsMap.entrySet());
        for (int i = 0; i < 3; i++) {
            Map.Entry<String, Integer> maxEntry = Collections.max(set, comparator);
            sourceMaxValueList.add(maxEntry.getKey());
            set.remove(maxEntry);
        }
        return sourceMaxValueList;
    }

    private List<String> cryptoCore(List<String> sourceList, int key, List<Character> alphabet) {
        if (key < 0) {
            try {
                throw new WrongNumberException(KEY_NUMBER_ERROR);
            } catch (WrongNumberException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }

        List<String> crypotList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (String textLine : sourceList) {
            for (int x = 0; x < textLine.length(); x++) {
                char textChar = textLine.charAt(x);
                boolean isUppercase = Character.isUpperCase(textChar);
                if (alphabet.contains(Character.toLowerCase(textChar))) {
                    char newChar = alphabet.get((alphabet.indexOf(Character.toLowerCase(textChar)) + key) % alphabet.size());
                    stringBuilder.append(isUppercase ? Character.toUpperCase(newChar) : newChar);
                } else stringBuilder.append(textLine.charAt(x));
            }
            crypotList.add(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        }
        return crypotList;
    }
}