package at.maturaexercise.gallery.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Builder

@Entity
@Table(name = "photographers")
public class Photographer extends AbstractPersistable<Long> {
}
