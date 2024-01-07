package com.task.decrypt.controller;

import com.task.decrypt.models.EncryptedFile;
import com.task.decrypt.repository.EncryptedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private EncryptedFileRepository encryptedFileRepository;

    @PostMapping("/encrypt")
    public ResponseEntity<String> encryptFile(@RequestParam("file") MultipartFile file) throws IOException {

        byte[] fileContent = file.getBytes();

        byte[] encryptedContent = encryptContent(fileContent, 5);

        EncryptedFile encryptedFile = new EncryptedFile();
        encryptedFile.setFileName(file.getOriginalFilename());
        encryptedFile.setEncryptedContent(encryptedContent);
        encryptedFileRepository.save(encryptedFile);

        return ResponseEntity.ok("File encrypted and saved to the database.");
    }

    @GetMapping("/decrypt/{fileId}")
    public ResponseEntity<byte[]> decryptFile(@PathVariable Long fileId) {
        Optional<EncryptedFile> encryptedFileOptional = encryptedFileRepository.findById(fileId);

        if (encryptedFileOptional.isPresent()) {
            EncryptedFile encryptedFile = encryptedFileOptional.get();

            byte[] decryptedContent = decryptContent(encryptedFile.getEncryptedContent(), 5);

            return ResponseEntity.ok(decryptedContent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private byte[] encryptContent(byte[] content, int shift) {
        byte[] encryptedContent = new byte[content.length];
        for (int i = 0; i < content.length; i++) {
            byte originalByte = content[i];
            byte encryptedByte = (byte) (originalByte + shift);
            encryptedContent[i] = encryptedByte;
        }
        return content;
    }

    private byte[] decryptContent(byte[] content, int shift) {
        byte[] decryptedContent = new byte[content.length];
        for (int i = 0; i < content.length; i++) {
            byte encryptedByte = content[i];
            byte originalByte = (byte) (encryptedByte - shift);
            decryptedContent[i] = originalByte;
        }
        return content;
    }
}
