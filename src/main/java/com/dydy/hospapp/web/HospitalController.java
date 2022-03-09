package com.dydy.hospapp.web;

import com.dydy.hospapp.dto.ApiReturn;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("")
    public String index(RequestPageSortDto pageSortDto, Model model,
                       @RequestParam(value = "siDo", required = false) String siDo,
                       @RequestParam(value = "sggu", required = false) String sggu) {

        Pageable pageable = pageSortDto.getPageableSort(Sort.by("YADM_NM").ascending());

        Page<HospitalResponseDto> hospitalList = hospitalService.hospitalList(pageable, siDo, sggu);
        PagingDto result = new PagingDto(hospitalList);

        // 시, 도 리스트
        model.addAttribute("siDos", hospitalService.listBySido());
        // 병원 리스트
        model.addAttribute("hospitals", hospitalList);
        log.info("LIST SIZE={}", hospitalList.getTotalElements());
        // 페이징 리스트 (페이지수, 이전, 이후)
        model.addAttribute("results", result);
        return "index";
    }

    @GetMapping("/api/hospital")
    @ResponseBody
    public ApiReturn<Page<HospitalResponseDto>> apiHospitalList(RequestPageSortDto pageSortDto,
                                                                @RequestParam(value = "siDo", required = false) String siDo,
                                                                @RequestParam(value = "sggu", required = false) String sggu) {
        Pageable pageable = pageSortDto.getPageableSort(Sort.by("YADM_NM").ascending());

        Page<HospitalResponseDto> hospital = hospitalService.hospitalList(pageable, siDo, sggu);

        return new ApiReturn<>(hospital.getTotalElements(), hospital);
    }

    @GetMapping("/api/sggu")
    @ResponseBody
    public List<String> sggu(String siDo) {
        return hospitalService.ListBySggu(siDo);
    }
}
