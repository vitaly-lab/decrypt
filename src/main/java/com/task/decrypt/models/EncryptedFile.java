package com.task.decrypt.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "encrypted_files")
public class EncryptedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Lob
    @Column(name = "encrypted_content", length = 1000000)
    private byte[] encryptedContent;
}
