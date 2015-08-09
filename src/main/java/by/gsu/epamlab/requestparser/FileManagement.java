package by.gsu.epamlab.requestparser;

import java.io.*;

public class FileManagement {
    public static boolean saveFile(UploadedFile file, String filePath) {
        StringBuilder path = new StringBuilder();
        path.append(filePath).append(File.separator).append(file.getFilename());

        FileOutputStream fileOutputStream = null;
        BufferedWriter writer = null;
        try {
            fileOutputStream = new FileOutputStream(new File(path.toString()));
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            for (byte[] arr : file.getContent()) {
                fileOutputStream.write(arr);
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteFile(String fileName, String filePath) {
        StringBuilder path = new StringBuilder();
        path.append(filePath).append(File.separator).append(fileName);
        new File(path.toString()).delete();
    }
}
