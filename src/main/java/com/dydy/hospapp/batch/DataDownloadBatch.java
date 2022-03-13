package com.dydy.hospapp.batch;

import com.dydy.hospapp.domain.Hospital;
import com.dydy.hospapp.domain.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataDownloadBatch {

    private final HospitalRepository hospitalRepository;

    // 초 분 시 일 월 주
    @Scheduled(cron = "0 50 * * * *", zone = "Asia/Seoul") // 매시 정각에 배치하게 설정
    public void startBatch() {
        try {
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

            // 전체 delete
            hospitalRepository.deleteAll();
            log.info("삭제 완료>>>>>>>>>>>");
            // 전체 insert
            hospitalRepository.saveAll(hospitals);
            log.info("다시 추가>>>>>>>>>>>");

        } catch (RestClientException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
