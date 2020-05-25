package edu.mangement.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/25/2020
 * TIME : 11:45 PM
 */
public class FileProcessUtils {
    private static File getFolderUpload() {
        var folderUpload = new File("C:/upload");
        if (!folderUpload.exists()) {
            folderUpload.mkdir();
        }
        return folderUpload;
    }

    public static String processUploadFile(MultipartFile multipartFile) {
        var fileName = multipartFile.getOriginalFilename();
        try {
            var fileExtension = FilenameUtils.getExtension(fileName);
            fileName = System.currentTimeMillis() + "." + fileExtension;
            var file = new File(FileProcessUtils.getFolderUpload(), fileName);
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
