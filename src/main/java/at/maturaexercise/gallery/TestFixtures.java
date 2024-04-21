package at.maturaexercise.gallery;

import at.maturaexercise.gallery.domain.*;
import at.maturaexercise.gallery.foundation.DateTimeFactory;

import java.util.Set;

public class TestFixtures {

//************** PHOTO *************************************************************************************************

    public static Photo photo() {
        return Photo.builder()
                    .name("My Photo")
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
                     .username("irina.gramsch@gmx.at")
                     .password("geheim")
                     .firstName("Irina")
                     .lastName("Gramsch")
                     .nickname("ig")
                     .build();
    }

//************** PHOTOGRAPHER ******************************************************************************************

    public static Photographer photographer() {
        return Photographer.builder()
                           .username("irina.gramsch@gmx.at")
                           .password("geheim")
                           .firstName("Irina")
                           .lastName("Gramsch")
                           .studioAddress(address())
                           .mobilePhoneNumber(phoneNumber())
                           .emailAddresses(Set.of(new Email("irina.gramsch@gmx.at"), new Email("i.gramsch@spg.at")))
                           .build();
    }

//************** ADDRESS ***********************************************************************************************

    public static Address address() {
        return Address.builder()
                      .streetNumber("Bag End")
                      .zipCode("1220")
                      .city("Hobbition")
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
}


















