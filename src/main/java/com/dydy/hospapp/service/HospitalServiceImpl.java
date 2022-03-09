package com.dydy.hospapp.service;

import com.dydy.hospapp.domain.HospitalRepository;
import com.dydy.hospapp.dto.HospitalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService{

    private final HospitalRepository hospitalRepository;

    @Override
    public Page<HospitalResponseDto> hospitalList(Pageable pageable, String siDo, String sggu) {
        return hospitalRepository.findByHospital(pageable, siDo, sggu);
    }

    @Override
    public List<String> listBySido() {
        return hospitalRepository.listBySido();
    }

    @Override
    public List<String> ListBySggu(String siDo) {
        return hospitalRepository.ListBySggu(siDo);
    }

}
