package com.dydy.hospapp.domain;

import com.dydy.hospapp.dto.HospitalResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HospitalRepositoryCustom {

    Page<HospitalResponseDto> findByHospital(Pageable pageable, String siDo, String sggu);

    List<HospitalResponseDto> ListHospital();
}
