package Test;

import java.io.File;

public class FileRename {
    public static void main(String[] args) {
        File picture = new File("src/com/zyproject/picture");
        File[] files = picture.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];

            String newName = String.format("%d.png", i + 1);
            File newFile = new File(file.getParent(), newName);

            boolean success = file.renameTo(newFile);

            if (success) {
                System.out.println("File was rename successfully：" + file.getName() + "——>" + newFile.getName());
            } else {
                System.out.println("File could not be renamed" + file.getName());
            }
        }
    }

}
