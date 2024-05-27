package com.brash.digital_bookshelf.data.entity;

import com.brash.digital_bookshelf.data.enums.PriceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorInfo author;

    @OneToOne()
    @JoinColumn(name = "cover_id")
    private Image cover;

    @ManyToMany
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "book_tags",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "series_id")
    private BookSeries series;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false, length = 3000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "price_type", length = 20)
    private PriceType priceType;

    @Min(0)
    @Column(name = "price")
    private Long price;

    @Column(name = "extension", nullable = false)
    private String extension;

    @UpdateTimestamp
    private Timestamp lastUpdate;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    public String getFilenameWithExtension(){
        return this.id + "." + this.getExtension();
    }
}