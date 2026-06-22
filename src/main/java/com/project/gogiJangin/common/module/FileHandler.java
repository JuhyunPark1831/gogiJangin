package com.project.gogiJangin.common.module;

import com.project.gogiJangin.repository.AttachFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileHandler {

    private final AttachFileRepository attachFileRepository;

    public static boolean fileCheck(String filePath, String fileOriName) {

        File file = new File(filePath + File.separator + fileOriName);

        return file.exists();
    }

    public static Long saveFiles(List<MultipartFile> fileList, String filePath) {

        try {
            for (MultipartFile file : fileList) {
                String savePath = filePath + File.separator + System.currentTimeMillis() + file.getOriginalFilename();
                File saveFile = new File(savePath);
                image.transferTo(file); 
            }

            return filePath;
        } catch (IOException e) {
            throw new CustomException(ErrorCode.FILE_SAVE_FAIL);
        }
    }
}
