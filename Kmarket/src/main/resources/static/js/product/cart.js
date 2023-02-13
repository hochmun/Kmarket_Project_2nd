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
    });

    // 개별 체크박스를 누르면 전체 선택 해제
    for(let i=0; i < checkboxes.length; i++){

        checkboxes[i].addEventListener('click', ()=>{
            if(!this.checked){
            checkAll.checked = false;
            }
        });
    }

    // 선택 삭제
    document.getElementById('del').addEventListener('click', ()=> {

        let isDeleteOk = confirm('선택된 상품들을 장바구니에서 삭제하시겠습니까?');

        if(!isDeleteOk){
            return false;
        }

       // 선택된 상품들만 배열에 넣기
       let checkboxArr = [];
       for(let i=0; i < checkboxes.length; i++){
           if(checkboxes[i].checked){
               let no = checkboxes[i].value;
               checkboxArr.push(no);

               // 목록에서 숨기기
               let tr = checkboxes[i].parentNode.parentNode;
               tr.style.display = 'none';
           }
       }
        /*
        if(isDeleteOk){

            // AJAX 전송
            const xhr = new XMLHttpRequest();
            xhr.open('post','/Kmarket/product/deleteCart');
            xhr.responseType = "json";
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.send(JSON.stringify(jsonData));

            xhr.onreadystatechange = function(){

                if(xhr.readyState == XMLHttpRequest.DONE){
                    if(xhr.status == 200){
                        const data = xhr.response;
                        console.log(data);

                        if(data.result > 0){
                            location.href="Kmarket/product/cart";
                        }

                    }else{
                        alert('요청을 실패하였습니다.\n 잠시 후 다시 시도해 주세요.');
                    }
                }
            };
        }
        */

    });

    // 상품 정보 출력


}