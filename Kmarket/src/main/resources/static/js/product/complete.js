/*
    날짜 : 2023/02/13
    이름 : 이해빈
    내용 : 상품 complete 자바스크립트
*/

window.onload = function(){

    // 결제 방법
    const ordPayment = document.getElementById('ordPayment').getAttribute('data-payment');
    let payment = '';

    switch(ordPayment) {
        case '1' :
            payment = '신용카드';
            break;
        case '2' :
            payment = '체크카드';
            break;
        case '3' :
            payment = '실시간 계좌이체';
            break;
        case '4' :
            payment = '무통장 입금';
            break;
        case '5' :
            payment = '휴대폰 결제';
            break;
        case '6' :
            payment = '카카오 페이';
            break;
    }

    document.getElementById('ordPayment').innerText = payment;
};