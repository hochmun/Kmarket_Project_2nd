$(function() {
  var visibleCount = 3;

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

    $faqList.find('li.more').click(function(e) {
      e.preventDefault();
      if (visibleCount === 3) {
        visibleCount = 10;
        $faqList.find('li.more a').text('접기');
      } else {
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

   console.log('1');

  const faqLists = document.querySelectorAll('.faq-list');


  faqLists.forEach((faqList)=>{

        console.log(faqList.childElementCount);

      if (faqList.childElementCount == 1) {

        console.log('2');
        const newLi = document.createElement('li');
        const newLink = document.createElement('a');

        newLink.textContent = '게시물이 없습니다.';

        newLi.appendChild(newLink);

        faqList.prepend(newLi);

      }
  });
  
})




