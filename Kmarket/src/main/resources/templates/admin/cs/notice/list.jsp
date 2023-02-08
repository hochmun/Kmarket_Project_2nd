<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../../_header.jsp"/>
<script src="../js/noticeList.js"></script>
    <section id="admin-product-list">
                <nav>
                    <h3>공지사항 목록</h3>
                    <p>
                        HOME > 고객센터 > <strong>공지사항</strong>
                    </p>
                </nav>
                <!-- 상품목록 컨텐츠 시작 -->                                
                <section>
                    <div>
                        <select name="type" class="w-btn-outline w-btn-indigo-outline" >
                            <option value="">유형 선택</option>
                            <option value="10">고객서비스</option>
                            <option value="11">안전거래</option>
                            <option value="12">위해상품</option>                                    
                            <option value="13">이벤트상품</option>                                    
                        </select>
                        
                    </div>
                    <table class="VOStableList">
                        <tr>
                            <th style="width: 5%;"><input type="checkbox" id="cbx_chkAll"/></th>
                            <th style="width: 5%;">번호</th>
                            <th style="width: 11%;">유형</th>
                            <th style="width: auto;">제목</th>
                            <th style="width: 5%;">조회</th>
                            <th style="width: 9%;">날짜</th>
                            <th style="width: 5%;">관리</th>
                        </tr>
                        
                        <c:forEach var="vo" items="${ vos }">
                        	<tr>
	                            <td><input type="checkbox" name="상품코드"/></td>
	                            <td>${ vo.noticeNo }</td>
	                            
	                            <c:if test="${ vo.noticeCate eq 10 }"><td>고객서비스</td></c:if>
	                            <c:if test="${ vo.noticeCate eq 11 }"><td>안전거래</td></c:if>
	                            <c:if test="${ vo.noticeCate eq 12 }"><td>위해상품</td></c:if>
	                            <c:if test="${ vo.noticeCate eq 13 }"><td>이벤트상품</td></c:if>
	                            
	                            <td><a href="./view.do?p=${ currentPage }&t=${ vo.noticeCate }&n=${ vo.noticeNo }">${ vo.noticeTitle }</a></td>
	                            <td>${ vo.noticeHit }</td>
	                            <td>${ vo.noticeRdate }</td>
	                            <td>
	                                <a href="./delete.do?n=${ vo.noticeNo }&t=1" class="deleteSingle" onclick="deleteNotice()">[삭제]</a>
	                                <a href="./modify.do?p=${ currentPage }&t=${ vo.noticeCate }&n=${ vo.noticeNo }">[수정]</a>
	                            </td>
	                        </tr>
                        </c:forEach>
                        
                    </table>

					<div class="btn2ro">
                     <a href="#" class="w-btn w-btn-indigo deleteAll">선택삭제</a>
                     <a href="./write.do" class="w-btn w-btn-green">작성하기</a>                     
					</div>

                    <div class="paging">
                    	
                    	<c:if test="${ pageGroupStart gt 1 }">
		            		<span class="prev">
			                    <a href="${pageContext.request.contextPath}/admin/cs/notice/list.do?p=${ pageGroupStart - 1 }&t=${ param.t }">&nbsp;이전</a>
			                </span>
		            	</c:if>
		            	
		            	<span class="num">
			            	<c:forEach var="i" begin="${ pageGroupStart }" end="${ pageGroupEnd }" step="1">
			            		<a href="${pageContext.request.contextPath}/admin/cs/notice/list.do?p=${ i }&t=${ param.t }" class="${ currentPage eq i ? 'on' : '' }">${ i }</a>
			            	</c:forEach>
		                </span>
		                
		                <c:if test="${ pageGroupEnd lt lastPageNum }">
		                	<span class="next">
			                    <a href="${pageContext.request.contextPath}/admin/cs/notice/list.do?p=${ pageGroupEnd+1 }&t=${ param.t }">다음&nbsp;</a>
			                </span>
		                </c:if>
                    	
                        
					</div>

                </section>              
	</section>
</main>
<jsp:include page="../../_footer.jsp"/>