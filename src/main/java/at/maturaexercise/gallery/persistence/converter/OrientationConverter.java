package at.maturaexercise.gallery.persistence.converter;

import at.maturaexercise.gallery.domain.Orientation;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class OrientationConverter implements AttributeConverter<Orientation, String> {

    @Override
    public String convertToDatabaseColumn(Orientation orientation) {

        return Optional.ofNullable(orientation)
                       .map(o -> switch (o) { //Wenn in dem Optional was ist, wandel es um, ansonsten null
                           case LANDSCAPE -> "L";
                           case PORTRAIT -> "P";
                           case SQUARE -> "S";
                       }).orElse(null);

        /*return (orientation == null) ? null
                                     : switch (orientation) {
            case LANDSCAPE -> "L";
            case PORTRAIT -> "P";
            case SQUARE -> "S";
        };*/
    }

    @Override
    public Orientation convertToEntityAttribute(String dbValue) {
        return Optional.ofNullable(dbValue)
                       .map(dbV -> switch (dbV) {
                           case "L" -> Orientation.LANDSCAPE;
                           case "P" -> Orientation.PORTRAIT;
                           case "S" -> Orientation.SQUARE;
                           default ->
                                   throw new IllegalArgumentException("Unknown value '%s' for Orientation".formatted(dbValue));
                       }).orElse(null);
    }
}
