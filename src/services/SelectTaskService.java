package services;

import exceptions.WrongNumberException;

import java.io.BufferedReader;
import java.io.IOException;

import static constants.Constants.*;

public class SelectTaskService {
    private final BufferedReader reader;
    private int taskNumber;

    public SelectTaskService(BufferedReader reader) {
        this.reader = reader;
    }

    public void selectTask() {
        System.out.println(CHOOSE_A_TASK);
        taskNumber = consoleReader();
        numberTest(taskNumber);
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    private int consoleReader() {
        int number = 0;
        try {
            number = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println(NUMBER_INPUT_ERROR);
            System.exit(0);
        }
        return number;
    }

    private void numberTest(int number) {
        if (number == 5) {
            System.out.println(GAME_OVER);
            System.exit(0);
        } else if (number < 1 || number > 5) {
            try {
                throw new WrongNumberException(WRONG_NUMBER_ERROR);
            } catch (WrongNumberException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }
}
