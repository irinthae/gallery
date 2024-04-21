package at.maturaexercise.gallery.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor

@Entity
@Table(name = "photographers")
public class Photographer extends User {

    @Embedded
    private Address studioAddress;

    @Embedded
    private PhoneNumber mobilePhoneNumber;

   /* @Embedded
    private PhoneNumber businessPhoneNumber;*/

    @ElementCollection
    @JoinTable(name = "photographer_emails")
    private Set<Email> emailAddresses;

    @Builder
    public Photographer(String username, String password, String firstName, String lastName, Address studioAddress, PhoneNumber mobilePhoneNumber, Set<Email> emailAddresses) {
        super(username, password, firstName, lastName);
        this.studioAddress = studioAddress;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.emailAddresses = (emailAddresses != null) ? new HashSet<>(emailAddresses) : new HashSet<>(3);
    }

    public Set<Email> getEmailAddresses() {
        return Collections.unmodifiableSet(emailAddresses);
    }
}
