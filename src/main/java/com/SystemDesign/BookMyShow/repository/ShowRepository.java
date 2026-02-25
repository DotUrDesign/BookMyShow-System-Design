package com.SystemDesign.BookMyShow.repository;

import com.SystemDesign.BookMyShow.model.Screen;
import com.SystemDesign.BookMyShow.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Long> {

    @Query(value = "select * from shows where id = :showId", nativeQuery = true)
    Optional<Show> getShowById(@Param("showId") Long showId);

    @Query(value = "select screen from shows where id = :showId", nativeQuery = false)
    Optional<Screen> getScreenByShowId(@Param("showId") Long showId);


}
