package ru.practicum.ewm.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.entity.EndpointHit;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EndpointHitRepository extends JpaRepository<EndpointHit, Long> {

    @Query("SELECT e.uri FROM EndpointHit e WHERE e.timestamp BETWEEN :start AND :end")
    List<String> findAllHitsBetweenDates(LocalDateTime start, LocalDateTime end);

    @Query("SELECT COUNT(DISTINCT e.ip) FROM EndpointHit e WHERE e.uri = :uri AND e.timestamp BETWEEN :start AND :end")
    long findHitsByUriAndUniqueIp(LocalDateTime start, LocalDateTime end, String uri);

    @Query("SELECT COUNT(e) FROM EndpointHit e WHERE e.uri = :uri AND e.timestamp BETWEEN :start AND :end")
    long findHitsByUriNotUniqueIp(LocalDateTime start, LocalDateTime end, String uri);

}