package com.dydy.hospapp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long>, HospitalRepositoryCustom {

//    @Query("SELECT h FROM Hospital h WHERE h.sgguCdNm=:sggu AND h.sidoCdNm=:sido ")
//    List<Hospital> findBySido(@Param("sggu") String sggu, @Param("sido") String sido);
}
