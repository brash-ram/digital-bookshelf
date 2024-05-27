package com.brash.digital_bookshelf.data.entity;

import com.brash.digital_bookshelf.data.enums.Gender;
import com.brash.digital_bookshelf.data.enums.Role;
import com.brash.digital_bookshelf.data.enums.ShowBirthType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    @NonNull
    private String username;

    @Column(name = "password", nullable = false)
    @NonNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @NonNull
    private Set<AuthorityRole> roles = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "profile_image_id")
    private Image profileImage;

    @OneToOne(mappedBy = "user")
    private AuthorInfo authorInfo;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "show_birth_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShowBirthType showBirthType;

    @Column(name = "birth")
    private Date birth;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "life_status", length = 500)
    private String lifeStatus;

    @Column(name = "about", length = 10000)
    private String about;

    @Column(name = "ref_vk")
    private String refVk;

    @Column(name = "ref_tg")
    private String refTg;

    @Column(name = "ref_site")
    private String refSite;

    @Column(name = "ref_email")
    private String refEmail;

    @OneToMany(mappedBy = "user")
    private Set<PurchasedBook> purchasedBooks = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_library",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> library = new HashSet<>();

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;


    public void addBookToLibrary(Book book) {
        library.add(book);
    }

    public List<Role> getEnumRoles() {
        return roles.stream().map(AuthorityRole::getName).toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
