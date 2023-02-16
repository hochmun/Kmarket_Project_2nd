/*
    날짜 : 2023/02/13
    이름 : 이해빈
    내용 : 상품 cart 자바스크립트
*/

window.onload = function(){

    const checkAll = document.querySelector('input[name=all]');
    const checkboxes = document.querySelectorAll('input[name=chk]');

    // 체크박스 전체선택, 해제
    checkAll.addEventListener('click', ()=> {

        for(let i=0; i < checkboxes.length; i++){
            checkboxes[i].checked = checkAll.checked;
        }

        // 장바구니 전체합계 변경
        cartTotal();

    });

    // 개별 체크박스를 누르면 전체 선택 해제
    for(let i=0; i < checkboxes.length; i++){

        checkboxes[i].addEventListener('click', ()=>{
            if(!this.checked){
            checkAll.checked = false;
            }

            // 장바구니 전체합계 변경
            cartTotal();
        });
    }

    // 상품 정보 출력
    const cartTotal = () => {

        // 계산에 필요한 변수
        let totCount = 0;
        let totPrice = 0;
        let totDisprice = 0;
        let totDelivery = 0;
        let totPoint = 0;
        let totTotal = 0;

        let _price = 0;
        let discount = 0;

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

        const count = document.querySelector('.total > table tr:nth-child(1) > td:nth-child(2)');
        const price = document.querySelector('.total > table tr:nth-child(2) > td:nth-child(2)');
        const disprice = document.querySelector('.total > table tr:nth-child(3) > td:nth-child(2)');
        const delivery = document.querySelector('.total > table tr:nth-child(4) > td:nth-child(2)');
        const total = document.querySelector('.total > table tr:nth-child(5) > td:nth-child(2)');
        const point = document.querySelector('.total > table tr:nth-child(6) > td:nth-child(2)');

        count.innerText = totCount+'개';
        price.innerText = totPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';
        disprice.innerText = '-' +totDisprice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';
        delivery.innerText = totDelivery.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';
        point.innerText = totPoint.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'점';
        total.innerText = totTotal.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';

    }


    // 선택삭제
    document.getElementById('del').addEventListener('click', ()=> {
        checkbox('deleteCart');
        //alert('del');
    });

    // 선택주문
    document.getElementById('orderProduct').addEventListener('click', ()=> {
        checkbox('goOrder');
        //alert('order');
    });


    function checkbox(type){

        let Ok = null;

        if(type == 'deleteCart'){
           Ok = confirm('선택된 상품들을 장바구니에서 삭제하시겠습니까?');
        }else if(type == 'goOrder'){
            Ok = confirm('선택한 상품들을 주문하시겠습니까?');
        }

        if(!Ok){
            return false;
        }

        // 선택된 상품들만 배열에 넣기
        let checkboxArr = [];
        for(let i=0; i < checkboxes.length; i++){


            if(checkboxes[i].checked){
                let no = checkboxes[i].value;
                checkboxArr.push(no);

                if(type == 'deleteCart'){

                    // 목록에서 숨기기
                    let tr = checkboxes[i].parentNode.parentNode;
                    tr.style.display = 'none';

                    // 전체합계에서 빼기
                    checkboxes[i].checked = false;
                    cartTotal();

                }
            }


        }

        if(checkboxArr == ''){
            alert('선택된 상품이 없습니다!')
            return false;
        }

        if(Ok){

            // AJAX 전송
            const xhr = new XMLHttpRequest();
            xhr.open('post','/Kmarket/product/' + type);
            xhr.responseType = "json";
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.send(JSON.stringify({"checkboxArr": checkboxArr}));

            xhr.onreadystatechange = function(){

                if(xhr.readyState == XMLHttpRequest.DONE){
                    if(xhr.status == 200){
                        const data = xhr.response;
                        console.log(data);

                        if(data.result > 0){

                            if(type == 'deleteCart'){
                                alert('장바구니에서 상품을 삭제하였습니다.');
                            }else if(type == 'goOrder'){
                                location.href = "/Kmarket/product/order";
                            }
                        }

                    }else{
                        alert('요청을 실패하였습니다.\n 잠시 후 다시 시도해 주세요.');
                    }
                }
            };
        }
    }
}
