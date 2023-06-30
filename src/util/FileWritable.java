package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class FileWritable {
    private static Path dataDirectory = Paths.get("./data");

    /**
     * @param id
     * @return
     */
    public static String getFilePath(String id) {
        return dataDirectory.toAbsolutePath() + "/" + id + ".data";
    }

    public static Path getDataDirectory() {
        return dataDirectory;
    }

    public static void ensureDirectoryExists() throws Exception {
        try {
            Files.createDirectory(dataDirectory);
        } catch (FileAlreadyExistsException e) {
            // Ignore
            return;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param id
     */
    public static void serialize(String id, Object obj) {
        String fileLocation = FileWritable.getFilePath(id);

        try {
            ensureDirectoryExists();
            FileOutputStream fileOutputStream = new FileOutputStream(fileLocation);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id
     * @return
     */
    public static Object deserialize(String id) {
        try (FileInputStream fileInputStream = new FileInputStream(FileWritable.getFilePath(id))) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}