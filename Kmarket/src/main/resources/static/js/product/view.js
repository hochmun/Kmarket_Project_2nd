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


function order(type){

    let count = document.querySelector('input[name=num]').value;
    let total = price * (1 - discount / 100) * count;

    let jsonData = {
        "prodNo":prodNo,
        "prodName":prodName,
        "count":count,
        "price":price,
        "discount": discount,
        "point": point,
        "total": total,
        "delivery":delivery,
        "prodCate1":prodCate1,
        "prodCate2":prodCate2,
        "thumb1":thumb1,
        "descript":descript,
        "type":type
    }

    // AJAX 전송
    const xhr = new XMLHttpRequest();
    xhr.open('post','/Kmarket/product/goToOrder');
    xhr.responseType = "json";
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(jsonData));

    xhr.onreadystatechange = function(){

        if(xhr.readyState == XMLHttpRequest.DONE){
            if(xhr.status == 200){
                const data = xhr.response;
                console.log(data);

                if(data.result > 0){

                    if(type == 'cart'){
                        let goCart = confirm('물건이 장바구니에 담겼습니다. \n장바구니로 이동하시겠습니까?');
                        if(goCart){
                            location.href = "/Kmarket/product/cart";
                        }

                    }else if(type == 'product'){
                        location.href = "/Kmarket/product/order";
                    }
                }

            }else{
                alert('요청을 실패하였습니다.\n 잠시 후 다시 시도해 주세요.');
            }
        }
    };
};

