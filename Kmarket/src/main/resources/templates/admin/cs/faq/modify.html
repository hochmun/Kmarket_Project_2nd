<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../_header.jsp"/>
<script>
$(()=>{
	$('.w-btn-green').click((e)=>{
		e.preventDefault();
		const faqNo = "${ param.faqNo }";
		const cate1 = $('select[name=cate1]').val();
		const cate2 = $('select[name=cate2]').val();
		const title = $('input[name=title]').val();
		const content = $('textarea[name=content]').val();
		
		// 검사
		if(cate1 == null || cate1 == "") {
			alert('카테고리1을 선택하십시오');
			return false;	
		}
		
		if(cate2 == null || cate2 == "") {
			alert('카테고리2를 선택하십시오.');
			return false;
		}
		
		if(title == null || title == "") {
			alert('제목을 입력 하십시오.');
			return false;
		}
		
		if(content == null || content == "") {
			alert('내용을 입력 하십시오.');
			return false;
		}
		
		// 데이터베이스 전송
		$.ajax({
			url: '/Kmarket/admin/cs/faq/modify.do',
			type: 'POST',
			data: {"cate1":cate1,"cate2":cate2,"title":title,"content":content,"faqNo":faqNo},
			dataType: 'json',
			success: (data)=>{
				if(data.result > 0) {
					alert('게시물 수정 완료!');
					location.href = '/Kmarket/admin/cs/faq/list.do';
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
                    <h3>자주묻는질문 수정</h3>
                    <p>
                        HOME > 고객센터 > <strong>자주묻는질문</strong>
                    </p>
                </nav>
 	<!-- 상품목록 컨텐츠 시작 -->                                
                <section>
                   
                    <table>
                        <tr>
                            <td>유형</td>
                            <td>
                                <select class="w-btn-outline w-btn-indigo-outline" name="cate1">
                                	
                                	<c:forEach var="vo2" items="${ vos2 }">
                                		<option value="${ vo2.cate1 }" ${ vo2.cate1 eq vo.faqCate1 ? 'selected' : '' } >${ vo2.cate1Name }</option>
                                	</c:forEach>
                                	
                                </select>
                                
                                <select class="w-btn-outline w-btn-indigo-outline" name="cate2">
                                	
                                	<c:forEach var="vo3" items="${ vos3 }" >
                                		<option value="${ vo3.cate2 }" ${ vo3.cate2 eq vo.faqCate2 ? 'selected' : '' } >${ vo3.cate2Name }</option>
                                	</c:forEach>
                                	
                                </select>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>제목</td>
                            <td>
                                <input type="text" name="title" class="w-btn-outline w-btn-indigo-outline" placeholder="제목을 입력하세요."
                                value="${ vo.faqTitle }">
                            </td>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td>
                                <pre style="display: grid;">
                                	<textarea name="content" class="w-btn-outline w-btn-indigo-outline" placeholder="내용을 입력하세요." style="display: block;">${ vo.faqContent }</textarea>
                                </pre>
                            </td>
                        </tr>
                    </table>
                    <div class="btn2ro">
                        <a href="./list.do" class="w-btn w-btn-indigo">취소하기</a>
                        <a href="#" class="w-btn w-btn-green">수정하기</a>
                    </div>
                </section>         
                <p class="ico info">
                    <strong>Tip!</strong>
                    전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
                </p>      
            </section>
    </div>
</section>
<jsp:include page="../../_footer.jsp"/>