package at.maturaexercise.gallery.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static at.maturaexercise.gallery.foundation.Guard.ensureNotNull;

@Data
@NoArgsConstructor

@Entity
@Table(name = "photographers")
public class Photographer extends User {
    public static final int LENGTH_EMAIL_ADDRESSES = 64;
    public static final int INITIAL_EMAIL_ADDRESSES_SIZE = 3;

    @Embedded
    @Valid
    private Address studioAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "countryCode", column = @Column(name = "mobile_country_code", nullable = false)),
            @AttributeOverride(name = "areaCode", column = @Column(name = "mobile_area_code", nullable = false)),
            @AttributeOverride(name = "serialNumber", column = @Column(name = "mobile_serial_number", length = PhoneNumber.LENGTH_SERIAL_NUMBER, nullable = false))
    })
    @NotNull
    @Valid
    private PhoneNumber mobilePhoneNumber;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "countryCode", column = @Column(name = "studio_country_code")),
            @AttributeOverride(name = "areaCode", column = @Column(name = "studio_area_code")),
            @AttributeOverride(name = "serialNumber", column = @Column(name = "studio_serial_number", length = PhoneNumber.LENGTH_SERIAL_NUMBER))
    })
    @Valid
    private PhoneNumber officePhoneNumber;


    @ElementCollection
    @JoinTable(name = "photographer_emails", foreignKey = @ForeignKey(name = "FK_photographer_emails_2_photographers"))
    @Column(name = "email", nullable = false, length = LENGTH_EMAIL_ADDRESSES)
    @Setter(AccessLevel.PRIVATE)
    private Set<EmailAddress> emailAddresses;

    @Builder
    public Photographer(EmailAddress username, String password, String firstName, String lastName, Address studioAddress, PhoneNumber mobilePhoneNumber, Set<EmailAddress> emailAddresses) {
        super(username, password, firstName, lastName);
        this.studioAddress = studioAddress;
        this.mobilePhoneNumber = ensureNotNull(mobilePhoneNumber);
        this.emailAddresses = (emailAddresses != null) ? new HashSet<>(emailAddresses) : new HashSet<>(INITIAL_EMAIL_ADDRESSES_SIZE);
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
