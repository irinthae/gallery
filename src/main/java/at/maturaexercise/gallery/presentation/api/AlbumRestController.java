package at.maturaexercise.gallery.presentation.api;

import at.maturaexercise.gallery.persistence.exception.DataQualityException;
import at.maturaexercise.gallery.presentation.api.dtos.AlbumDto;
import at.maturaexercise.gallery.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {

    private final AlbumService albumService;

    @GetMapping
    public HttpEntity<List<AlbumDto>> fetchAlbums(@RequestParam Optional<String> namePart) {
        return Optional.of(albumService.fetchAlbums(namePart))
                       .filter(albums -> !albums.isEmpty())
                       .map(albums -> albums.stream().map(AlbumDto::new).toList())
                       .map(albumDtos -> ResponseEntity.ok(albumDtos))
                       .orElse(ResponseEntity.noContent().build());
    }

    @ExceptionHandler(DataQualityException.class)
    public HttpEntity<Void> handleDataQualityException(DataQualityException dataQualityExceptionEx) {
        log.warn("A DataQualityException occurred because of: {}", dataQualityExceptionEx.getMessage());
        return ResponseEntity.internalServerError().build();
    }

}
