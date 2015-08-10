package by.gsu.epamlab.requestparser;

import java.io.*;

public abstract class FileManagement {
    public static boolean saveFile(UploadedFile file, String filePath) {
        String path = concatPath(filePath, file.getFilename());
        FileOutputStream fileOutputStream = null;
        BufferedWriter writer = null;
        try {
            if (!new File(filePath).exists()) {
                new File(filePath).mkdir();
            }
            fileOutputStream = new FileOutputStream(new File(path));
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
        String path = concatPath(filePath, fileName);
        new File(path).delete();
    }

    public static String concatPath(String... args) {
        StringBuilder builder = new StringBuilder();
        for (String s : args) {
            builder.append(s).append(File.separator);
        }
        builder.delete(builder.lastIndexOf(File.separator), builder.length());
        return builder.toString();
    }
}
