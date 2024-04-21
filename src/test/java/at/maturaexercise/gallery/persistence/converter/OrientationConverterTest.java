package at.maturaexercise.gallery.persistence.converter;

import at.maturaexercise.gallery.domain.Orientation;
import at.maturaexercise.gallery.persistence.exception.DataQualityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrientationConverterTest {

    private static final String PORTRAIT_KEY = "P";
    private static final String LANDSCAPE_KEY = "L";
    private static final String SQUARE_KEY = "S";

    private OrientationConverter orientationConverter;

    @BeforeEach
    void init() {
        orientationConverter = new OrientationConverter();
    }

    @Test
    void ensureConvertToDatabaseColumnWorks() {
        assertThat(orientationConverter.convertToDatabaseColumn(Orientation.PORTRAIT)).isEqualTo(PORTRAIT_KEY);
        assertThat(orientationConverter.convertToDatabaseColumn(Orientation.LANDSCAPE)).isEqualTo(LANDSCAPE_KEY);
        assertThat(orientationConverter.convertToDatabaseColumn(Orientation.SQUARE)).isEqualTo(SQUARE_KEY);
        assertThat(orientationConverter.convertToDatabaseColumn(null)).isNull();

    }

    @Test
    void ensureConvertToEntityAttributeWorks() {
        assertThat(orientationConverter.convertToEntityAttribute(PORTRAIT_KEY)).isEqualTo(Orientation.PORTRAIT);
        assertThat(orientationConverter.convertToEntityAttribute(LANDSCAPE_KEY)).isEqualTo(Orientation.LANDSCAPE);
        assertThat(orientationConverter.convertToEntityAttribute(SQUARE_KEY)).isEqualTo(Orientation.SQUARE);
    }

    @Test
    void ensureConvertToEntityAttributeWithIllegalArgumentThrowsException() {
        var dqEx = assertThrows(DataQualityException.class, () -> orientationConverter.convertToEntityAttribute("x"));

        assertThat(dqEx).hasMessage("Unknown dbValue of 'x' for enumType 'Orientation'");
    }


}