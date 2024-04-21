package at.maturaexercise.gallery.persistence.converter;

import at.maturaexercise.gallery.domain.EmailAddress;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<EmailAddress, String> {
    @Override
    public String convertToDatabaseColumn(EmailAddress emailAddress) {
        return Optional.ofNullable(emailAddress).map(EmailAddress::value).orElse(null);
    }

    @Override
    public EmailAddress convertToEntityAttribute(String dbValue) {
        return Optional.ofNullable(dbValue).map(EmailAddress::new).orElse(null);
    }
}
