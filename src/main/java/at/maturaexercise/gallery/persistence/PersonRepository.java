package at.maturaexercise.gallery.persistence;

import at.maturaexercise.gallery.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    boolean existsByUsername(String username);

    List<Person> findByLastNameLikeIgnoreCase(String lastNamePart);
}
