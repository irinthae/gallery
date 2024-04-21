package at.maturaexercise.gallery.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "albums")
public class Album extends AbstractPersistable<Long> {
    public static final int LENGTH_ALBUM_NAME = 64;
    public static final int LENGTH_ALBUM_TPE = 1;
    public static final int INITIAL_ALBUM_PHOTOS_SIZE = 20;

    @Column(name = "album_name", length = LENGTH_ALBUM_NAME, nullable = false)
    @NotBlank
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_albums_2_photographers"))
    @NotNull
    @Valid
    private Photographer owner;

    @Column(name = "album_type", length = LENGTH_ALBUM_TPE, columnDefinition = "CHAR(1) DEFAULT 'D' CHECK(album_type IN ('D', 'P'))", nullable = false)
    private AlbumType type;

    @ElementCollection
    @JoinTable(name = "album_photos", foreignKey = @ForeignKey(name = "FK_album_photos_2_albums"))
    @Builder.Default
    private List<AlbumPhoto> albumPhotos = new ArrayList<>(INITIAL_ALBUM_PHOTOS_SIZE);

    public List<AlbumPhoto> getAlbumPhotos() {
        return Collections.unmodifiableList(albumPhotos);
    }

    public List<Photo> getPhotos() {
        return albumPhotos.stream()
                          .sorted()
                          .map(AlbumPhoto::getPhoto)
                          .toList();
    }

    public void setAlbumPhotos(List<AlbumPhoto> albumPhotos) {
        this.albumPhotos = new ArrayList<>(INITIAL_ALBUM_PHOTOS_SIZE);
        this.albumPhotos.addAll(albumPhotos);
    }

    public Album addPhotos(Photo... photos) {
        AtomicInteger maxPosition = new AtomicInteger(albumPhotos.stream()
                                                                 .max(AlbumPhoto.positionComparator)
                                                                 .map(AlbumPhoto::getPosition)
                                                                 .orElse(0));

        Arrays.stream(photos).forEach(p -> {
            albumPhotos.add(AlbumPhoto.builder()
                                      .photo(p)
                                      .position(maxPosition.incrementAndGet())
                                      .build());
        });

        return this;
    }

    public Album insertPhotos(Photo photo, Integer position) {
        //TODO

        return this;
    }

    public Album removePhoto(Photo photo) {
        List<AlbumPhoto> foundPhotos = albumPhotos.stream()
                                                  .filter(ap -> ap.getPhoto().equals(photo))
                                                  .toList();

        foundPhotos.forEach(fp -> {
            int position = fp.getPosition();
            albumPhotos.remove(fp);
            albumPhotos.stream()
                       .filter(ap -> ap.getPosition() > position)
                       .forEach(ap -> ap.setPosition(ap.getPosition() - 1));
        });


        return this;
    }

    public Album removePhotos(Photo... photos) {
        Arrays.stream(photos).forEach(this::removePhoto);

        return this;
    }

    public Album clearPhotos() {
        albumPhotos.clear();

        return this;
    }
}











