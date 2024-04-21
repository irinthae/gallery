package at.maturaexercise.gallery.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public class User extends AbstractPersistable<Long> {
    public static final int LENGTH_USERNAME = 64;
    public static final int LENGTH_FIRST_NAME = 64;
    public static final int LENGTH_LAST_NAME = 32;

    @Column(name = "username", length = LENGTH_USERNAME, nullable = false)
    @Email
    @NotBlank
    private String username;

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @Column(name = "firstName", length = LENGTH_FIRST_NAME, nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "lastName", length = LENGTH_LAST_NAME, nullable = false)
    @NotBlank
    private String lastName;

}
