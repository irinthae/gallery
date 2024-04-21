package at.maturaexercise.gallery.persistence.converter;

import at.maturaexercise.gallery.domain.AlbumType;
import at.maturaexercise.gallery.persistence.exception.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class AlbumTypeConverter implements AttributeConverter<AlbumType, String> {

    private static final String DIGITAL_KEY = "D";
    private static final String PHYSICAL_KEY = "P";

    @Override
    public String convertToDatabaseColumn(AlbumType albumType) {
        return Optional.ofNullable(albumType)
                       .map(a -> switch (a) {
                           case DIGITAL -> DIGITAL_KEY;
                           case PHYSICAL -> PHYSICAL_KEY;
                       }).orElse(null);
    }

    @Override
    public AlbumType convertToEntityAttribute(String dbValue) {
        return Optional.ofNullable(dbValue)
                       .map(dbV -> switch (dbV) {
                           case DIGITAL_KEY -> AlbumType.DIGITAL;
                           case PHYSICAL_KEY -> AlbumType.PHYSICAL;
                           default -> throw DataQualityException.forInvalidEnumDBValue(dbValue, AlbumType.class);
                       }).orElse(null);
    }
}
