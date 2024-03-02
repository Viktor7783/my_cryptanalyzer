package services;

import exceptions.DecryptionException;
import exceptions.WrongNumberException;

import java.nio.file.Path;
import java.util.*;

import static constants.Constants.*;

public class CryptoModel {
    private FileService fileService;

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
        Map<String, Integer> exampleMaxValueMap = getSourceMaxValueMap(exampleList);
        if (isSuccessDecryptAndWrite(sourceList, exampleMaxValueMap, destination)) return;
        int key = 1;
        for (int i = 0; i < ALPHABET_RUS_ENG_REVERSE.size(); i++) {
            if (isSuccessDecryptAndWrite(cryptoCore(sourceList, key, ALPHABET_RUS_ENG_REVERSE), exampleMaxValueMap, destination)) {
                isDecrypt = true;
                break;
            }
            ++key;
        }
        if (!isDecrypt) try {
            throw new DecryptionException(String.format(DECRYPT_ERROR_MESSAGE, Path.of(source)));
        } catch (DecryptionException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private boolean isSuccessDecryptAndWrite(List<String> cryptoList, Map<String, Integer> exampleMaxValueMap, String destination) {
        Map<String, Integer> cryptoMaxValueMap = getSourceMaxValueMap(cryptoList);
        int count = 0;
        for (Map.Entry<String, Integer> examplePair : exampleMaxValueMap.entrySet()) {
            if (cryptoMaxValueMap.containsKey(examplePair.getKey())) ++count;
        }
        if (count > 1) {
            fileService.writeToFile(destination, cryptoList);
            return true;
        }
        return false;
    }

    private static Map<String, Integer> getSourceMaxValueMap(List<String> sourceList) {
        Map<String, Integer> sourceWordsMap = new HashMap<>();
        Map<String, Integer> sourceMaxValueMap = new HashMap<>();
        for (String textLine : sourceList) {
            String[] wordsArray = textLine.split(" ");
            for (String word : wordsArray) {
                Integer valueMap = sourceWordsMap.get(word.toLowerCase());
                sourceWordsMap.put(word.toLowerCase(), valueMap == null ? 1 : valueMap + 1);
            }
        }

        Comparator<Map.Entry<String, Integer>> comparator = Map.Entry.comparingByValue();
        Set<Map.Entry<String, Integer>> set = sourceWordsMap.entrySet();
        for (int i = 0; i < 3; i++) {
            Map.Entry<String, Integer> maxEntry = Collections.max(set, comparator);
            sourceMaxValueMap.put(maxEntry.getKey(), maxEntry.getValue());
            set.remove(maxEntry);
        }
        return sourceMaxValueMap;
    }

    public void statisticalDecrypt() {

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
            stringBuilder.append('\n');
            crypotList.add(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        }
        return crypotList;
    }
}