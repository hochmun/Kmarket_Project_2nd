/*
    김재준 // cs/faq/list 스크립트
*/
$(function() {
  // FAQ 리스트 더보기
  // 3개만 보이고 나머지는 숨김
  var visibleCount = 3;

  // 각 리스트의 개수를 구함
  $('.faq-list').each(function() {
    var $faqList = $(this);
    var $listItems = $faqList.find('li');
    var itemCount = $listItems.length;
    var remainingCount = itemCount - visibleCount;

    $listItems.each(function(i, item) {
      if (i >= visibleCount) {
        $(this).hide();
      }
    });
    // 더보기 버튼을 생성하고 더보기 버튼을 클릭하면 나머지 리스트를 보여줌
    $faqList.find('li.more').click(function(e) {
      e.preventDefault();
      if (visibleCount === 3) {
      // 더보기 버튼을 클릭하면 더보기 버튼의 텍스트를 접기로 변경
        visibleCount = 10;
        $faqList.find('li.more a').text('접기');
      } else {
      // 접기 버튼을 클릭하면 더보기 버튼의 텍스트를 더보기로 변경
        visibleCount = 3;
        $faqList.find('li.more a').text('더보기');
      }

      $listItems.each(function(i, item) {
        if (i < visibleCount) {
          $(this).show();
        } else {
          $(this).hide();
        }
      });

      if (visibleCount == itemCount) {
        $faqList.find('li.more').hide();
      } else {
        $faqList.find('li.more').show();
      }
    });
  });
});

window.addEventListener('load',()=>{
// 게시물이 없을 경우 게시물이 없습니다. 라는 문구를 출력
  const faqLists = document.querySelectorAll('.faq-list');

  faqLists.forEach((faqList)=>{
      if (faqList.childElementCount == 1) {

        const newLi = document.createElement('li');
        const newLink = document.createElement('a');

        newLink.textContent = '게시물이 없습니다.';

        newLi.appendChild(newLink);

        faqList.prepend(newLi);

      }
  });
});




