package com.example.bootcampodev.adapter.rest.movie;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieCreateResponse {

    private Long id;

    public static MovieCreateResponse convertToMovieResponse(Long movieId)
    {
        return MovieCreateResponse.builder()
                .id(movieId)
                .build();
    }
}

