$(document).ready(()=>{
    const gnb = $('#gnb > li > a');

    gnb.click(function(e){
        e.preventDefault();

        const isOpen = $(this).next().is(':visible');

        if(isOpen){
            // 현재 서브메뉴를 닫음
            $(this).next().slideUp(200);
        } else {
            // 현재 서브메뉴를 펼침
            $(this).next().slideDown(200);
        }
    })
});