package at.maturaexercise.gallery.service;

import at.maturaexercise.gallery.domain.Album;
import at.maturaexercise.gallery.persistence.AlbumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class AlbumService {

    private final AlbumRepository albumRepository;

    public List<Album> fetchAlbums(Optional<String> namePart) {
        log.info("starting to fetch data for AlbumService.fetchAlbums");
        return namePart.map(albumRepository::findAllByNameContainingIgnoreCase)
                       .orElseGet(albumRepository::findAll);
    }
}
