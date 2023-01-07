<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTables Advanced Tables
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" >
                                <thead>
                                    <tr>
                                        <th>#번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list }" var="board">
	                                    <tr class="odd gradeX">
	                                        <td><c:out value="${board.bno }"/></td>
	                                        <td><a href="/board/get?bno=<c:out value="${board.bno }"/>"><c:out value="${board.title }"/></a></td>
	                                        <td><c:out value="${board.writer }"/></td>
	                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate }"/></td>
	                                        <td ><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate }"/></td>
	                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->

	<form id='actionForm' action="/board/list" method="get">
		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"> 
	    <input type="hidden" name="amount" value="${pageMaker.cri.amount}"> 
	    <input type="hidden" name="type" value="<c:out value='${pageMaker.cri.type}'/>"> 
	    <input type="hidden" name="keyword" value="<c:out value='${pageMaker.cri.keyword}'/>">
	</form>
	
			<!-- 검색기능 -->
		<form id="searchForm" action="/board/list" method="get">
			<select name="type">
				<option value=""<c:out value='${pageMaker.cri.type == null? "selected": ""}'/>>--</option>
				<option value="T"<c:out value='${pageMaker.cri.type eq "T"? "selected": ""}'/>>제목</option>
				<option value="C"<c:out value='${pageMaker.cri.type eq "C"? "selected": ""}'/>>내용</option>
				<option value="W"<c:out value='${pageMaker.cri.type eq "W"? "selected": ""}'/>>작성자</option>
			</select> 
			<input type="text" name="keyword" value="<c:out value='${pageMaker.cri.keyword}'/>"> 
			<input type="hidden" name="pageNum" value="<c:out value='${pageMaker.cri.pageNum}'/>"> 
			<input type="hidden" name="amount" value="<c:out value='${pageMaker.cri.amount}' />">
			<button class="btn btn-default">검색</button>
		</form>
	

<!-- Paging -->
		<nav class="page navigation">

			<ul class="pagination">

				<c:if test="${pageMaker.prev}">
					<li class="paginate_button previous"><a class="page-link"
						href="${pageMaker.startPage - 1}">Prev</a></li>
				</c:if>
				<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
					<li class="paginate_button ${pageMaker.cri.pageNum == num ? "active" : "" } ">
						<a class="page-link" href="${num}">${num}</a>
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next}">
					<li class="paginate_button next"><a class="page-link"
						href="${pageMaker.endPage + 1}">Next</a></li>
				</c:if>

			</ul>


		</nav>
		<!-- /.page -->
		




<%@include file="../includes/footer.jsp"%>
 
 
<script type="text/javascript">
	$(document).ready(function() {
		var result = '<c:out value="${result}"/>';
		if(!(result=='')&& parseInt(result)>0)
			alert(parseInt(result)+"번 게시물이 등록되었습니다!");
		
		var actionForm = $('#actionForm'); 
		$('.paginate_button a').on('click', function(e) { 
			e.preventDefault()
			//걸어둔 링크로 이동하는 것을 일단 막음
			actionForm.find('input[name="pageNum"]').val($(this).attr('href')); actionForm.submit(); 
			});
		
		
		var searchForm = $('#searchForm');
		$('#searchForm button').on('click', function(e) {
			if (!searchForm.find('option:selected').val()) {
				alert('검색종류를 선택하세요');
				return false;
			}
			if (!searchForm.find('input[name="keyword"]').val()) {
				alert('키워드를 입력하세요');
				return false;
			}
			e.preventDefault();
			searchForm.find('input[name="pageNum"]').val('1');
			searchForm.submit();
		});
		
		
	});
	
	
</script>