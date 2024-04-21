package at.maturaexercise.gallery;

import at.maturaexercise.gallery.domain.*;
import at.maturaexercise.gallery.foundation.DateTimeFactory;

import java.util.Set;

public class TestFixtures {

    //TODO Detached Entity

//************** PHOTO *************************************************************************************************

    public static Photo photo() {
        return Photo.builder()
                    .name("Gathering of Dwarves")
                    .location(location())
                    .creationTimeStamp(DateTimeFactory.now())
                    .width(640)
                    .height(480)
                    .orientation(Orientation.PORTRAIT)
                    .photographer(photographer())
                    .build();
    }

    public static Photo photo(String name) {
        return Photo.builder()
                    .name(name)
                    .location(location())
                    .creationTimeStamp(DateTimeFactory.now())
                    .width(640)
                    .height(480)
                    .orientation(Orientation.PORTRAIT)
                    .photographer(photographer())
                    .build();
    }

//************** LOCATION **********************************************************************************************

    public static Location location() {
        return new Location(16.0d, 48.0d);
    }

//************** PERSON ************************************************************************************************

    public static Person person() {
        return Person.builder()
                     .username(new EmailAddress("bilbo.baggins@gdmail.at"))
                     .password("RingbearerSupreme")
                     .firstName("Bilbo")
                     .lastName("Baggins")
                     .nickName("Wyrmslayer")
                     .build();
    }

//************** PHOTOGRAPHER ******************************************************************************************

    public static Photographer photographer() {
        return Photographer.builder()
                           .username(new EmailAddress("bilbo.baggins@gdmail.at"))
                           .password("RingbearerSupreme")
                           .firstName("Bilbo")
                           .lastName("Baggins")
                           .studioAddress(address())
                           .mobilePhoneNumber(phoneNumber())
                           .emailAddresses(Set.of(new EmailAddress("dragonbane.bilbo@thorinandcompany.at"), new EmailAddress("b.baggins@pipeweedlovers.at")))
                           .build();
    }

//************** ADDRESS ***********************************************************************************************

    public static Address address() {
        return Address.builder()
                      .streetNumber("Bag End")
                      .zipCode("1220")
                      .city("Hobbiton")
                      .country(country())
                      .build();
    }

//************** COUNTRY ***********************************************************************************************

    public static Country country() {
        return Country.builder()
                      .name("The Shire")
                      .iso2Code("TS")
                      .topLevelDomain(".st")
                      .countryCode(77)
                      .build();
    }

//************** PHONENUMBER *******************************************************************************************

    public static PhoneNumber phoneNumber() {
        return PhoneNumber.builder()
                          .countryCode(77)
                          .areaCode(9)
                          .serialNumber("12341234")
                          .build();
    }

//************** ALBUM *************************************************************************************************

    public static Album album() {
        return Album.builder()
                    .name("Bilbo's Odyssey: A Tale of the Lonely Mountain")
                    .type(AlbumType.PHYSICAL)
                    .owner(photographer())
                    .build();
    }


}





























