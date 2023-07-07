package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;

public class FileAccessors {
    /**
     * @param folderPathName
     * @return
     */
    public static File[] getFiles(String folderPathName) {
        File folder = new File(folderPathName);
        return folder.listFiles();
    }

    /**
     * @param id The ID representing the filename of the object stored on disk
     * @return returns `Object` if file was found, otherwise `null`
     * @throws Exception if it can not ensure that the data directory exists.
     */
    public static Object getObjectById(String id) throws Exception {
        Path dataDirectory = FileWritable.getDataDirectory();
        FileWritable.ensureDirectoryExists();
        String absolutePath = dataDirectory.toAbsolutePath().toString();
        File[] files = FileAccessors.getFiles(absolutePath);

        for (File file : files) {
            if (file.getName().equals(id + ".data")) {
                return FileAccessors.parse(file);
            }
        }

        return null;
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