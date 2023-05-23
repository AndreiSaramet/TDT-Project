package com.auctix.auctx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_image")
public class ProductImage {

    @Id
    private Long id;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    @JsonIgnore
    @ToString.Exclude
    private Product product;

    @Column(name = "image")
    @Lob
    @ToString.Exclude
    private byte[] image;
}
