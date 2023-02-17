/*
    날짜 : 2023/02/10
    이름 : 이해빈
    내용 : 상품 view 자바스크립트
        - 2023/02/16 상품평보기 클릭시 스크롤 기능 추가
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


    // 리뷰 페이지 업데이트
    document.addEventListener('click', function(e){
        if(e.target.matches('div.paging a')){
            e.preventDefault(); // 링크의 동작을 막음
            updateReviewPage(e.target.href);
        }
    });
};


// 리뷰 페이지 업데이트 함수
function updateReviewPage(href){
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){

            let responseText = xhr.responseText; // 서버에서 응답 데이터를 불러움
            let parser = new DOMParser();
            let newDoc = parser.parseFromString(responseText, 'text/html'); // HTML 파싱
            let newPaging = newDoc.querySelector('.review'); // 새로운 페이징 영역 추출
            let oldPaging = document.querySelector('.review'); // 현재 페이징 영역 추출
            oldPaging.innerHTML = newPaging.innerHTML;
            console.log(newPaging);
        }
    };

    xhr.open('GET', href, true);
    xhr.send();

}


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

function increase(){

    let num = document.querySelector('input[name=num]');
    let count = parseInt(num.value) + 1;
    num.setAttribute('value', count);

    // 총 주문금액
    let total = parseInt(price * (1 - discount / 100) * count + delivery);
    total = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    document.querySelector('.total > span').innerHTML = total;

}

function decrease(){

    let num = document.querySelector('input[name=num]');

    if(num.value > 1) {
        let count = parseInt(num.value) - 1;
        num.setAttribute('value', count);

        // 총 주문금액
        let total = parseInt(price * (1 - discount / 100) * count + delivery);
        total = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        document.querySelector('.total > span').innerHTML = total;

    }

}

// 상품 리뷰란으로 이동
function goReview(){

    const location = document.querySelector('.review').offsetTop;
    window.scrollTo({top:location, behavior:'smooth'});

}

