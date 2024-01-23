package com.passboard.DigitalIdentityManagement.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.validator.constraints.LuhnCheck;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * username and password should've been handled by spring security UserModel, but was declared here just for simplicity
     */
    @Column(unique = true)
    @NotNull
    @NotEmpty
    private String username;

    @Column
    @NotNull
    @NotEmpty
    @JsonIgnore
    // TODO: Add password policy e.g contains upper and lower cases, numbers and special characters
    private String password;

    @Column
    @NotEmpty
    @NotNull
    private String firstName;

    @Column
    @NotEmpty
    @NotNull
    private String lastName;

    @Column
    @NotNull
    @Past
    private Date birthDay;

    @Column
    @NotNull
    @JsonProperty("active")
    private Boolean active;

    @Column(unique = true)
    @NotNull
    @NotEmpty
    private String nationalId;

    @Column
    @NotEmpty
    @NotNull
    private String nationality;

    @Column
    @NotEmpty
    private String city;

    @Column
    @NotEmpty
    private String address;

    @Column
    @Email
    @NotEmpty
    private String emailAddress;

    @Column
    @NotEmpty
    private String phoneNumber;

    @Column
    @NotEmpty
    @NotNull
    private String gender;

    @Column
    @Min(16)
    private Short age;

    @Column
    @NotEmpty
    @NotNull
    @LuhnCheck
    @JsonIgnore
    private String cardNumber;

    @Column
    @NotEmpty
    @NotNull
    @JsonIgnore
    private String cardCVV;

    @OneToMany
    @JoinColumn
    @ToString.Exclude
    private Set<Role> roles;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Visitor visitor = (Visitor) o;
        return getId() != null && Objects.equals(getId(), visitor.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
