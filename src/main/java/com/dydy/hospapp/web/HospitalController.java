package com.dydy.hospapp.web;

import com.dydy.hospapp.dto.HospitalResponseDto;
import com.dydy.hospapp.dto.paging.PagingDto;
import com.dydy.hospapp.dto.paging.RequestPageSortDto;
import com.dydy.hospapp.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/")
    public String home(RequestPageSortDto pageSortDto, Model model) {

        Pageable pageable = pageSortDto.getPageableSort(Sort.by("hospital_id").ascending());

        Page<HospitalResponseDto> hospitalList = hospitalService.hospitalList(pageable);
        PagingDto result = new PagingDto(hospitalList);

        log.info("TOTAL PAGE={}",hospitalList.getTotalPages());
        log.info("TOTAL>>>>>={}", result.getTotalPage());
        model.addAttribute("hospital", hospitalList);
        model.addAttribute("result", result);
        return "index";
    }
}
