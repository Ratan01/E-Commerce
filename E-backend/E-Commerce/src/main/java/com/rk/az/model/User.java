package com.rk.az.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rk.az.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String email;
    private String fullName;
    private String mobile;
    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @OneToMany
    private Set<Address> addresses = new HashSet<>();

    @ManyToMany
    @JsonIgnore
    private Set<Coupon> usedCoupons = new HashSet<>();

//    @OneToMany
//    private Set<Cart> carts = new HashSet<>();
//
//    @OneToMany
//    private Set<Order> orders =new HashSet<>();
//
//    @OneToMany
//    private Set<Review> reviews = new HashSet<>();
//
//    @OneToMany
//    private Set<Transaction> transactions = new HashSet<>();
//    @OneToOne
//    private Set<WishList> wishLists = new HashSet<>();
}
