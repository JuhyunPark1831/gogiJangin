package com.project.gogiJangin.controller;

import com.project.gogiJangin.common.module.FileHandler;
import com.project.gogiJangin.common.response.CustomResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    @Value("${file.upload-path}")
    private String path;

    private final FileHandler fileHandler;

    @PostMapping("/api/saveFiles.do")
    @ResponseBody
    public CustomResponseEntity<Long> saveFiles(@RequestParam("files") List<MultipartFile> files) {
        return CustomResponseEntity.success("파일 저장 성공", fileHandler.saveFiles(files, path));
    }

    @GetMapping("/api/getImageSource.do/{afId}")
    @ResponseBody
    public ResponseEntity<FileSystemResource> getImage(@PathVariable Long afId) {
        return fileHandler.getImageSource(afId);
    }

    @GetMapping("/api/downloadFile.do/{afId}")
    @ResponseBody
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable Long afId) {
        return fileHandler.downloadFile(afId);
    }

    @DeleteMapping("/api/deleteFile.do/{afId}")
    @ResponseBody
    public CustomResponseEntity<Object> deleteFile(@PathVariable Long afId) {
        fileHandler.deleteFile(afId);
        return CustomResponseEntity.success("파일이 삭제되었습니다.", null);
    }

    @DeleteMapping("/api/deleteAllFile.do/{afGroupId}")
    @ResponseBody
    public CustomResponseEntity<Object> deleteAllFile(@PathVariable Long afGroupId) {
        fileHandler.deleteAllFileByAfGroupId(afGroupId);
        return CustomResponseEntity.success("파일이 전부 삭제되었습니다.", null);
    }
}
