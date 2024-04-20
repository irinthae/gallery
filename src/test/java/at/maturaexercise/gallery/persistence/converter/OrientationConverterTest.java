package at.maturaexercise.gallery.persistence.converter;

import at.maturaexercise.gallery.domain.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrientationConverterTest {

    private OrientationConverter orientationConverter;

    @BeforeEach
    void init() {
        orientationConverter = new OrientationConverter();
    }

    @Test
    void ensureConvertToDatabaseColumnWorks() {
        assertThat(orientationConverter.convertToDatabaseColumn(Orientation.PORTRAIT)).isEqualTo("P");
        assertThat(orientationConverter.convertToDatabaseColumn(Orientation.LANDSCAPE)).isEqualTo("L");
        assertThat(orientationConverter.convertToDatabaseColumn(Orientation.SQUARE)).isEqualTo("S");
        assertThat(orientationConverter.convertToDatabaseColumn(null)).isNull();

    }

    @Test
    void ensureConvertToEntityAttributeWorks() {
        assertThat(orientationConverter.convertToEntityAttribute("P")).isEqualTo(Orientation.PORTRAIT);
        assertThat(orientationConverter.convertToEntityAttribute("L")).isEqualTo(Orientation.LANDSCAPE);
        assertThat(orientationConverter.convertToEntityAttribute("S")).isEqualTo(Orientation.SQUARE);
    }

    @Test
    void ensureConvertToEntityAttributeWithIllegalArgumentThrowsException() {
        var iaEx = assertThrows(IllegalArgumentException.class, () -> {
            orientationConverter.convertToEntityAttribute("x");
        });

        assertThat(iaEx).hasMessage("Unknown value 'x' for Orientation");
    }


}