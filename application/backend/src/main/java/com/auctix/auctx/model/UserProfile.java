package com.auctix.auctx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profile")
public class UserProfile {

    @Id
    private Long id;

    @Column(name = "bio")
    @Length(min = 2, max = 1000)
    private String bio;

    @Lob
    @Column(name = "profile_picture")
    @ToString.Exclude
    private byte[] profilePicture;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    @ToString.Exclude
    @JsonIgnore
    private Users user;
}
