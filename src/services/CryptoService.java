package services;

import java.util.Scanner;

public class CryptoService {
    private Scanner scanner;
    private SelectTaskService selectTaskService;

    public CryptoService() {
        scanner = new Scanner(System.in);
        selectTaskService = new SelectTaskService(scanner);
    }

    public void start() {
        selectTaskService.selectTask();
        if (selectTaskService.getTaskNumber() == 1) {
         //Вызов Cryptomodel (внутри Cryptomodel проверка на язык и выбор нужной константы с языком)
        } else if (selectTaskService.getTaskNumber() == 2) {
         //Вызов Cryptomodel
        } else if (selectTaskService.getTaskNumber() == 3) {
          //Вызов Cryptomodel
        } else if (selectTaskService.getTaskNumber() == 4) {

        }
    }
}
