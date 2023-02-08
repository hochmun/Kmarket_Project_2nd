<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../_header.jsp"/>
<script>
$(()=>{
	$('.btnNoticeDelete').click(function(e){
		e.preventDefault();
		const deleteOk = confirm('삭제 하시겠습니까?');
		if(deleteOk == false) return false;
		else $.ajax({
			url:'/Kmarket/admin/cs/notice/delete.do',
			type: 'get',
			data: {"n":${param.n},"t":2},
			dataType: 'json',
			success: (data)=>{
				if(data.result > 0) {
					alert('게시물 삭제에 성공 했습니다.');
					location.href = '/Kmarket/admin/cs/notice/list.do';
				} else {
					alert('게시물 삭제에 실패 했습니다.');
					return false;
				}
			}
		});
	});
});
</script>
    <section id="admin-product-list">
                <nav>
                    <h3>공지사항 보기</h3>
                    <p>
                        HOME > 고객센터 > <strong>공지사항</strong>
                    </p>
                </nav>
 	<!-- 상품목록 컨텐츠 시작 -->                                
                <section>
                   
                    <table>
                        <tr>
                            <td>유형</td>
                            <td>
                            	<c:if test="${ vo.noticeCate eq 10 }">고객서비스</c:if>
                            	<c:if test="${ vo.noticeCate eq 11 }">안전거래</c:if>
                            	<c:if test="${ vo.noticeCate eq 12 }">위해상품</c:if>
                            	<c:if test="${ vo.noticeCate eq 13 }">이벤트상품</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>제목</td>
                            <td>
                               ${ vo.noticeTitle }
                            </td>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td>
                               ${ vo.noticeContent }    
                            </td>
                        </tr>
                    </table>
                    <div class="btn3ro">
                        <a href="#" class="w-btn w-btn-indigo btnNoticeDelete">삭제</a>
                        <a href="/Kmarket/admin/cs/notice/modify.do?n=${ vo.noticeNo }" class="w-btn w-btn-green">수정</a>
                        <a href="/Kmarket/admin/cs/notice/list.do?p=${ param.p }&t=${ param.t }"  class="w-btn w-btn-indigo">목록</a>
                    </div>
       
                </section>                
                <p class="ico info">
                    <strong>Tip!</strong>
                    전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
                </p>
       
            </section>
</section>
</main>
<jsp:include page="../../_footer.jsp"/>