package at.maturaexercise.gallery.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Embeddable
public class Address {

    @Column(name = "streetNumber", length = 64)
    @NotBlank
    private String streetNumber;

    @Column(name = "zipCode", length = 16)
    @NotBlank
    private String zipCode;

    @Column(name = "city", length = 64)
    @NotBlank
    private String city;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_Address_2_Countries"))
    private Country country;
}
