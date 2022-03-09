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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/")
    public String home(RequestPageSortDto pageSortDto, Model model,
                       @RequestParam(value = "siDo", required = false) String siDo,
                       @RequestParam(value = "sggu", required = false) String sggu) {

        Pageable pageable = pageSortDto.getPageableSort(Sort.by("hospital_id").ascending());

        Page<HospitalResponseDto> hospitalList = hospitalService.hospitalList(pageable, siDo, sggu);
        PagingDto result = new PagingDto(hospitalList);

        model.addAttribute("siDos", hospitalService.listBySido());
        model.addAttribute("hospitals", hospitalList);
        model.addAttribute("results", result);
        return "index";
    }

    @GetMapping("/api/sggu")
    @ResponseBody
    public List<String> sggu(String siDo) {
        return hospitalService.ListBySggu(siDo);
    }
}
