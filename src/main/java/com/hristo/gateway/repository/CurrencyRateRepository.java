package com.hristo.gateway.repository;

import com.hristo.gateway.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    Optional<CurrencyRate> findFirstByCodeOrderByDateTimeDesc(String code);

    @Query("select cr from CurrencyRate cr where cr.code = :code and cr.dateTime >= :dateTime")
    List<CurrencyRate> findAllByCodeWithDateTimeAfter(@Param("code") String code, @Param("dateTime") LocalDateTime dateTime);
}
