// 정규 표현식
const reNum = /^[0-9]+$/; // 숫자만 허용

let isPriceOk = false; // 기본 null
let isDiscountOk = true; // 기본 0
let isStockOk = true;
let isDeliveryOk = true;
let isThumb1Ok = false; // 이미지 체크
let isThumb2Ok = false;
let isThumb3Ok = false;
let isDetailOk = false;

const maxSize = 1024 * 1000; // 이미지 각각 업로드시 최대 크기 1MB

$(()=>{
	// 카테고리1값 변경시
	$('select[name=prodCate1]').change(()=>{
		const cate1 = $('select[name=prodCate1]').val();

		// 카테고리2값 넣기전 비우기
		$('select[name=prodCate2]').empty();
        $('select[name=prodCate2]').append("<option value='cate0'>2차 분류 선택</option>");

        // 카테1값 없을경우 실행 안함 => 오류 로그 표시 안하게 할려고
		if (cate1 == "") return;

		$.get('/Kmarket/admin/product/getCate2?cate1='+cate1,(data)=>{
			for(let vo of data.cate2s) {
				// 카테고리2값 입력
				$('select[name=prodCate2]').append("<option value='"+vo.cate2+"'>"+vo.c2Name+"</option>");
			}
		});
	});
	
	// 유효성 검사
	// 판매가격 유효성 검사
	$('input[name=price]').change(function(){
		const price = $(this).val();
		
		if(price.match(reNum)) {
			isPriceOk = true;
			$('.priceResult').text('');
		} else {
			isPriceOk = false;
			$('.priceResult').css('color','red').text('숫자만 입력할 수 있습니다.');
		}
	});
	
	// 할인율 유효성 검사
	$('input[name=discount]').change(function(){
		const discount = $(this).val();
		if(discount.match(reNum)) {
			isDiscountOk = true;
			$('.discountResult').text('');
		} else {
			isDiscountOk = false;
			$('.discountResult').css('color','red').text('숫자만 입력할 수 있습니다.');
		}
	})
	
	// 재고수량 유효성 검사
	$('input[name=stock]').change(function(){
		const stock = $(this).val();
		if(stock.match(reNum)) {
			isStockOk = true;
			$('.stockResult').text('');
		} else {
			isStockOk = false;
			$('.stockResult').css('color','red').text('숫자만 입력할 수 있습니다.');
		}
	})
	
	// 배송비 유효성 검사
	$('input[name=delivery]').change(function(){
		const delivery = $(this).val();
		if(delivery.match(reNum)) {
			isDeliveryOk = true;
			$('.deliveryResult').text('');
		} else {
			isDeliveryOk = false;
			$('.deliveryResult').css('color','red').text('숫자만 입력할 수 있습니다.');
		}
	})
	
	// 파일 190x190 크기체크
	$('input[name=thumb1File]').change(function(){
		var ext = $(this).val().split(".").pop().toLowerCase();
    
	    if($.inArray(ext,["gif","jpg","jpeg","png","bmp"]) == -1) {
	        alert("gif, jpg, jpeg, png, bmp 파일만 업로드 해주세요.");
	        $("input[name=thumb1File]").val("");
	        isThumb1Ok = false;
	        return;
	    }
	    
	    var fileSize = this.files[0].size;
	    if(fileSize > maxSize) {
	        alert("파일용량을 초과하였습니다.");
	        $("input[name=thumb1File]").val("");
	        isThumb1Ok = false;
	        return;
	    }
	    
	    var file  = this.files[0];
	    var _URL = window.URL || window.webkitURL;
	    var img = new Image();
	    
	    img.src = _URL.createObjectURL(file);
	    img.onload = function() {
	        
	        if(img.width != 190 || img.height != 190) {
	            alert("이미지 가로 190px, 세로 190px로 맞춰서 올려주세요.");
	            $("input[name=thumb1File]").val("");
	            isThumb1Ok = false;
	            return;
	        } 
	    }
	    
	    isThumb1Ok = true;
	});
	
	// 파일 230x230 크기체크
	$('input[name=thumb2File]').change(function(){
		var ext = $(this).val().split(".").pop().toLowerCase();
    
	    if($.inArray(ext,["gif","jpg","jpeg","png","bmp"]) == -1) {
	        alert("gif, jpg, jpeg, png, bmp 파일만 업로드 해주세요.");
	        $("input[name=thumb2File]").val("");
	        isThumb2Ok = false;
	        return;
	    }
	    
	    var fileSize = this.files[0].size;
	    if(fileSize > maxSize) {
	        alert("파일용량을 초과하였습니다.");
	        $("input[name=thumb2File]").val("");
	        isThumb2Ok = false;
	        return;
	    }
	    
	    var file  = this.files[0];
	    var _URL = window.URL || window.webkitURL;
	    var img = new Image();
	    
	    img.src = _URL.createObjectURL(file);
	    img.onload = function() {
	        
	        if(img.width != 230 || img.height != 230) {
	            alert("이미지 가로 230px, 세로 230px로 맞춰서 올려주세요.");
	            $("input[name=thumb2File]").val("");
	            isThumb2Ok = false;
	            return;
	        } 
	    }
	    
	    isThumb2Ok = true;
	});
	
	// 파일 456x456 크기체크
	$('input[name=thumb3File]').change(function(){
		var ext = $(this).val().split(".").pop().toLowerCase();
    
	    if($.inArray(ext,["gif","jpg","jpeg","png","bmp"]) == -1) {
	        alert("gif, jpg, jpeg, png, bmp 파일만 업로드 해주세요.");
	        $("input[name=thumb3File]").val("");
	        isThumb3Ok = false;
	        return;
	    }
	    
	    var fileSize = this.files[0].size;
	    if(fileSize > maxSize) {
	        alert("파일용량을 초과하였습니다.");
	        $("input[name=thumb3File]").val("");
	        isThumb3Ok = false;
	        return;
	    }
	    
	    var file  = this.files[0];
	    var _URL = window.URL || window.webkitURL;
	    var img = new Image();
	    
	    img.src = _URL.createObjectURL(file);
	    img.onload = function() {
	        
	        if(img.width != 456 || img.height != 456) {
	            alert("이미지 가로 456px, 세로 456px로 맞춰서 올려주세요.");
	            $("input[name=thumb3File]").val("");
	            isThumb3Ok = false;
	            return;
	        } 
	    }
	    
	    isThumb3Ok = true;
	});
	
	// 파일 가로 940 크기체크
	$('input[name=detailFile]').change(function(){
		var ext = $(this).val().split(".").pop().toLowerCase();
    
	    if($.inArray(ext,["gif","jpg","jpeg","png","bmp"]) == -1) {
	        alert("gif, jpg, jpeg, png, bmp 파일만 업로드 해주세요.");
	        $("input[name=detailFile]").val("");
	        isDetailOk = false;
	        return;
	    }
	    
	    var fileSize = this.files[0].size;
	    if(fileSize > maxSize) {
	        alert("파일용량을 초과하였습니다.");
	        $("input[name=detailFile]").val("");
	        isDetailOk = false;
	        return;
	    }
	    
	    var file  = this.files[0];
	    var _URL = window.URL || window.webkitURL;
	    var img = new Image();
	    
	    img.src = _URL.createObjectURL(file);
	    img.onload = function() {
	        
	        if(img.width != 940) {
	            alert("이미지 가로 940px로 맞춰서 올려주세요.");
	            $("input[name=detailFile]").val("");
	            isDetailOk = false;
	            return;
	        } 
	    }
	    
	    isDetailOk = true;
	});
	
	// 최종 폼 전송
	$('#admin-product-register form').submit(()=>{
		// cate1 값이 cate0 일뗴 경고
		const cate1 = $('select[name=prodCate1]').val();
		if(cate1 == 'cate0') {
			alert('카테고리를 선택하십시오.');
			return false;
		}
		// cate2 값이 cate0 일때 경고
		const cate2 = $('select[name=prodCate2]').val();
		if(cate2 == 'cate0') {
			alert('카테고리 2차 분류를 선택하십시오.');
			return false;
		}
		// 상품명값이 없을경우 경고
		const prodName = $('input[name=prodName]').val();
		if(prodName == null || prodName == '') {
			alert('상품명을 입력하시오.');
			return false;
		}
		// 상품기본 설명
		const descript = $('input[name=descript]').val();
		if(descript == null || descript == '') {
			alert('상품 기본설명을 입력하시오.');
			return false;
		}
		// 제조사
		const company = $('input[name=company]').val();
		if(company == null || company == '') {
			alert('상품의 제조사를 입력하시오.');
			return false;
		}
		// 판매가격
		const price = $('input[name=price]').val();
		if(price == null || price == '') {
			alert('상품의 판매가격을 입력하시오.');
			return false;
		} else if (!isPriceOk) {
			alert('상품 판매가격 입력이 유효하지 않습니다.');
			return false;
		}
		// 할인율
		const discount = $('input[name=discount]').val();
		if(discount == null || discount == '') {
			alert('상품의 할인율을 입력하시오.');
			return false;
		} else if (!isDiscountOk) {
			alert('상품 할인율 입력이 유효하지 않습니다.');
			return false;
		}
		// 재고수량
		const stock = $('input[name=stock]').val();
		if(stock == null || stock == '') {
			alert('상품의 재고수량을 입력하시오.');
			return false;
		} else if (!isStockOk) {
			alert('상품 재고수량의 입력이 유효하지 않습니다.');
			return false;
		}
		// 배송비
		const delivery = $('input[name=delivery]').val();
		if(delivery == null || delivery == '') {
			alert('상품의 배송비를 입력하시오.');
			return false;
		} else if (!isDeliveryOk) {
			alert('상품 배송비의 입력이 유효하지 않습니다.');
			return false;
		}
		// thumb1
		const thumb1 = $('input[name=thumb1File]').val();
		if(thumb1 == null || thumb1 == '') {
			alert('상품 사진이 등록 되지 않았습니다.');
			return false;
		} else if (!isThumb1Ok) {
			alert('상품 사진이 유효하지 않습니다.');
			return false;
		}
		// thumb2
		const thumb2 = $('input[name=thumb2File]').val();
		if(thumb2 == null || thumb2 == '') {
			alert('상품 사진이 등록 되지 않았습니다.');
			return false;
		} else if (!isThumb2Ok) {
			alert('상품 사진이 유효하지 않습니다.');
			return false;
		}
		// thumb3
		const thumb3 = $('input[name=thumb3File]').val();
		if(thumb3 == null || thumb3 == '') {
			alert('상품 사진이 등록 되지 않았습니다.');
			return false;
		} else if (!isThumb3Ok) {
			alert('상품 사진이 유효하지 않습니다.');
			return false;
		}
		// detail
		const detail = $('input[name=detailFile]').val();
		if(detail == null || detail == '') {
			alert('상품 상세 정보 사진이 등록 되지 않았습니다.');
			return false;
		} else if (!isDetailOk) {
			alert('상품 사진이 유효하지 않습니다.');
			return false;
		}
		
		// status
		const status = $('input[name=status]').val();
		if(status == null || status == '') {
			alert('상품 상태가 등록 되지 않았습니다.');
			return false;
		}
		
		// duty
		const duty = $('input[name=duty]').val();
		if(duty == null || duty == '') {
			alert('부가세 면세여부가 등록 되지 않았습니다.');
			return false;
		}
		
		// receipt
		const receipt = $('input[name=receipt]').val();
		if(receipt == null || receipt == '') {
			alert('영수증발행여부가 등록 되지 않았습니다.');
			return false;
		}
		
		// bizType
		const bizType = $('input[name=bizType]').val();
		if(bizType == null || bizType == '') {
			alert('사업자구분이 등록 되지 않았습니다.');
			return false;
		}
		
		// origin
		const origin = $('input[name=origin]').val();
		if(origin == null || origin == '') {
			alert('원산지가 등록 되지 않았습니다.');
			return false;
		}
		
		// 유효성 검사 완료
		return true;
	});
});
