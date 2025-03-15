package id.web.fitrarizki.blog.controller;

import id.web.fitrarizki.blog.dto.media.CreateMediaResponse;
import id.web.fitrarizki.blog.dto.media.GetMediaRequest;
import id.web.fitrarizki.blog.dto.media.GetMediaResponse;
import id.web.fitrarizki.blog.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/media")
@RequiredArgsConstructor
public class MediaAdminController {
    private final MediaService mediaService;

    @GetMapping
    public ResponseEntity<List<GetMediaResponse>> getListMedia(@RequestParam(required = false, defaultValue = "0") Integer pageNo, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        GetMediaRequest request = new GetMediaRequest();
        request.setPageNo(pageNo);
        request.setLimit(limit);

        return ResponseEntity.ok(mediaService.getAllMedia(request));
    }

    @PostMapping
    public ResponseEntity<CreateMediaResponse> createMedia(@RequestParam("file")MultipartFile file, @RequestParam String name) {
        return ResponseEntity.ok(mediaService.createMedia(file, name));
    }
}
