import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TextFileReaderTest {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("src/uaTEXT.txt"), StandardCharsets.UTF_8);

        while (scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
    }
}
