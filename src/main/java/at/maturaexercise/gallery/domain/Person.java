package at.maturaexercise.gallery.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@NoArgsConstructor

@Entity
@Table(name = "persons")
public class Person extends User {
    public static final int LENGTH_NICKNAME = 16;

    @Column(name = "nickName", length = LENGTH_NICKNAME, nullable = false)
    @NotBlank
    private String nickName;

    @Builder
    public Person(EmailAddress username, String password, String firstName, String lastName, String nickName) {
        super(username, password, firstName, lastName);
        this.nickName = nickName;
    }

    public static final Comparator<Person> byFirstName = Comparator.comparing(Person::getFirstName);
    public static final Comparator<Person> byLastName = Comparator.comparing(Person::getLastName);
    public static final Comparator<Person> byLastNameDesc = byLastName.reversed();
    public static final Comparator<Person> byName = byLastName.thenComparing(byFirstName);
}
