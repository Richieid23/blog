package id.web.fitrarizki.blog.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import id.web.fitrarizki.blog.config.SecretPropertiesConfig;
import id.web.fitrarizki.blog.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class GoogleCloudStorageService {
    private final SecretPropertiesConfig config;

    public String save(MultipartFile file) {
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append("web/www/files");
        pathBuilder.append("/");
        pathBuilder.append(Instant.now().getEpochSecond());
        pathBuilder.append("_");
        pathBuilder.append(file.getOriginalFilename());

        Storage storage = StorageOptions.newBuilder().setProjectId(config.getGoogle().getProjectId()).build().getService();
        BlobId blobId = BlobId.of("fc-blog-cyberboy-bucket", pathBuilder.toString());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        try {
            storage.create(blobInfo, file.getBytes());
        } catch (IOException e) {
            throw new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return pathBuilder.toString();
    }
}
