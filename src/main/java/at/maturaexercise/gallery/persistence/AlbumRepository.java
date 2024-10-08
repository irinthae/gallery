package at.maturaexercise.gallery.persistence;

import at.maturaexercise.gallery.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findAllByNameContainingIgnoreCase(String name);
}
