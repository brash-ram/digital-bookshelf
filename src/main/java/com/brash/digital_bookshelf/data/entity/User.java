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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 20)
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

    @Column(name = "name")
    private String name;

    @Column(name = "showBirthType")
    @Enumerated(EnumType.STRING)
    private ShowBirthType showBirthType;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "lifeStatus")
    private String lifeStatus;

    @Column(name = "about", length = 10000)
    private String about;

    @Column(name = "refVk")
    private String refVk;

    @Column(name = "refTg")
    private String refTg;

    @Column(name = "refSite")
    private String refSite;

    @Column(name = "refEmail")
    private String refEmail;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;


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
