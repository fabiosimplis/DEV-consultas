package com.devsuperior.uri2611.repositories;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projection.MovieMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true,
            value = "SELECT m.id, m.name FROM movies AS m " +
                    "INNER JOIN genres AS g " +
                    "ON m.id_genres = g.id " +
                    "WHERE UPPER(g.description) = UPPER(:genre)")
    List<MovieMinProjection> findMoviesByGenreSQL(String genre);

    @Query("SELECT new com.devsuperior.uri2611.dto.MovieMinDTO(m.id, m.name) " +
            "FROM Movie m " +
            "WHERE UPPER(m.genre.description) = UPPER(:genre)")
    List<MovieMinDTO> findMoviesByGenreJPQL(String genre);
}
