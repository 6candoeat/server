package com._candoit.drfood.utils;

import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import java.io.*;
import java.util.Base64;

public class ImageUtil {

    public static File base64ToFile (String copyName, String base64) throws MimeTypeException, IOException {
        int colon = base64.indexOf(":");
        int semicolon = base64.indexOf(";");
        String mimeType = base64.substring(colon + 1, semicolon);
        String base64WithoutHeader = base64.substring(semicolon + 8);
        String extension = MimeTypes.getDefaultMimeTypes().forName(mimeType).getExtension();

        byte[] bytes = Base64.getDecoder().decode(base64WithoutHeader);
        copyName = copyName + extension;
        File file = new File("C:/photo/" + copyName);

        try (OutputStream outputStream = new BufferedOutputStream((new FileOutputStream(file)))) {
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }
}
