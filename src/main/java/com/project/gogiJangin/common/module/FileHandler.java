package com.project.gogiJangin.common.module;

import com.project.gogiJangin.common.exception.CustomException;
import com.project.gogiJangin.common.exception.ErrorCode;
import com.project.gogiJangin.entity.AttachFile;
import com.project.gogiJangin.repository.AttachFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileHandler {

    private final AttachFileRepository attachFileRepository;

    public boolean fileCheck(String filePath, String fileOriName) {

        File file = new File(filePath + File.separator + fileOriName);

        return file.exists();
    }

    @Transactional
    public Long saveFiles(List<MultipartFile> fileList, String filePath) {

        Long newAfGroupId = attachFileRepository.findMaxAfGroupId();

        try {

            for (MultipartFile file : fileList) {

                String savePath = filePath + File.separator + System.currentTimeMillis() + file.getOriginalFilename();
                File saveFile = new File(savePath);
                file.transferTo(saveFile);

                attachFileRepository.save(AttachFile.builder()
                                .afGroupId(newAfGroupId)
                                .afFileOriName(file.getOriginalFilename())
                                .afFilePath(savePath)
                        .build());
            }

        } catch (IOException e) {
            throw new CustomException(ErrorCode.FAILED_SAVE_FILE);
        }

        return newAfGroupId;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<FileSystemResource> getImageSource(Long afId) {

        AttachFile attachFile = attachFileRepository.findById(afId).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_ATTACH_FILE));

        File file = new File(attachFile.getAfFilePath());

        if (!file.exists()) {
            throw new CustomException(ErrorCode.NOT_FOUND_ATTACH_FILE);
        }

        FileSystemResource resource = new FileSystemResource(file);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "image/png");
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<FileSystemResource> downloadFile(Long afId) {

        AttachFile attachFile = attachFileRepository.findById(afId).orElseThrow(() ->
                        new CustomException(ErrorCode.NOT_FOUND_ATTACH_FILE));

        File file = new File(attachFile.getAfFilePath());

        if (!file.exists()) {
            throw new CustomException(ErrorCode.NOT_FOUND_ATTACH_FILE);
        }

        FileSystemResource resource = new FileSystemResource(file);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentDisposition(
                ContentDisposition.builder("attachment")
                        .filename(attachFile.getAfFileOriName(), StandardCharsets.UTF_8)
                        .build()
        );

        headers.setContentLength(file.length());

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @Transactional
    public void deleteFile(Long afId) {

        AttachFile attachFile = attachFileRepository.findById(afId)
                .orElseThrow(() ->
                        new CustomException(ErrorCode.NOT_FOUND_ATTACH_FILE));

        File file = new File(attachFile.getAfFilePath());

        if (!file.exists()) {
            throw new CustomException(ErrorCode.NOT_FOUND_ATTACH_FILE);
        }

        attachFile.softDelete();

        boolean deleted = file.delete();

        if (!deleted) {
            throw new CustomException(ErrorCode.FAILED_DELETE_FILE);
        }
    }

    @Transactional
    public void deleteAllFileByAfGroupId(Long afGroupId) {

        List<AttachFile> attachFileList = attachFileRepository.findAllByAfGroupId(afGroupId);

        for (AttachFile attachFile : attachFileList) {
            File file = new File(attachFile.getAfFilePath());

            if (!file.exists()) {
                throw new CustomException(ErrorCode.NOT_FOUND_ATTACH_FILE);
            }

            attachFile.softDelete();

            boolean deleted = file.delete();

            if (!deleted) {
                throw new CustomException(ErrorCode.FAILED_DELETE_FILE);
            }
        }
    }
}
