package at.maturaexercise.gallery.persistence;

import at.maturaexercise.gallery.domain.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Long> {
}
