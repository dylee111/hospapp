package com.dydy.hospapp.batch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseDto {
    private Response response;
}

@Getter
@Setter
@ToString
@NoArgsConstructor
class Body {

    private Items items;
    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;

}

@Getter
@Setter
@ToString
@NoArgsConstructor
class Example {
    private Response response;
}

@Getter
@Setter
@ToString
@NoArgsConstructor
class Header {

    private String resultCode;
    private String resultMsg;
}

@Getter
@Setter
@ToString
@NoArgsConstructor
class Item {

    private String addr;
    private Integer mgtStaDd;
    private String pcrPsblYn;
    private String ratPsblYn;
    private Integer recuClCd;
    private Integer rnum;
    private String rprtWorpClicFndtTgtYn;
    private String sgguCdNm;
    private String sidoCdNm;
    private String telno;
    private Integer xPos;
    private Double xPosWgs84;
    private Integer yPos;
    private Double yPosWgs84;
    private String yadmNm;
    private String ykihoEnc;
}

@Getter
@Setter
@ToString
@NoArgsConstructor
class Items {

    private List<Item> item = new ArrayList<>();

}

@Getter
@Setter
@ToString
@NoArgsConstructor
class Response {

    private Header header;
    private Body body;
}