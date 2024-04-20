package at.maturaexercise.gallery.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "persons")
public class Person extends User {

    private String nickname;

    @Builder
    public Person(String username, String password, String firstName, String lastName, String nickname) {
        super(username, password, firstName, lastName);
        this.nickname = nickname;
    }
}
