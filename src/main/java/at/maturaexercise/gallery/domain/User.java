package at.maturaexercise.gallery.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public class User extends AbstractPersistable<Long> {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

}
