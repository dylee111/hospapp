package com.dydy.hospapp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long>, HospitalRepositoryCustom {

    @Query("SELECT DISTINCT h.sidoCdNm FROM Hospital h ORDER BY h.sidoCdNm ASC ")
    List<String> listBySido();

    @Query("SELECT DISTINCT h.sgguCdNm FROM Hospital h WHERE h.sidoCdNm=:siDo ORDER BY h.sgguCdNm ASC ")
    List<String> ListBySggu(@Param("siDo") String SiDo);
}
