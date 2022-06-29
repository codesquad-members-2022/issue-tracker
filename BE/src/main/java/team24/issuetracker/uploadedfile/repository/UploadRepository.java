package team24.issuetracker.uploadedfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team24.issuetracker.uploadedfile.domain.UploadedFile;

@Repository
public interface UploadRepository extends JpaRepository<UploadedFile, Long> {
}
