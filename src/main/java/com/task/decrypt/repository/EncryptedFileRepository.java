package com.task.decrypt.repository;

import com.task.decrypt.models.EncryptedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncryptedFileRepository extends JpaRepository<EncryptedFile, Long> {
}

