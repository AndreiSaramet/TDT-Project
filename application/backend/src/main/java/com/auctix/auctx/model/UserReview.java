package com.auctix.auctx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_review")
public class UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text")
    @Length(min = 6, max = 1000)
    private String text;

    @Column(name = "rating")
    @Min(1)
    @Max(5)
    private Integer rating;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "post_date")
    private LocalDateTime postDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poster_id")
    @ToString.Exclude
    @JsonIgnore
    private Users poster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    @ToString.Exclude
    @JsonIgnore
    private Users receiver;
}
