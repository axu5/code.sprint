package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class FileAccessors {
    /**
     * @param folderPathName
     * @return
     */
    public static File[] getFiles(String folderPathName) {
        File folder = new File(folderPathName);
        return folder.listFiles();
    }

    public static Object parse(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Object obj = objectInputStream.readObject();

            fileInputStream.close();
            objectInputStream.close();

            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}