package com.ahuggins.spring_data_jpa_demo.dtos;

import org.springframework.beans.factory.annotation.Value;

public interface MovieTitleRatingDto {
    String getMovieTitle();

    @Value("#{target.rating / 2}")
    int getStarRating();
}
