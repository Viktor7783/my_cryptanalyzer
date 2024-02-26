package services;

import java.util.Scanner;

public class ReadPathService {
    private Scanner scanner;
    String source;
    String destination;
    String example;
    String stringKey;
    int key;

    public ReadPathService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void readPathForEncrypt() {
        System.out.println("Для выхода из программы введите 'exit'\n");
        System.out.println("Введите адрес файла c оригинальным текстом, который нужно зашифровать:");
        source = scanner.nextLine();
        System.out.println("Source после ввода = " + source); // delete!!!
        if (source.equalsIgnoreCase("exit")) {
            System.out.println("GAME OVER!");
            System.exit(0);
        }
        System.out.println("Введите адрес файла в который нужно записать зашифрованный текст: ");
        destination = scanner.nextLine();
        if (destination.equalsIgnoreCase("exit")) {
            System.out.println("GAME OVER!");
            System.exit(0);
        }
        System.out.println("Введите ключ для шифрования (Целое не отрицательное число):");
        stringKey = scanner.nextLine();
        if (stringKey.equalsIgnoreCase("exit")) {
            System.out.println("GAME OVER!");
            System.exit(0);
        }
        try {
            key = Integer.parseInt(stringKey);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ключ должен быть целым числом!");
        }
    }

    public void readPathForDecrypt() {

    }

    public void readPathForExampleDecrypt() {

    }

}
