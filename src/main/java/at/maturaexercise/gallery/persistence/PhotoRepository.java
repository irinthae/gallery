package at.maturaexercise.gallery.persistence;

import at.maturaexercise.gallery.domain.Photo;
import at.maturaexercise.gallery.domain.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findAllByPhotographerAndCreationTimeStampBetween(Photographer photographer, LocalDateTime startTS, LocalDateTime endTS);

    Long countByPhotographer(Photographer photographer);
}
