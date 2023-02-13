$(()=>{
    // 검색 기능
	$('input[name=btnSearch]').click(()=>{
		const s = $('input[name=search]').val();
		const st = $('select[name=searchType]').val();

		location.href = '/Kmarket/admin/product/list/1/?s='+s+'&st='+st;
	});

	// 개별 삭제 기능
	$('.deleteProduct').click(function(e){
		e.preventDefault();
		const deleteOk = confirm('상품을 제거 하시겠습니까?');
		if(deleteOk == false) return false;
		const tr = $(this).parent().parent();
		const prodNo = tr.children().eq(2).text();
		$.get('/Kmarket/admin/product/deleteProduct?prodNo='+prodNo,(data)=>{
            if(data.result > 0) {
                alert('상품 삭제 완료 했습니다.');
                tr.remove();
            } else {
                alert('상품 삭제에 실패 하였습니다.');
            }
        });
	});

	// 다중 삭제 기능
	$('#deleteAll').click(()=>{
		const deleteOk = confirm('선택한 상품을 삭제 하시겠습니까?');
		if(deleteOk == false) return false;
		const trs = $('input[name=상품코드]:checked').parent().parent();
		let prodNo = "";
		for(tr of trs) {
			prodNo += tr.children[2].innerText;
			prodNo += ", ";
		}
		$.get('/Kmarket/admin/product/deleteProduct?prodNo='+prodNo,(data)=>{
            if(data.result > 0) {
                alert('상품 삭제 완료 했습니다.');
                trs.remove();
            } else {
                alert('상품 삭제에 실패 하였습니다.');
            }
        });
	});

	// 수정 기능
	/*
	$('.modifyProduct').click(function(e){
	    e.preventDefault();
	    let status = $(this).text();
	    const tr = $(this).parent().parent();

	    if(status == "[수정]"){
	        $(this).text("[수정 완료]");

	        tr.children().eq(3).attr('contenteditable', true);
	        tr.children().eq(4).attr('contenteditable', true);
	        tr.children().eq(5).attr('contenteditable', true);
	        tr.children().eq(7).attr('contenteditable', true);
	    } else {
	        $(this).text("[수정]");

            tr.children().eq(3).attr('contenteditable', false);
            tr.children().eq(4).attr('contenteditable', false);
            tr.children().eq(5).attr('contenteditable', false);
            tr.children().eq(7).attr('contenteditable', false);

            const prodName = tr.children().eq(3).text();
            const price = tr.children().eq(4).text();
            const discount = tr.children().eq(5).text();
            const stock = tr.children().eq(7).text();
            const prodNo = tr.children().eq(3).text();

            $.ajax({
                url:'/Kmarket/admin/product/modifyProduct',
                type: 'PUT',
                data: {'prodName':prodName,'price':price,'discount':discount,'stock':stock, 'prodNo':prodNo},
                dataType: 'json',
                success: (data)=>{
                    if(data.result > 0) {
                        alert('상품 수정이 완료 했습니다.');
                    } else {
                        alert('상품 수정에 실패 하였습니다.');
                    }
                }
            });
	    }
	});
	*/
});