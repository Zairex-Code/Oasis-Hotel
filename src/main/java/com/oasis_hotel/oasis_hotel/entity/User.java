package com.oasis_hotel.oasis_hotel.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.oasis_hotel.oasis_hotel.entity.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", nullable=false, length=30)
    private String firstName;

    @Column(name="last_name", nullable=false, length=30)
    private String lastName;

    @Column(nullable=false,unique=true,length=100)
    private String email;

    @Column(nullable=false, length=100)
    private String password;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role = Role.CUSTOMER;

    @CreationTimestamp
    @Column(name="created_at",nullable=false, updatable=false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updatedAt",nullable=false)
    private LocalDateTime updatedAt;


    // ==========================================
    // SPRING SECURITY USERDETAILS METHODS
    // =========================================

    /**
     * Returns the authorities (roles) granted to the user.
     * Spring Security uses the "ROLE_" prefix by convention.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("Role_" + role.name()));
    }

    /**
     * Returns the username used to authenticate the user.
     * In our application, the email is the unique identifier.
     */

    @Override
    public String getUsername() {
        return this.email; // here we say to spring that our "Username" is email
    }


    /**
     * Indicates whether the user's account has expired.
     */
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     */

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     */
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     */

    @Override
    public boolean isEnabled(){
        return true;
    }



}
