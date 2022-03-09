// 검색 조건 설정 후 제출
document.querySelector("#btn-submit").addEventListener("click", (e) => {
    e.preventDefault(); // 새로고침 방지
    let siDo = document.querySelector("#siDo").value;
    let sggu = document.querySelector("#sggu").value;

    getHospital().then(console.log);
});

// API 호출로 JSON 데이터 불러오기
let getHospital = async (e) => {
    let siDo = document.querySelector("#siDo").value;
    let sggu = document.querySelector("#sggu").value;

    let response = await fetch(`http://localhost:8080/api/hospital?siDo=${siDo}&sggu=${sggu}`)
    let responseParsing = await response.json();
    setHospital(responseParsing);
};

// API 데이터로 새로운 리스트 만들기
let setHospital = (responseParsing) => {
    let hospTbodyDom = document.querySelector("#hosp-tbody");
    hospTbodyDom.innerHTML = "";

    let content = responseParsing.data.content;
    content.forEach((e) => {
        let trEL = document.createElement("tr");

        let tdEL1 = document.createElement("td");
        let tdEL2 = document.createElement("td");
        let tdEL3 = document.createElement("td");
        let tdEL4 = document.createElement("td");

        // td 태그에 텍스트를 넣을 떄는 innerHTML을 활용
        tdEL1.innerHTML = e.hospitalName;
        tdEL2.innerHTML = e.pcrYN;
        tdEL3.innerHTML = e.address;
        tdEL4.innerHTML = e.telNo;

        trEL.append(tdEL1);
        trEL.append(tdEL2);
        trEL.append(tdEL3);
        trEL.append(tdEL4);
        hospTbodyDom.append(trEL);
    });

}

// API 호출 불어온 데이터로 시, 군, 구 리스트 불러온 후 option 생성
let setSggu = (responseParsing) => {
    let sgguDom = document.querySelector("#sggu");
    // Option Element 초기화
    sgguDom.innerHTML = "";

    responseParsing.forEach((e) => {
        // option Element 생성
        let optionEL = document.createElement("option");
        optionEL.text = e;
        sgguDom.append(optionEL);
    });

    sgguDom.append('<option></option>')
};

// API 호출로 시, 군, 구 데이터 불러오기
let getSggu = async (siDo) => {
    // 1 옆에 `을 사용하면 변수 바인딩 가능
    let response = await fetch(`http://localhost:8080/api/sggu?siDo=${siDo}`)
    let responseParsing = await response.json();
    setSggu(responseParsing);
}

// 시, 도 선택시, 시, 군, 구 option 태그 변경 메서드
let siDoDom = document.querySelector("#siDo");
siDoDom.addEventListener('change', (e) => {
    let siDo = e.target.value;
    getSggu(siDo);
});