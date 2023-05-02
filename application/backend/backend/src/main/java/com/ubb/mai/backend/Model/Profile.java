package com.ubb.mai.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    private AppUser appUser;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String city;

    @Override
    public String toString() {
        return "Profile{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", emailAddress='" + emailAddress + '\'' + ", city='" + city + '\'' + '}';
    }
}
