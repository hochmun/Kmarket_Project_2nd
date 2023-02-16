/*
    날짜 : 2023/02/15
    이름 : 이해빈
    내용 : 상품 order 자바스크립트
*/
window.onload = function(){

    // 최종결제 정보 관련 번수
    const count = document.querySelector('.final > table tr:nth-child(1) > td:nth-child(2)');
    const price = document.querySelector('.final > table tr:nth-child(2) > td:nth-child(2)');
    const disprice = document.querySelector('.final > table tr:nth-child(3) > td:nth-child(2)');
    const delivery = document.querySelector('.final > table tr:nth-child(4) > td:nth-child(2)');
    const total = document.querySelector('.final > table tr:nth-child(6) > td:nth-child(2)');
    const point = document.querySelector('.final > table tr:nth-child(7) > td:nth-child(2)');
    const dispoint = document.querySelector('.final > table tr:nth-child(5) > td:nth-child(2)');


    // 체크 박스 관련 변수
    const checkAll = document.querySelector('input[name=all]');
    const checkboxes = document.querySelectorAll('input[name=chk]');

    // 체크박스 전체선택, 해제
    checkAll.addEventListener('click', ()=> {

        for(let i=0; i < checkboxes.length; i++){
            checkboxes[i].checked = checkAll.checked;
        }

        // 최종결제정보 전체합계 변경
        finalTotal();

    });

    // 개별 체크박스를 누르면 전체 선택 해제
    for(let i=0; i < checkboxes.length; i++){

        checkboxes[i].addEventListener('click', ()=>{
            if(!this.checked){
            checkAll.checked = false;
            }

            // 최종결제정보 전체합계 변경
            finalTotal();
        });
    }

    // 포인트 할인
    document.getElementById('pointDiscount').addEventListener('click', ()=>{

        let check = /^[0-9]+$/;
        let inputPoint = document.querySelector('input[name=point]').value;

        if(!check.test(inputPoint)){
            alert('숫자만 입력 가능합니다.');
            document.querySelector('input[name=point]').value = '0';
            return false;
        }

        // 포인트가 5000점 이하일때
        if(memberPoint < 5000){
            alert('5000 포인트 이상부터 현금처럼 사용 가능합니다.');
            document.querySelector('input[name=point]').value = '0';
            return false;
        }

        // 입력한 포인트가 갖고있는 포인트를 초과할 때
        if(inputPoint > memberPoint){
            alert(memberPoint + '점 포인트 이하까지 사용가능합니다.')
            document.querySelector('input[name=point]').value = '0';
            return false;
        }

        finalTotal();
    });


    // 주문하기
    document.getElementById('payment').addEventListener('click', ()=>{

        // 배송정보 관련 변수
        const recipName = document.querySelector('input[name=orderer]').value;
        const recipHp = document.querySelector('input[name=hp]').value;
        const recipZip = document.querySelector('input[name=zip]').value;
        const recipAddr1 = document.querySelector('input[name=addr1]').value;
        const recipAddr2 = document.querySelector('input[name=addr2]').value;


        let isOk = confirm('주문하시겠습니까?');

        if(!isOk){
            return false;
        }

        // 선택된 상품들만 배열에 넣기
        let checkboxArr = [];  // 장바구니 번호
        let prodNoArr = [];    // 상품번호
        for(let i=0; i < checkboxes.length; i++){
            if(checkboxes[i].checked){
                let no = checkboxes[i].value;
                let prodNo = checkboxes[i].getAttribute('data-prodNo');

                checkboxArr.push(no);
                prodNoArr.push(prodNo);

            }
        }

        if(checkboxArr == ''){
            alert('선택된 상품이 없습니다!')
            return false;
        }

        // 배송 정보
        if(recipName == ''){
            alert('수령자의 이름을 입력해주세요.');
            return false;
        }

        if(recipHp == ''){
            alert('수령자 휴대폰번호를 입력해주세요.');
            return false;
        }

        if(recipZip == ''){
            alert('배송지 우편번호를 입력해주세요.');
            return false;
        }

        if(recipAddr1 == ''){
            alert('배송지 주소를 입력하세요.');
            return false;
        }

        // 결제 방법
        let ordPayment = '';
        const payments = document.querySelectorAll('input[name=payment]');
        for(let i=0; i < payments.length; i++){
            if(payments[i].checked){
                ordPayment = payments[i].value.slice(-1);
            }
        }

        let orderinfo = {
            "ordCount" : newStr(count.innerText),
            "ordPrice" : newStr(price.innerText),
            "ordDiscount" : newStr(disprice.innerText),
            "ordDelivery" : newStr(delivery.innerText),
            "ordTotPrice" : newStr(total.innerText),
            "savePoint" : newStr(point.innerText),
            "usedPoint" : newStr(dispoint.innerText),
            "recipName" : recipName,
            "recipHp" : recipHp,
            "recipZip" : recipZip,
            "recipAddr1" : recipAddr1,
            "recipAddr2" : recipAddr2,
            "ordPayment" : ordPayment,
            "ordComplete" : 1,
            "prodNoArr" : prodNoArr
        }


        // AJAX 전송
        const xhr = new XMLHttpRequest();
        xhr.open('post','/Kmarket/product/order');
        xhr.responseType = "json";
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify({"checkboxArr": checkboxArr, "orderinfo" : orderinfo}));

        xhr.onreadystatechange = function(){

            if(xhr.readyState == XMLHttpRequest.DONE){
                if(xhr.status == 200){
                    const data = xhr.response;
                    console.log(data);

                    if(data.result > 0){
                        location.href = "/Kmarket/product/complete";
                    }

                }else{
                    alert('요청을 실패하였습니다.\n 잠시 후 다시 시도해 주세요.');
                }
            }
        };
    });


    // 상품 정보 출력
    const finalTotal = () => {

        // 계산에 필요한 변수
        let totCount = 0;
        let totPrice = 0;
        let totDisprice = 0;
        let totDelivery = 0;
        let totPoint = 0;
        let totTotal = 0;

        let _price = 0;
        let discount = 0;

        let d_point = 0;

        for(let i=0; i < checkboxes.length; i++){
            if(checkboxes[i].checked){

                let tr = checkboxes[i].parentNode.parentNode;
                let td = tr.childNodes;

                totCount += parseInt(td[5].innerText);

                _price = parseInt(td[7].innerText.replace(/,/g, ""));
                discount = parseInt(td[9].innerText.replace(/%/g, ""));

                totPrice += _price;
                totDisprice += parseInt(_price * (discount / 100));

                totPoint += parseInt(td[11].innerText.replace(/,/g, ""));;

                if(td[13].innerText == '무료배송'){
                    totDelivery += 0;
                }else{
                    totDelivery += parseInt(td[13].innerText.replace(/,/g, ""));
                }

                totTotal += parseInt(td[15].innerText.replace(/,/g, ""));

            }
        }

        // 전체 주문금액 사용하려는 포인트가 클 경우
        d_point = parseInt(document.querySelector('input[name=point]').value);

        if(totTotal - d_point < 0){
            alert('주문하시는 금액보다 적은 포인트를 사용해주세요.');
            document.querySelector('input[name=point]').value = 0;
            d_point = 0
        }else{
            // 크지 않은 경우
            totTotal -= d_point;
        }

        /*
        const count = document.querySelector('.final > table tr:nth-child(1) > td:nth-child(2)');
        const price = document.querySelector('.final > table tr:nth-child(2) > td:nth-child(2)');
        const disprice = document.querySelector('.final > table tr:nth-child(3) > td:nth-child(2)');
        const delivery = document.querySelector('.final > table tr:nth-child(4) > td:nth-child(2)');
        const total = document.querySelector('.final > table tr:nth-child(6) > td:nth-child(2)');
        const point = document.querySelector('.final > table tr:nth-child(7) > td:nth-child(2)');
        const dispoint = document.querySelector('.final > table tr:nth-child(5) > td:nth-child(2)');
        */


        count.innerText = totCount+'개';
        price.innerText = totPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';
        disprice.innerText = '-' +totDisprice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';
        delivery.innerText = totDelivery.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';
        point.innerText = totPoint.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'점';
        total.innerText = totTotal.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';
        dispoint.innerText = '-' +d_point.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';

    }

};

// 결제정보에서 숫자만 추출하는 함수
function newStr(str){
    let newstr = String(str).replace(/-/g, "").replace(/,/g, "").slice(0, -1);
    return newstr;
}