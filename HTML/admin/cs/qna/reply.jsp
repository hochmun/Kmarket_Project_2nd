<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../_header.jsp"/>
<script src="../js/qnaReply.js"></script>
<script>
$(()=>{
	$('.w-btn-green').click((e)=>{
		e.preventDefault();
		const qnaNo = "${ param.qnaNo }";
		const qnaAdminContent = $('textarea[name=AdminContent]').val();
		
		// 검사
		
		if(qnaAdminContent == null || qnaAdminContent == "") {
			alert('내용을 입력 하십시오.');
			return false;
		}
		
		// 데이터베이스 전송
		$.ajax({
			url: '/Kmarket/admin/cs/qna/reply.do',
			type: 'POST',
			data: {"qnaAdminContent":qnaAdminContent,"qnaNo":qnaNo},
			dataType: 'json',
			success: (data)=>{
				if(data.result > 0) {
					alert('게시물 수정 완료!');
					location.href = '/Kmarket/admin/cs/qna/list.do';
				} else {
					alert('게시물 수정 실패!');
					return false;
				}
			}
		});
		
	});
});
</script>
    <section id="admin-product-list">
                <nav>
                    <h3>문의하기 답변</h3>
                    <p>
                        HOME > 고객센터 > <strong>문의하기</strong>
                    </p>
                </nav>
 	<!-- 상품목록 컨텐츠 시작 -->                                
                <section class="AdminContent">
                <form action="/Kmarket/admin/cs/qna/reply.do" method="post">
                    <table>
                        <tr>
                            <td><strong>유형</strong></td>
                            <td>
                                ${vo.cate1Name} - ${vo.cate2Name}
                            </td>
                        </tr>
                        <tr>
                            <td>제목</td>
                            <td>
                               ${vo.qnaTitle}
                            </td>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td>
                               ${vo.qnaContent}     
                            </td>
                        </tr>
                        <tr>
                        	<td>답변</td>
                        	<td>
                                <textarea name="AdminContent" placeholder=""></textarea>
                            </td>
                        </tr>
                    </table>
                    <div class="btn3ro">
                        <a href="./delete.do?qnaNo=${vo.qnaNo}" class="w-btn w-btn-indigo btnDelete" id="delete">삭제</a>
                        <a href="#" class="w-btn w-btn-green btnSubmit">답변등록</a>
                        <a href="./list.do?cate1=${ param.cate1 }&cate2=${ param.cate2 }&pg=${param.pg}" class="w-btn w-btn-indigo btnList">목록</a>
                    </div>
                     </form>
                </section>                
                <p class="ico info">
                    <strong>Tip!</strong>
                    전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
                </p>
       
            </section>
    </div>
</section>
<jsp:include page="../../_footer.jsp"/>