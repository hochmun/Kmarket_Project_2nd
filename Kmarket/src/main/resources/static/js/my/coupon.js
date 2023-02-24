/*
    날짜 : 2023/02/22
    이름 : 이해빈
    내용 : 마이페이지 쿠폰 자바스크립트
*/



$(document).ready(function(){

    let now = new Date();
    let Edates = $('.expire');

    let statusArr = $('.status');

    statusArr.each(function(i,e){

        let edate = $(this).prev('.expire').data("edate");
        let status = $(this).data("status");

        let Couponstatus = '';

        if(status == 2){
            Couponstatus = '사용완료';
        }else if(Date.parse(edate) < now){
            Couponstatus = '사용불가';
        }else{
            Couponstatus = '사용가능';
        }

        $(this).text(Couponstatus);

    });
});