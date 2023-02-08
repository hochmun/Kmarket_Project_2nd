<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../_header.jsp"/>
<script>
$(()=>{
	$('.btnNoticeModify').click((e)=>{
		e.preventDefault();
		const n = "${ param.n }";
		const c = $('select[name=cate]').val();
		const title = $('input[name=title]').val();
		const content = $('textarea[name=content]').val();
		
		// 검사
		if(c == null || c == "") {
			alert('카테고리를 선택하십시오');
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
			url: '/Kmarket/admin/cs/notice/modify.do',
			type: 'POST',
			data: {"c":c, "n":n, "title":title, "content":content},
			dataType: 'json',
			success: (data)=>{
				if(data.result > 0) {
					alert('게시물 수정 완료!');
					location.href = '/Kmarket/admin/cs/notice/list.do';
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
                    <h3>공지사항 수정</h3>
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
                                <select name="cate" class="w-btn-outline w-btn-indigo-outline">
                                    <option value="10" ${ vo.noticeCate eq 10 ? 'selected' : '' }>고객서비스</option>
                                    <option value="11" ${ vo.noticeCate eq 11 ? 'selected' : '' }>안전거래</option>
                                    <option value="12" ${ vo.noticeCate eq 12 ? 'selected' : '' }>위해상품</option>
                                    <option value="13" ${ vo.noticeCate eq 13 ? 'selected' : '' }>이벤트상품</option>
                                </select>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>제목</td>
                            <td>
                                <input type="text" name="title" class="w-btn-outline w-btn-indigo-outline" placeholder="제목을 입력하세요."
                                value="${ vo.noticeTitle }">
                            </td>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td>
                            	<pre style="display: grid;">
                                	<textarea name="content" class="w-btn-outline w-btn-indigo-outline" placeholder="내용을 입력하세요." style="display: block;">${ vo.noticeContent }</textarea>
                                </pre>
                            </td>
                        </tr>
                    </table>
                    <div class="btn2ro">
                        <a href="./list.do" class="w-btn w-btn-indigo">취소하기</a>
                        <a href="#" class="w-btn w-btn-green btnNoticeModify">수정하기</a>
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