package at.maturaexercise.gallery.service;

import at.maturaexercise.gallery.domain.*;
import at.maturaexercise.gallery.persistence.AlbumRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor

@Service
public class InitService {

    private final AlbumRepository albumRepository;

    @PostConstruct
    public void init() {
        Album album = Album.builder()
                           .name("Bilbo's Odyssey: A Tale of the Lonely Mountain")
                           .type(AlbumType.PHYSICAL)
                           .owner(Photographer.builder()
                                              .username(new EmailAddress("bilbo.baggins@gdmail.at"))
                                              .password("RingbearerSupreme")
                                              .firstName("Bilbo")
                                              .lastName("Baggins")
                                              .studioAddress(Address.builder()
                                                                    .streetNumber("Bag End")
                                                                    .zipCode("1220")
                                                                    .city("Hobbiton")
                                                                    .country(Country.builder()
                                                                                    .name("The Shire")
                                                                                    .iso2Code("TS")
                                                                                    .topLevelDomain(".st")
                                                                                    .countryCode(77)
                                                                                    .build())
                                                                    .build())
                                              .mobilePhoneNumber(PhoneNumber.builder()
                                                                            .countryCode(77)
                                                                            .areaCode(9)
                                                                            .serialNumber("12341234")
                                                                            .build())
                                              .emailAddresses(Set.of(new EmailAddress("dragonbane.bilbo@thorinandcompany.at"), new EmailAddress("b.baggins@pipeweedlovers.at")))
                                              .build())
                           .build();

        albumRepository.saveAndFlush(album);
    }
}
