import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        try {
            System.out.println(Files.writeString(Path.of("/home/kaban/IdeaProjects/KabanFirst/file11.txt"), "Testing how how hoooww"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}