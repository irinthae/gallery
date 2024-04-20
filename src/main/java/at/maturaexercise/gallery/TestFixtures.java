package at.maturaexercise.gallery;

import at.maturaexercise.gallery.domain.*;
import at.maturaexercise.gallery.foundation.DateTimeFactory;

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

    public static Photographer photographer() {
        return new Photographer();

    }

//************** LOCATION **********************************************************************************************

    public static Location location() {
        return new Location(16.0d, 48.0d);
    }

//************** PERSON **********************************************************************************************

    public static Person person() {
        return Person.builder()
                     .username("irina.gramsch@gmx.at")
                     .password("geheim")
                     .firstName("Irina")
                     .lastName("Gramsch")
                     .nickname("ig")
                     .build();
    }

}


















