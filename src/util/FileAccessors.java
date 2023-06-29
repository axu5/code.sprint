package util;

import java.io.File;

public class FileAccessors {
    /**
     * @param folderPathName
     * @return
     */
    public static File[] getFiles(String folderPathName) {
        File folder = new File(folderPathName);
        return folder.listFiles();
    }
}