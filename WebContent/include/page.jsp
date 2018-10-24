<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <script type="text/javascript">
	$(function() {
	$("#gotopage").click(function() {
	//alert("Hello");
	//用户输入了我要去第几页
	//1、获取到用户输入的值
	var pn = $("#pn_input").val();
	//2、发送新的分页请求
	var url = "${page.url}";
	window.location.href = "${page.url}&pn="+ pn;
		});
	});
</script>  
 
		<div id="page_nav">
			<!-- 把分页的详细信息，page， -->

			<a href="${page.url }&pn=1">首页</a>
			<c:if test="${page.hasPrev }">
				<a
					href="${page.url }&pn=${page.pageNo-1 }">上一页</a>
			</c:if>


			<!--  这个是当前页-->
			<!-- 总页码在五页以内 -->
			<c:if test="${page.totalPage<=5 }">
				<!--给begin和end赋值  -->
				<c:set var="begin" value="1" scope="page"></c:set>
				<c:set var="end" value="${page.totalPage }" scope="page"></c:set>
			</c:if>
			<!-- 总页码在五页以上 ，才使用连续显示五页的逻辑-->
			<c:if test="${page.totalPage>5 }">
				<!--当前页码小于3  -->
				<c:if test="${page.pageNo <=3}">
					<c:set var="begin" value="1" scope="page"></c:set>
					<c:set var="end" value="5" scope="page"></c:set>
				</c:if>
				<!-- 当前页码大于3 -->
				<c:if test="${page.pageNo >3}">
					<c:set var="begin" value="${page.pageNo-2}" scope="page"></c:set>
					<c:set var="end" value="${page.pageNo+2}" scope="page"></c:set>
				</c:if>
				<!-- 当前页码 + 2 大于等于总页码   end就到总页码结束   begin:总页码-4-->
				<c:if test="${page.pageNo+2 >= page.totalPage}">
					<c:set var="begin" value="${page.totalPage-4}" scope="page"></c:set>
					<c:set var="end" value="${page.totalPage}" scope="page"></c:set>
				</c:if>
			</c:if>


			<!-- 显示所有页码   总页码1 - totalPage begin -->
			<c:forEach begin="${begin }" end="${end }" var="pnum">
				<!-- 判断当前遍历的页码号是否为当前页码，是--不加连接 -->
				<c:if test="${pnum == page.pageNo}">
					<span style="color: blue;">【${page.pageNo}】</span>
				</c:if>
				<!-- 不是当前页码，要加链接 -->
				<c:if test="${pnum != page.pageNo}">
					<a href="${page.url }&pn=${pnum }">${pnum }</a>
				</c:if>
			</c:forEach>




			<c:if test="${page.hasNext }">
				<a
					href="${page.url }&pn=${page.pageNo+1 }">下一页</a>
			</c:if>

			<a
				href="${page.url }&pn=${page.totalPage }">末页</a>
			共${page.totalPage }页，${page.totalCount }条记录 到第<input
				value="${page.pageNo }" name="pn" id="pn_input" />页 <input
				type="button" value="确定" id="gotopage">
		</div>