package com.example.bootcampodev.entity;

import com.example.bootcampodev.entity.enums.Genre;
import com.example.bootcampodev.entity.enums.Status;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Table(name = "movie")
@Entity(name = "movieEntity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Where(clause = "status = 'ACTIVE'")
//@Where(clause = "status <> 'ACTIVE'")
@Where(clause = "status <> 'DELETED'")
@EntityListeners(AuditingEntityListener.class)
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @CreatedDate
    private LocalDateTime createdDate;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @NotNull
    private int releaseYear;

    private String director;

    @OneToMany(mappedBy = "movie",fetch = FetchType.EAGER)
    private List<RateEntity> rates;
}
