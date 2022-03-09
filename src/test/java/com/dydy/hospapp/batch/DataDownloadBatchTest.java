package com.dydy.hospapp.batch;

import com.dydy.hospapp.domain.Hospital;
import com.dydy.hospapp.service.HospitalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DataDownloadBatchTest {

    @Autowired
    private HospitalService hospitalService;

    @Test
    public void start() throws URISyntaxException {

        System.out.println("HELLO");
        RestTemplate rt = new RestTemplate();

        // Service Key는 이미 utf-8로 인코딩된 상태
        // 하지만 string으로 그대로 받을 경우, 한 번 더 인코딩되면서 인식을 못하는 문제가 발생한다.
        // URI로 인코딩 방지
        URI uri = new URI("http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=g6cnfXekk4e9IY8m5jIJhLRX99mIrreQ26bq7tgH1%2BFk2FG5iiGpleBNhC5FL5LbQ7knRRHEsnhE0K70opdyPg==&pageNo=1&numOfRows=10&_type=json");

        ResponseDto dto = rt.getForObject(uri, ResponseDto.class);
        List<Item> items = dto.getResponse().getBody().getItems().getItem();
        for (Item item : items) {
            System.out.println("item1 = " + item.getYadmNm());
            System.out.println("PCR 여부 = " + item.getPcrPsblYn());
        }
    }


    // 공공 데이터 다운로드 테스트 및 다운로드 데이터 담기 (전체 데이터)
    @Test
    public void 데이터_다운로드() throws URISyntaxException {

        // 1. 전체 병원 담을 리스트
        List<Hospital> hospitalList = new ArrayList<>();

        // 2. api에서 데이터 하나 불러와서 전체 리스트 크기 확인
        RestTemplate rt = new RestTemplate();

        // totalCnt를 1로 했을 때는 item이 컬렉션이 아니라서 파싱이 안되서 2로 변경
        int totalCnt = 2;

        URI totalCntUri = new URI("http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=g6cnfXekk4e9IY8m5jIJhLRX99mIrreQ26bq7tgH1%2BFk2FG5iiGpleBNhC5FL5LbQ7knRRHEsnhE0K70opdyPg==" +
                "&pageNo=1&numOfRows=" + totalCnt + "&_type=json");

        ResponseDto dtoTotalCnt = rt.getForObject(totalCntUri, ResponseDto.class);
        totalCnt = dtoTotalCnt.getResponse().getBody().getTotalCount();

        // 3. totalCnt만큼 한번에 가져오기
        URI uri = new URI("http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=g6cnfXekk4e9IY8m5jIJhLRX99mIrreQ26bq7tgH1%2BFk2FG5iiGpleBNhC5FL5LbQ7knRRHEsnhE0K70opdyPg==" +
                "&pageNo=1&numOfRows=" + totalCnt + "&_type=json");
        ResponseDto responseDto = rt.getForObject(uri, ResponseDto.class);

        List<Item> items = responseDto.getResponse().getBody().getItems().getItem();
        System.out.println("items.size() = " + items.size());
        List<Hospital> hospitals = items.stream().map((e) -> {
            return Hospital.builder()
                    .addr(e.getAddr())
                    .mgtStaDd(e.getMgtStaDd())
                    .pcrPsblYn(e.getPcrPsblYn())
                    .ratPsblYn(e.getRatPsblYn())
                    .recuClCd(e.getRecuClCd())
                    .rnum(e.getRnum())
                    .rprtWorpClicFndtTgtYn(e.getRprtWorpClicFndtTgtYn())
                    .sgguCdNm(e.getSgguCdNm())
                    .sidoCdNm(e.getSidoCdNm())
                    .telno(e.getTelno())
                    .yadmNm(e.getYadmNm())
                    .ykihoEnc(e.getYkihoEnc())
                    .build();
        }).collect(Collectors.toList());

        assertThat(totalCnt).isEqualTo(items.size());
    }
}
