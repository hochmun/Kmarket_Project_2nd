/*
    날짜 : 2023/02/23
    이름 : 이해빈
    내용 : 관리자 배너 스크립트

*/

$(function() {


    $("#tabs").tabs();

    // 팝업 닫기
    $('.btnClose').click(function(){
        $(this).closest('.popup').removeClass('on');
    });

    // 배너등록 팝업 띄우기
    $('.btnRegister').click(function(e){
        e.preventDefault();
        $('#bannerRegister').addClass('on');
    });

    // 배너 활성화
    $(document).on('click', '.statusOff', function(e){

        e.preventDefault();

        //let no =

    });



/*
    $('#bannerRegister > div > section > article > form > div > input').click(function(e){
        window.location.reload();
    })

*/
});
