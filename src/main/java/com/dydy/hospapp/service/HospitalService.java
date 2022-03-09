package com.dydy.hospapp.service;

import com.dydy.hospapp.dto.HospitalResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HospitalService {

    Page<HospitalResponseDto> hospitalList(Pageable pageable, String siDo, String sggu);

    List<HospitalResponseDto> ListHospital();

    List<String> listBySido();

    List<String> ListBySggu(String siDo);
}
