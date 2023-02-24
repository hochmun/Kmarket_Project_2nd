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

                $('input[name=pqnaReUid]').val(data.vo.uid);
                $('td[class=company]').text(data.vo.company);
                $('td[class=ceo]').text(data.vo.ceo);
                $('td[class=tel]').text(data.vo.tel);
                $('td[class=fax]').text(data.vo.fax);
                $('#popSeller td[class=email]').text(data.vo.email);
                $('td[class=bizNum]').text(data.vo.bizRegNum);
                $('#popSeller td[class=address]').text('['+(data.vo.zip).substr(0, 3)+'**] '+data.vo.addr1+' '+data.vo.addr2);

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

    // 문의하기 등록 버튼 클릭
    $('#popQuestion .btnPositive').click(function(e){
        e.preventDefault();

        const pqnaReUid = $('input[name=pqnaReUid]').val();
        const pqnaCate = $('input[name=pqnaCate]:checked').val();
        const pqnaEmail = $('input[name=pqnaEmail]').val();
        const pqnaTitle = $('input[name=pqnaTitle]').val();
        const pqnaContent = $('textarea[name=pqnaContent]').val();

        // 빈값 체크
        if(typeof pqnaCate == 'undefined' || pqnaEmail == '' || pqnaTitle == '' || pqnaContent == '') {
            alert('내용을 확인하여 주십시오');
            return;
        }

        const jsonContent = {"pqnaReUid"    :pqnaReUid,
                             "pqnaCate"     :pqnaCate,
                             "pqnaEmail"    :pqnaEmail,
                             "pqnaTitle"    :pqnaTitle,
                             "pqnaContent"  :pqnaContent};

        // 전송
        $.ajax({
            url: '/Kmarket/my/home/uploadProductQna',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(jsonContent),
            dataType: 'json',
            success: (data) => {
                if(data.result > 0) {
                    alert('문의 사항 작성 성공');
                    $('#popQuestion').removeClass('on');
                } else {
                    alert('문의 사항 작성 실패');
                }
            }
        });
    });

    // 문의하기 취소 버튼 클릭
    // 클릭시 판매자 정보 팝업 출력
    $('#popQuestion > div > section > form > div > button').click(function(e){
        e.preventDefault();
        $('#popQuestion').removeClass('on');
        $('#popSeller').addClass('on');
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

        // 주문 상태 가져와서 비교하기
        const ordStatus = $(this).parent().parent().find('.status').text();
        if(ordStatus == '상품 구매 확정') {
            alert('이미 수취 확인이 된 상품 입니다.');
            return;
        }

        // 주문번호 가져오기
        const ordNo = $(this).parent().parent().find('.orderNo > a').text();
        $('#popReceive > input[type=hidden]').val(ordNo);

        $('#popReceive').addClass('on');
    });

    // 수취확인 팝업 닫기
    $('#popReceive > div > section > div > button.btnNegative.btnCancel').click(function(e){
        e.preventDefault();
        $('#popReceive').removeClass('on');
    });

    // 수취확인 클릭시
    $('#popReceive > div > section > div > button.btnPositive.btnConfirm').click(function(e){
        e.preventDefault();

        // 주문 번호 가져오기
        const ordNo = $('#popReceive > input[type=hidden]').val();

        // 배열에 담기
        const jsonContent = {"ordNo":ordNo}

        // 포스트 전송
        $.ajax({
            url: '/Kmarket/my/home/receiptConfirm',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(jsonContent),
            dataType: 'json',
            success: (data) => {
                if(data.result > 0) {
                    alert('수취 확인이 완료 되었습니다.');
                } else {
                    alert('수취 확인을 실패 하였습니다.');
                }
            }
        });

        // 페이지 새로고침
        window.location.reload();
    });

    // 상품평 작성 팝업 띄우기
    $('.latest .confirm > .review').click(function(e){
        e.preventDefault();

        // 상품 번호 받기, 주문 번호 받기, 상품 명 받기
        const productNo = $(this).parent().parent().find('input[name=productNo]').val()
        const orderNo = $(this).parent().parent().find('.orderNo > a').text();
        const productName = $(this).parent().parent().find('.prodName > a').text();
        $('#popReview').find('input[name=reviewProdNo]').val(productNo);
        $('#popReview').find('input[name=reviewordNo]').val(orderNo);
        $('#popReview').find('td[class=productName]').text(productName);

        // 별점 초기화
        $("#popReview .my-rating").remove();
        $('#popReview .rating').html('<div class="my-rating"></div>');
        $("#popReview .my-rating").starRating({
            starSize: 20,
            useFullStars: true,
            strokeWidth: 0,
            useGradient: false,
            minRating: 1,
            ratedColors: ['#ffa400', '#ffa400', '#ffa400', '#ffa400', '#ffa400'],
            callback: function(currentRating, $el){
                $('input[name=starRating]').val(currentRating);
            }
        });

        $('#popReview').addClass('on');
    });

    // 상품평 작성 확인 클릭
    $('#popReview .btnPositive').click(function(e){
        e.preventDefault();

        const prodNo = $('#popReview').find('input[name=reviewProdNo]').val();
        const ordNo = $('#popReview').find('input[name=reviewordNo]').val();
        const starRating = $('#popReview').find('input[name=starRating]').val();
        const review = $('#popReview .review > textarea').val();

        // 상품 번호 없음
        if(prodNo == '') {
            alert('상품 정보 오류 입니다.')
            window.location.reload();
        }

        // 별점 선택 안함
        if(starRating == '') {
            alert('리뷰 하기전 별점을 선택하여 주십시오.')
            return;
        }

        const jsonContent = {"prodNo":prodNo,"ordNo":ordNo,"starRating":starRating,"review":review}

        // 포스트 전송
        $.ajax({
            url: '/Kmarket/my/home/insertReview',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(jsonContent),
            dataType: 'json',
            success: (data) => {
                if(data.result > 0) {
                    alert('리뷰 작성이 완료 되었습니다.');
                    window.location.reload();
                } else if(data.result == 0) {
                    alert('리뷰는 중복으로 작성 하실수 없습니다.');
                    window.location.reload();
                } else {
                    alert('리뷰 작성중 오류가 생겼습니다.');
                    window.location.reload();
                }
            }
        });
    });

    // 상품평 작성 팝업 닫기
    $('#popReview > div > section > form > div > button').click(function(e){
        e.preventDefault();
        $('#popReview').removeClass('on');
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