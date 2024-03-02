import services.CryptoService;

import static constants.Constants.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        new CryptoService().start();

    }
}