package com.dydy.hospapp.domain;

import com.dydy.hospapp.dto.HospitalResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HospitalRepositoryCustom {

    Page<HospitalResponseDto> findByHospital(Pageable pageable);
}
