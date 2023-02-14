/*
    날짜 : 2023/02/10
    이름 : 이해빈
    내용 : 상품 view 자바스크립트
*/

window.onload = function(){

    // 배송 날짜 구하기
    let delivery = new Date();
    let arrival = document.querySelector('.arrival');

    let day = delivery.getDay();

    if(day == 5){ // 금요일일 때

        delivery.setDate(delivery.getDate()+4);

    }else if(day == 6 || 0){ // 주말일 때

        delivery.setDate(delivery.getDate()+3);

    }else { // 평일일 때

        delivery.setDate(delivery.getDate()+2);
    }

    let d1 = ['일', '월', '화', '수', '목', '금', '토'];

    let dateFormat = (delivery.getMonth()+1) + '/' + delivery.getDate() + '('+ d1[delivery.getDay()] +')  도착예정';

    arrival.innerHTML = dateFormat;


    // 상품 수량 더하기
    document.querySelector('.increase').addEventListener('click', ()=>{

        let num = document.querySelector('input[name=num]');
        let count = parseInt(num.value) + 1;
        num.setAttribute('value', count);

        // 총 주문금액
        let total = price * (1 - discount / 100) * count;
        total = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        document.querySelector('.total > span').innerHTML = total;

    });
    // 상품 수량 빼기
    document.querySelector('.decrease').addEventListener('click', ()=>{

        let num = document.querySelector('input[name=num]');

        if(num.value > 1) {
            let count = parseInt(num.value) - 1;
            num.setAttribute('value', count);

            // 총 주문금액
            let total = price * (1 - discount / 100) * count;
            total = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
            document.querySelector('.total > span').innerHTML = total;

        }
    });
};


