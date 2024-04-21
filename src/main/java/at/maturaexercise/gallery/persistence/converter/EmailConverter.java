package at.maturaexercise.gallery.persistence.converter;

import at.maturaexercise.gallery.domain.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email, String> {
    @Override
    public String convertToDatabaseColumn(Email email) {
        return Optional.ofNullable(email).map(Email::value).orElse(null);
    }

    @Override
    public Email convertToEntityAttribute(String dbValue) {
        return Optional.ofNullable(dbValue).map(Email::new).orElse(null);
    }
}
