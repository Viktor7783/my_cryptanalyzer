package constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Constants {
    public static final List<Character> ALPHABET_RUS_ENG = new ArrayList<>(List.of('а', 'б', 'в',
            'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'я', 'a', 'b', 'c',
            'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z'));
    public static final List<Character> ALPHABET_RUS_ENG_REVERSE;

    static {
        List<Character> tempList = new ArrayList<>(ALPHABET_RUS_ENG);
        Collections.reverse(tempList);
        ALPHABET_RUS_ENG_REVERSE = tempList;
    }


    public static final List<String> UNAVAILABLE_PATHS_FOR_WINDOWS = List.of("System32", "SysWOW64", "AppData", "ProgramData", "Program Files");
    public static final List<String> UNAVAILABLE_PATHS_FOR_LINUX = List.of("bash_profile", "hosts", "/etc/", "/boot/", "/dev/", "/lib/", "/lib64/", "/opt/", "/proc/", "/root/", "/run/", "/sbin/", "/swap/", "/sys/", "/tmp/", "/usr/", "/var/", "/bin/");
    public static final List<String> UNAVAILABLE_PATHS_FOR_MAC_OS = List.of("System", "Applications", "Library");

    public static final String CHOOSE_A_TASK = """
            ======================================================================
            Выберите тип задачи:
            1 - Шифровка текста из заданного файла
            2 - Расшифровка при известном ключе
            3 - Расшифровка методом brute force
            4 - Расшифровка методом статистического анализа
            5 - EXIT
            ======================================================================
            """;
    public static final String ENCRYPT_SOURCE_MESSAGE = """
            ======================================================================
            Введите адрес файла c оригинальным текстом, который нужно зашифровать:
            <<< Для выхода из программы введите 'exit' >>>
            ======================================================================
            """;
    public static final String ENCRYPT_DESTINATION_MESSAGE = """
            ======================================================================
            Введите адрес файла в который нужно записать зашифрованный текст:
            <<< Для выхода из программы введите 'exit' >>>
            ======================================================================
            """;
    public static final String KEY_FOR_ENCRYPT_MESSAGE = """
            **********************************************************************
            Введите ключ для шифрования (Целое не отрицательное число):
            <<< Для выхода из программы введите 'exit' >>>
            **********************************************************************
            """;
    public static final String DECRYPT_SOURCE_MESSAGE = """
            ======================================================================
            Введите адрес зашифрованного файла, который нужно расшифровать:
            <<< Для выхода из программы введите 'exit' >>>
            ======================================================================
            """;
    public static final String DECRYPT_DESTINATION_MESSAGE = """
            ======================================================================
            Введите адрес файла в который нужно записать расшифрованный текст:
            <<< Для выхода из программы введите 'exit' >>>
            ======================================================================
            """;
    public static final String KEY_FOR_DECRYPT_MESSAGE = """
            **********************************************************************
            Введите ключ для расшифровки (Целое не отрицательное число):
            <<< Для выхода из программы введите 'exit' >>>
            **********************************************************************
            """;

    public static final String EXAMPLE_SOURCE_MESSAGE = """
            ======================================================================
            Введите адрес файла с примером зашифрованного текста:
            <<< Для выхода из программы введите 'exit' >>>
            ======================================================================
            """;

    public static final String LANGUAGE_NUMBER_ERROR = "Ошибка: неверное число для выбора языка!\nВведите число:\n 1 - Русский\n2 - Английский";
    public static final String KEY_NUMBER_ERROR = "Ошибка: Ключ не должен быть меньше нуля!";
    public static final String CHANGE_FILE_ERROR = "Воу-воу-воу, полегче, мамкин хацкер! Файл %s изменять нельзя!!!";
    public static final String TOO_SMALL_FILE_ERROR = "Файл %s слишком маленький для анализа!";
    public static final String EMPTY_FILE_ERROR = "Файл %s пустой!";
    public static final String FILE_NOT_EXIST_ERROR = "Ошибка: файла с именем %s не существует!";
    public static final String NUMBER_INPUT_ERROR = "Ошибка: введено неправильное число!";
    public static final String WRONG_NUMBER_ERROR = "Введите число от 1 до 5";
    public static final String INPUT_ERROR = "Ошибка ввода!";
    public static final String KEY_INPUT_ERROR = "Ошибка: ключ должен быть целым числом!";
    public static final String READ_FILE_ERROR = "Не удалось прочитать файл ";
    public static final String WRITE_FILE_ERROR = "Не удалось записать текст в файл ";
    public static final String DECRYPT_ERROR_MESSAGE = "Ошибка: не удалось расшифровать файл %s. Попробуйте заменить файл с примером текста.";


    public static final String GAME_OVER = """
            ######################################################################
            GAME OVER!
            ######################################################################
            """;
    public static final String END_MESSAGE = """
            $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            УРА! МЫ ВСЕХ ПОБЕДИЛИ!
            $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            """;
}
