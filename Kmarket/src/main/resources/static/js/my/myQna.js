
/**
* 23/02/21 // 김재준 //
*/

// QnA 목록에서 제목을 클릭하면 답변이 나오도록 하는 함수
$(document).ready(function() {
    $('.tit a').click(function(event) {
        event.preventDefault(); // 기본 동작 취소
        var qnaTitle = $(this).text();
        var answerRow = $(this).parents('tr').next('.answerRow');
        if (answerRow.is(':visible')) {
            answerRow.hide();
        } else {
            answerRow.show();
        }
    });
});

// QnA 목록에서 답변이 없는 경우 답변 부분을 숨기는 함수
$(function() {
    $(".answer").each(function() {
        if ($(this).find(".content").text().trim() === "") {
          $(this).hide();
        }
    });
});