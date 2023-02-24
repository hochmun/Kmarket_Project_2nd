/*
    날짜 : 2023/02/21
    이름 : 이해빈
    내용 : 상품 search 자바스크립트
*/

window.onload = function(){


    document.querySelector('input[type=submit]').addEventListener('click', function(e){


        let keyword = document.getElementById('keyword').innerText;
        let keywords = document.querySelector('input[name=keywords]').value;
        let min = document.querySelector('input[name=min]').value;
        let max = document.querySelector('input[name=max]').value;


        if(keywords == ''){
            alert('검색어를 입력해 주세요');
            e.preventDefault();
            return false;
        }

        // 상품가격 유효성 검사
        let check = /^[0-9]+$/;

        if(!check.test(min) && min != ''){
            alert('숫자만 입력 가능합니다.');
            e.preventDefault();
            return false;
        } else {
            min = parseInt(min)
        }
        if(!check.test(max) && max != ''){
            alert('숫자만 입력 가능합니다.');
            e.preventDefault();
            return false;
        } else {
            max = parseInt(max)
        }

        if(max < min && max != ''){
            alert(min+'원 보다 큰 금액을 입력해 주세요.');
            e.preventDefault();
            return false;
        }

    });



    /*
    document.querySelector('input[type=submit]').addEventListener('click', function(e){
        e.preventDefault();

        let keyword = document.getElementById('keyword').innerText;
        let keywords = document.querySelector('input[name=search]').value;
        let min = document.querySelector('input[name=min]').value;
        let max = document.querySelector('input[name=max]').value;

        let chk1 = document.querySelector('input[name=chk1]').checked;
        let chk2 = document.querySelector('input[name=chk2]').checked;
        let chk3 = document.querySelector('input[name=chk3]').checked;

        if(keywords == ''){
            alert('검색어를 입력해 주세요');
            return false;
        }

        // 상품가격 유효성 검사
        let check = /^[0-9]+$/;

        if(!check.test(min) && min != ''){
            alert('숫자만 입력 가능합니다.');
            return false;
        } else {
            min = parseInt(min)
        }
        if(!check.test(max) && max != ''){
            alert('숫자만 입력 가능합니다.');
            return false;
        } else {
            max = parseInt(max)
        }

        if(max < min && max != ''){
            alert(min+'원 보다 큰 금액을 입력해 주세요.');
            return false;
        }

        let jsonData = {
            "keyword" : keyword,
            "keywords" : keywords,
            "min"      : min,
            "max"      : max,
            "chk1"     : chk1,
            "chk2"     : chk2,
            "chk3"     : chk3
        }

        console.log(jsonData);


        // AJAX 전송
        const xhr = new XMLHttpRequest();
        xhr.open('post','/Kmarket/product/search');
        xhr.responseType = "json";
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify(jsonData));

        xhr.onreadystatechange = function(){

            if(xhr.readyState == XMLHttpRequest.DONE){
                if(xhr.status == 200){
                    const data = xhr.response;
                    console.log("data : "+data);
                }else{
                    alert('요청을 실패하였습니다.\n 잠시 후 다시 시도해 주세요.');
                }
            }
        };
    });
    */

}
