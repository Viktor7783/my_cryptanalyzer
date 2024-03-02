package services;

import constants.CryptoTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static constants.Constants.*;

public class CryptoService {
    private BufferedReader reader;
    private SelectTaskService selectTaskService;
    private FileService fileService;
    private CryptoModel cryptoModel;
    private ReadPathAndKeyService readPathAndKeyService;
    private FileValidationService fileValidationService;

    public CryptoService() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        selectTaskService = new SelectTaskService(reader);
        fileValidationService = new FileValidationService();
        fileService = new FileService(fileValidationService);
        cryptoModel = new CryptoModel(fileService);
        readPathAndKeyService = new ReadPathAndKeyService(reader);
    }

    public void start() {
        selectTaskService.selectTask();
        if (selectTaskService.getTaskNumber() == 1) {
            readPathAndKeyService.readPathFor(CryptoTask.ENCRYPT);
            cryptoModel.encrypt(readPathAndKeyService.getSource(), readPathAndKeyService.getDestination(), readPathAndKeyService.getKey());
        } else if (selectTaskService.getTaskNumber() == 2) {
            readPathAndKeyService.readPathFor(CryptoTask.DECRYPT);
            cryptoModel.decrypt(readPathAndKeyService.getSource(), readPathAndKeyService.getDestination(), readPathAndKeyService.getKey());
        } else if (selectTaskService.getTaskNumber() == 3) {
            readPathAndKeyService.readPathForExampleDecrypt();
            cryptoModel.bruteForceDecrypt(readPathAndKeyService.getSource(), readPathAndKeyService.getExample(), readPathAndKeyService.getDestination());
        } else if (selectTaskService.getTaskNumber() == 4) {
            readPathAndKeyService.readPathForExampleDecrypt();
            //Вызов Cryptomodel
        }

        try {
            reader.close();
        } catch (IOException e) {
            System.out.println(INPUT_ERROR);
            System.exit(0);
        }
        System.out.println(END_MESSAGE);
    }

}
