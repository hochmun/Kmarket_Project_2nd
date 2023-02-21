/*
    날짜 : 2023/02/21
    이름 : 이해빈
    내용 : 상품 search 자바스크립트
*/

window.onload = function(){

    document.querySelector('input[type=submit]').addEventListener('click', function(){

        let keywords = document.querySelector('input[name=search]').value;
        alert(keywords);

    });

}
