package id.web.fitrarizki.blog.service;

import id.web.fitrarizki.blog.dto.media.CreateMediaResponse;
import id.web.fitrarizki.blog.dto.media.GetMediaRequest;
import id.web.fitrarizki.blog.dto.media.GetMediaResponse;
import id.web.fitrarizki.blog.entity.Media;
import id.web.fitrarizki.blog.mapper.MediaMapper;
import id.web.fitrarizki.blog.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;
    private final FileSystemStorageService fileSystemStorageService;
    private final GoogleCloudStorageService googleCloudStorageService;

    public List<GetMediaResponse> getAllMedia(GetMediaRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPageNo(), request.getLimit());
        return mediaRepository.findAll(pageRequest).getContent().stream().map(MediaMapper.INSTANCE::mapToGetMediaResponse).collect(Collectors.toList());
    }

    public CreateMediaResponse createMedia(MultipartFile file, String name) {
        String path = googleCloudStorageService.save(file);

        Media media = new Media();
        media.setName(name);
        media.setPath(path);
        media.setCreatedAt(Instant.now().getEpochSecond());
        mediaRepository.save(media);

        return CreateMediaResponse.builder().path(path).build();
    }
}
