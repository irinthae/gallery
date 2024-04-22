package at.maturaexercise.gallery.service;

import at.maturaexercise.gallery.domain.Album;
import at.maturaexercise.gallery.persistence.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
@Transactional
public class AlbumService {

    private final AlbumRepository albumRepository;

    public List<Album> fetchAlbums(Optional<String> namePart) {
        return namePart.map(albumRepository::findAllByNameContainingIgnoreCase)
                       .orElseGet(albumRepository::findAll);
    }
}
