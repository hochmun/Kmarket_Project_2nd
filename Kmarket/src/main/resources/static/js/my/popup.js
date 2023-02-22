$(function(){

    // 판매자 정보 팝업 띄우기
    $('.latest .info .company > a').click(function(e){
        e.preventDefault();

        // 판매자 uid 가져오기
        const sellerUid = $(this).parent().prev().text();

        // 가져온 회사 이름으로 판매자 정보 가져오기
        $.get('/Kmarket/my/home/getCompanyStatus?uid='+sellerUid,(data)=>{
            if(data.result > 0) {
                if(data.vo.level == 0) $('td[class=level]').text('탈퇴한 판매자 입니다.');
                if(data.vo.level == 1) $('td[class=level]').text('일반 판매자 입니다.');
                if(data.vo.level == 2) $('td[class=level]').text('실버 판매자 입니다.');
                if(data.vo.level == 3) $('td[class=level]').text('골드 판매자 입니다.');

                $('td[class=company]').text(data.vo.company);
                $('td[class=ceo]').text(data.vo.ceo);
                $('td[class=tel]').text(data.vo.tel);
                $('td[class=fax]').text(data.vo.fax);
                $('td[class=email]').text(data.vo.email);
                $('td[class=bizNum]').text(data.vo.bizRegNum);
                $('td[class=address]').text('['+(data.vo.zip).substr(0, 3)+'**] '+data.vo.addr1+' '+data.vo.addr2);

                $('#popSeller').addClass('on');
            } else {
                alert('판매자 불러오기 오류 입니다.');
            }
        });
    });

    // 문의하기 팝업 띄우기
    $('.btnQuestion').click(function(e){
        e.preventDefault();
        $('.popup').removeClass('on');
        $('#popQuestion').addClass('on');
    });

    // 주문상세 팝업 띄우기
    $('.latest .info .orderNo > a').click(function(e){
        e.preventDefault();

        // 주문 번호 가져오기
        const ordNo = $(this).text();

        $.get('/Kmarket/my/home/getOrderStatus?ordNo='+ordNo,(data)=>{
            if(data.result > 0) {
                let $tr1 = $('.order > table tr:nth-child(1)');
                let $tr2 = $('.order > table tr:nth-child(2)').clone(true);

                $('.order > table tr:gt(0)').remove();
                const formatter = new Intl.NumberFormat('ko');

                for(let vo of data.vos) {
                    let $tr3 = $tr2.clone(true);

                    $tr3.find('p[class=date]').text(vo.ordDate);
                    $tr3.find('.ordNo').text(vo.ordNo);
                    $tr3.find('.company').text(vo.company);
                    $tr3.find('.prodName').text(vo.prodName);
                    $tr3.find('.prodCount').text(vo.count);
                    $tr3.find('.prodPrice').text(formatter.format(vo.price) + '원');
                    $tr3.find('.price > span:last-child').text(formatter.format(vo.countPrice) + '원');
                    $tr3.find('.discount > span:last-child').text('- '+formatter.format(vo.disPrice) + '원');
                    $tr3.find('.total > span:last-child').text(formatter.format(vo.total) + '원');

                    console.log(vo.countPrice);

                    if(vo.ordComplete == 1) $tr3.find('.status').text('확인중');
                    if(vo.ordComplete == 2) $tr3.find('.status').text('배송중');
                    if(vo.ordComplete == 3) $tr3.find('.status').text('배송완료');
                    if(vo.ordComplete == 4) $tr3.find('.status').text('기타');
                    if(vo.ordComplete == 5) $tr3.find('.status').text('상품 구매 확정');

                    $tr1.after($tr3);
                }

                $('.delivery .name').text(data.vos[0].recipName);
                $('.delivery .hp').text(data.vos[0].recipHp);
                $('.delivery .address').text('['+(data.vos[0].recipZip).substr(0,3)+'**] '+data.vos[0].recipAddr1+' '+data.vos[0].recipAddr2);

            } else {
                alert('주문 정보 불러오기 실패');
            }
        });

        $('#popOrder').addClass('on');
    });

    // 수취확인 팝업 띄우기
    $('.latest .confirm > .receive').click(function(e){
        e.preventDefault();
        $('#popReceive').addClass('on');
    });

    // 상품평 작성 팝업 띄우기
    $('.latest .confirm > .review').click(function(e){
        e.preventDefault();
        $('#popReview').addClass('on');
    });

    // 팝업 닫기
    $('.btnClose').click(function(){
        $(this).closest('.popup').removeClass('on');
    });

    // 상품평 작성 레이팅바 기능
    $(".my-rating").starRating({
        starSize: 20,
        useFullStars: true,
        strokeWidth: 0,
        useGradient: false,
        minRating: 1,
        ratedColors: ['#ffa400', '#ffa400', '#ffa400', '#ffa400', '#ffa400'],
        callback: function(currentRating, $el){
            alert('rated ' + currentRating);
            console.log('DOM element ', $el);
        }
    });

});