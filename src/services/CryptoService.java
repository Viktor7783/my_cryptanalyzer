package services;

import java.util.Scanner;

public class CryptoService {
    private Scanner scanner;
    private SelectTaskService selectTaskService;
    private FileService fileService;
    private CryptoModel cryptoModel;
    private ReadPathService readPathService;

    public CryptoService() {
        scanner = new Scanner(System.in);
        selectTaskService = new SelectTaskService(scanner);
        fileService = new FileService();
        cryptoModel = new CryptoModel(fileService);
        readPathService = new ReadPathService(scanner);
    }

    public void start() {
        selectTaskService.selectTask();
        if (selectTaskService.getTaskNumber() == 1) {
            //Вызов Cryptomodel (внутри Cryptomodel проверка на язык и выбор нужной константы с языком)
            readPathService.readPathForEncrypt();
            cryptoModel.encrypt(readPathService.source, readPathService.destination, readPathService.key, selectTaskService.getLanguageNumber());

        } else if (selectTaskService.getTaskNumber() == 2) {
            //Вызов Cryptomodel
        } else if (selectTaskService.getTaskNumber() == 3) {
            //Вызов Cryptomodel
        } else if (selectTaskService.getTaskNumber() == 4) {

        }
        scanner.close();
    }

}
