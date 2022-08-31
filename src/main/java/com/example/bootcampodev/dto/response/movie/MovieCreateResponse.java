package com.example.bootcampodev.dto.response.movie;

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

