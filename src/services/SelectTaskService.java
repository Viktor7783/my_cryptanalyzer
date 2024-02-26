package services;

import exceptions.WrongNumberException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static constants.Constants.*;

public class SelectTaskService {
    private Scanner scanner;
    private int taskNumber;
    private int languageNumber;

    public SelectTaskService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void selectTask() {
        System.out.println(CHOOSE_A_TASK);
        try {
            taskNumber = scanner.nextInt();
        } catch (NoSuchElementException e) {
            System.out.println("Введено неправильное число, попробуй снова!");
            selectTask();
            return;
        }
        if (taskNumber == 5) {
            System.out.println("GAME OVER!");
            System.exit(0);
        } else if (taskNumber < 1 || taskNumber > 5) {
            try {
                throw new WrongNumberException("Введите число от 1 до 5");
            } catch (WrongNumberException e) {
                System.out.println(e.getMessage());
                selectTask();
                return;
            }
        }
        System.out.println(CHOOSE_A_LANGUAGE);
        try {
            languageNumber = scanner.nextInt();
        } catch (NoSuchElementException e) {
            System.out.println("Введено неправильное число, попробуй снова!");
            selectTask();
            return;
        }
        if (languageNumber == 3) {
            System.out.println("GAME OVER!");
            System.exit(0);
        } else if (languageNumber < 1 || languageNumber > 3) {
            try {
                throw new WrongNumberException("Введите число от 1 до 3");
            } catch (WrongNumberException e) {
                System.out.println(e.getMessage());
                selectTask();
            }
        }
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public int getLanguageNumber() {
        return languageNumber;
    }
}
