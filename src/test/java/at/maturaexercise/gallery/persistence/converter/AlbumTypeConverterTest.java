package at.maturaexercise.gallery.persistence.converter;

import at.maturaexercise.gallery.domain.AlbumType;
import at.maturaexercise.gallery.persistence.exception.DataQualityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AlbumTypeConverterTest {

    private static final String DIGITAL_KEY = "D";
    private static final String PHYSICAL_KEY = "P";

    private AlbumTypeConverter albumTypeConverter;

    @BeforeEach
    void init() {
        albumTypeConverter = new AlbumTypeConverter();
    }

    @Test
    void ensureConvertToDatabaseColumnWorks() {
        assertThat(albumTypeConverter.convertToDatabaseColumn(AlbumType.DIGITAL)).isEqualTo(DIGITAL_KEY);
        assertThat(albumTypeConverter.convertToDatabaseColumn(AlbumType.PHYSICAL)).isEqualTo(PHYSICAL_KEY);
        assertThat(albumTypeConverter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void ensureConvertToEntityAttributeWorks() {
        assertThat(albumTypeConverter.convertToEntityAttribute(DIGITAL_KEY)).isEqualTo(AlbumType.DIGITAL);
        assertThat(albumTypeConverter.convertToEntityAttribute(PHYSICAL_KEY)).isEqualTo(AlbumType.PHYSICAL);
    }

    @Test
    void ensureConvertToEntityAttributeWithIllegalArgumentThrowsException() {
        var dqEx = assertThrows(DataQualityException.class, () -> albumTypeConverter.convertToEntityAttribute("X"));

        assertThat(dqEx).hasMessage("Unknown dbValue of 'X' for enumType 'AlbumType'");
    }
}