package com.SystemDesign.BookMyShow.repository;

import com.SystemDesign.BookMyShow.model.Show;
import com.SystemDesign.BookMyShow.model.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    @Query(value = "select distinct show from showseat where id in :showSeatIds", nativeQuery = false)
    Show getShow(@Param("showSeatIds") List<Long> showSeatIds);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select * from showseat where id in :showSeatIds", nativeQuery = true)
    List<ShowSeat> findAllShowSeatsAndLock(@Param("showSeatIds") List<Long> showSeatIds);
}
