package id.web.fitrarizki.blog.service;

import id.web.fitrarizki.blog.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;

@Service
public class FileSystemStorageService {

    public String save(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ApiException("File is empty", HttpStatus.BAD_REQUEST);
        }

        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append("web/www/files");
        pathBuilder.append("/");
        pathBuilder.append(Instant.now().getEpochSecond());
        pathBuilder.append("_");
        pathBuilder.append(file.getOriginalFilename());

        Path path = Paths.get(pathBuilder.toString());

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return pathBuilder.toString();
    }
}
