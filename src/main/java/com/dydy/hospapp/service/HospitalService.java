package com.dydy.hospapp.service;

import com.dydy.hospapp.dto.HospitalResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HospitalService {

    Page<HospitalResponseDto> hospitalList(Pageable pageable);
}
