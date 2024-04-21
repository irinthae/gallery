package at.maturaexercise.gallery.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor

@Entity
@Table(name = "photographers")
public class Photographer extends User {

    @Embedded
    @Valid
    private Address studioAddress;

    @Embedded
    @Valid
    private PhoneNumber mobilePhoneNumber;

    @ElementCollection
    @JoinTable(name = "photographer_emails", foreignKey = @ForeignKey(name = "FK_photographer_emails_2_photographer"))
    @Column(name = "email", nullable = false, length = 64)
    @Setter(AccessLevel.PRIVATE)
    private Set<EmailAddress> emailAddresses;

    @Builder
    public Photographer(String username, String password, String firstName, String lastName, Address studioAddress, PhoneNumber mobilePhoneNumber, Set<EmailAddress> emailAddresses) {
        super(username, password, firstName, lastName);
        this.studioAddress = studioAddress;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.emailAddresses = (emailAddresses != null) ? new HashSet<>(emailAddresses) : new HashSet<>(3);
    }

    public Set<EmailAddress> getEmailAddresses() {
        return Collections.unmodifiableSet(emailAddresses);
    }

    public Photographer addEmails(EmailAddress... emailAddresses) {
        Arrays.stream(emailAddresses).forEach(this::addEmail);

        return this;
    }

    public Photographer addEmail(EmailAddress emailAddress) {
        emailAddresses.add(emailAddress);

        return this;
    }

    public Photographer removeEmails(EmailAddress... emailAddress) {
        Arrays.stream(emailAddress).forEach(this::removeEmail);

        return this;
    }

    public Photographer removeEmail(EmailAddress emailAddress) {
        emailAddresses.remove(emailAddress);

        return this;
    }

    public Photographer clearEmails() {
        emailAddresses.clear();

        return this;
    }
}
