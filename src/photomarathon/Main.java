package photomarathon;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import photomarathon.gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        try {
            Files.createDirectories(FileSystems.getDefault().getPath("photos"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        MainFrame frame = new MainFrame();
    }
}
