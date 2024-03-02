package services;

import constants.CryptoTask;

import java.io.BufferedReader;
import java.io.IOException;

import static constants.Constants.*;

public class ReadPathAndKeyService {
    private BufferedReader reader;
    private String source;
    private String destination;
    private String example;
    private String stringKey;
    private int key;

    public ReadPathAndKeyService(BufferedReader reader) {
        this.reader = reader;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getExample() {
        return example;
    }

    public int getKey() {
        return key;
    }

    public void readPathFor(CryptoTask cryptoTask) {
        System.out.println(cryptoTask.getSourceMessage());
        source = consoleReader();
        exitTest(source);
        System.out.println(cryptoTask.getDestinationMessage());
        destination = consoleReader();
        exitTest(destination);
        System.out.println(cryptoTask.getKeyMessage());
        stringKey = consoleReader();
        exitTest(stringKey);
        key = parseKey();
    }

    public void readPathForExampleDecrypt() {
        readPathFor(CryptoTask.DECRYPT);
        System.out.println(EXAMPLE_SOURCE_MESSAGE);
        example = consoleReader();
        exitTest(example);
    }

    private void exitTest(String text) {
        if (text.equalsIgnoreCase("exit")) {
            System.out.println(GAME_OVER);
            System.exit(0);
        }
    }

    private String consoleReader() {
        String path = "";
        try {
            path = reader.readLine();
        } catch (IOException e) {
            System.out.println(INPUT_ERROR);
            System.exit(0);
        }
        return path;
    }

    private int parseKey() {
        int key = -1;
        try {
            key = Integer.parseInt(stringKey);
        } catch (NumberFormatException e) {
            System.out.println(KEY_INPUT_ERROR);
            System.exit(0);
        }
        return key;
    }

}
