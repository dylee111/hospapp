package com.dydy.hospapp.service;

import com.dydy.hospapp.domain.HospitalRepository;
import com.dydy.hospapp.dto.HospitalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService{

    private final HospitalRepository hospitalRepository;

    @Override
    public Page<HospitalResponseDto> hospitalList(Pageable pageable) {
        return hospitalRepository.findByHospital(pageable);
    }
}
