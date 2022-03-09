package com.dydy.hospapp.domain;

import com.dydy.hospapp.batch.ResponseDto;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOSPITAL_ID")
    private Long id;

    private String addr;      // 병원 주소
    private Integer mgtStaDd; // 운영시작일자
    private String pcrPsblYn; // PCR 검사 여부
    private String ratPsblYn; // 호흡기 전담 클리닉 여부
    private Integer recuClCd; // 병원종별코드(종합병원 11,병원 21,의원 31)
    private Integer rnum;     // PK와 동일
    private String rprtWorpClicFndtTgtYn;
    private String sgguCdNm; // 시, 군 , 구
    private String sidoCdNm; // 시, 도 이름
    private String telno;    // 병원 연락처
    private String yadmNm;   // 병원 이름
    private String ykihoEnc; // 암호화된 병원 기호

    @Builder
    public Hospital(String addr, Integer mgtStaDd, String pcrPsblYn, String ratPsblYn, Integer recuClCd,
                    Integer rnum, String rprtWorpClicFndtTgtYn, String sgguCdNm, String sidoCdNm, String telno,
                    String yadmNm, String ykihoEnc) {
        this.addr = addr;
        this.mgtStaDd = mgtStaDd;
        this.pcrPsblYn = pcrPsblYn;
        this.ratPsblYn = ratPsblYn;
        this.recuClCd = recuClCd;
        this.rnum = rnum;
        this.rprtWorpClicFndtTgtYn = rprtWorpClicFndtTgtYn;
        this.sgguCdNm = sgguCdNm;
        this.sidoCdNm = sidoCdNm;
        this.telno = telno;
        this.yadmNm = yadmNm;
        this.ykihoEnc = ykihoEnc;
    }
}
