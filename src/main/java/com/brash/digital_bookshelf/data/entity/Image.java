package com.brash.digital_bookshelf.data.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "extension", nullable = false)
    private String extension;

    @Column(name = "blurhash", nullable = false)
    private String blurhash;

    public String getFilenameWithExtension(){
        return this.id + "." + this.getExtension();
    }
}
