package com.dydy.hospapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HospitalResponseDto {

    private String hospitalName;
    private String address;
    private String siDo;
    private String siGunGu;
    private String telNo;
    private String pcrYN;

    public HospitalResponseDto(String hospitalName, String address, String telNo, String pcrYN, String siDo) {
        this.hospitalName = hospitalName;
        this.address = address;
        this.telNo = telNo;
        this.pcrYN = pcrYN;
        this.siDo = siDo;
    }
}
