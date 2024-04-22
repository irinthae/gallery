package at.maturaexercise.gallery.presentation.api;

import at.maturaexercise.gallery.presentation.api.dtos.AlbumDto;
import at.maturaexercise.gallery.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {

    private final AlbumService albumService;

    @GetMapping
    public List<AlbumDto> fetchAlbums(@RequestParam Optional<String> namePart) {
        return albumService.fetchAlbums(namePart)
                           .stream()
                           .map(AlbumDto::new)
                           .toList();
    }

}
