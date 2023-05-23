package com.auctix.auctx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Length(min = 6, max = 50)
    @Email
    private String email;

    @Column(unique = true)
    @Length(min = 6, max = 40)
    private String username;

    @Length(min = 6, max = 255)
    private String password;

    @NotNull
    private String role;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<FriendRequest> sentFriendRequests;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<FriendRequest> receivedFriendRequests;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Product> products;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<ProductReview> productReviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<UserFriendship> userFriendships;

    @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<UserFriendship> friendFriendships;

    @OneToMany(mappedBy = "poster", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<UserReview> postedUserReviews;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<UserReview> receivedUserReviews;
}
