package at.maturaexercise.gallery.persistence.converter;

import at.maturaexercise.gallery.domain.Orientation;
import at.maturaexercise.gallery.persistence.exception.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class OrientationConverter implements AttributeConverter<Orientation, String> {

    private static final String LANDSCAPE_KEY = "L";
    private static final String PORTRAIT_KEY = "P";
    private static final String SQUARE_KEY = "S";

    @Override
    public String convertToDatabaseColumn(Orientation orientation) {

        return Optional.ofNullable(orientation)
                       .map(o -> switch (o) { //Wenn in dem Optional was ist, wandel es um, ansonsten null
                           case LANDSCAPE -> LANDSCAPE_KEY;
                           case PORTRAIT -> PORTRAIT_KEY;
                           case SQUARE -> SQUARE_KEY;
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
                           case LANDSCAPE_KEY -> Orientation.LANDSCAPE;
                           case PORTRAIT_KEY -> Orientation.PORTRAIT;
                           case SQUARE_KEY -> Orientation.SQUARE;
                           default -> throw DataQualityException.forInvalidEnumDBValue(dbValue, Orientation.class);
                       }).orElse(null);
    }
}
