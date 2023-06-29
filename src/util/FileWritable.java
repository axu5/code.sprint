package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class FileWritable {
    /**
     * @param id
     * @return
     */
    public static String getFilePath(String id) {
        return "data\\" + id + ".data";
    }

    /**
     * @param id
     */
    public void serialize(String id) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FileWritable.getFilePath(id))) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
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
