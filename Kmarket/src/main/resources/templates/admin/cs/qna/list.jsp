<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../_header.jsp"/>
<script src="${pageContext.request.contextPath}/admin/cs/js/qnaList.js"></script>
<script>
const cate2Ori = "${ param.cate2 }";
window.addEventListener('load',()=>{
	const cate1Ori = '${ param.cate1 }';
	$('select[name=cate1]').val(cate1Ori).trigger('change');
});
</script>
    <section id="admin-product-list">
                <nav>
                    <h3>문의하기 목록</h3>
                    <p>
                        HOME > 고객센터 > <strong>문의하기</strong>
                    </p>
                </nav>
                <!-- 상품목록 컨텐츠 시작 -->                                
                <section>
                    <div>
                        <select name="cate1" class="w-btn-outline w-btn-indigo-outline">
                            <option value="" >1차 선택</option>
                            
                            <c:forEach var="vo2" items="${ vos2 }">
                            <option value="${ vo2.cate1 }">${ vo2.cate1Name }</option>
                            </c:forEach>
                            
                        </select>
                        
                    </div>
                    <div>
                        <select name="cate2" class="w-btn-outline w-btn-indigo-outline">
                            <option value="">2차 선택</option>
                        </select>
                    </div>
                    
                    <table id="listTable">
                        <tr>
                            <th><input type="checkbox" id="cbx_chkAll"/></th>
                            <th>번호</th>
                            <th>1차유형</th>
                            <th>2차유형</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>상태</th>
                        </tr>
                        <c:forEach var="vo" items="${vos}">
                        <tr>
                            <td><input type="checkbox" name="상품코드"/></td>
                            <td>${vo.qnaNo}</td>
                            <td>${vo.cate1Name}</td>
                            <td>${vo.cate2Name}</td>
                            <td><a href="${pageContext.request.contextPath}/admin/cs/qna/reply.do?qnaNo=${vo.qnaNo}&cate1=${ param.cate1 }&cate2=${param.cate2}&pg=${param.pg}">${vo.qnaTitle}</a></td>
                            <td>${vo.uid}</td>
                            <td>${vo.qnaRdate}</td>
                            
                        <c:choose>
                        <c:when test="${vo.qnaType eq 1}">
                            <td>검토중</td>
                        </c:when>
                        <c:otherwise>
                        	<td>답변완료</td>
                        </c:otherwise>
                        </c:choose>
                        </tr>
                        </c:forEach>
                    </table>

                    <div class="btn1ro">
                    	<a href="#" class="w-btn w-btn-indigo deleteAll">선택삭제</a>
					</div>

                    <div class="paging">
	                    <c:if test="${pageGroupStart gt 1}">
							<span class="prev">
							    <a href="/Kmarket/admin/cs/qna/list.do?pg=${pageGroupStart - 1}&cate1=${param.cate1}&cate2=${param.cate2}"><&nbsp;이전</a>
							</span>
						</c:if>		
                        <c:forEach var="pg" begin="${pageGroupStart}" end="${pageGroupEnd}" step="1">
	                        <span class="num">
	                            <a href="/Kmarket/admin/cs/qna/list.do?pg=${pg}&cate1=${param.cate1}&cate2=${param.cate2}" class="w-btn-outline w-btn-blue-outline ${currentPage eq pg ? 'on' : 'off'}">${pg}</a>
	                        </span>
                        </c:forEach>
						<c:if test="${pageGroupEnd lt lastPageNum}">
							<span class="next">
							    <a href="/Kmarket/admin/cs/qna/list.do?pg=${pageGroupEnd + 1}&cate1=${param.cate1}&cate2=${param.cate2}">다음&nbsp;></a>
							</span>
						</c:if>
                    </div>

                </section>              
</section>
</main>
<jsp:include page="../../_footer.jsp"/>