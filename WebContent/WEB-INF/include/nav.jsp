<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <c:if test="${!empty page }">
	<div class="scott">
		<!-- 动态的设置遍历页码的起始和结束索引值 -->
		<c:choose>
			<c:when test="${page.totalPage<=5 }">
				<!-- 设置begin=1  end=totalPage -->
				<c:set var="begin" value="1"></c:set>
				<c:set var="end" value="${page.totalPage }"></c:set>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${page.pageNumber<=3 }">
						<c:set var="begin" value="1"></c:set>
						<c:set var="end" value="5"></c:set>
					</c:when>
					<c:otherwise>
						<!-- 总页码>5并且当前页码>3  根据当前页码动态计算begin和end的值 -->
						<c:set var="begin" value="${page.pageNumber-2 }"></c:set>
						<c:set var="end" value="${page.pageNumber+2 }"></c:set>
						<!-- 如果end值大于总页码，设置为总页码 -->
						<c:if test="${end>page.totalPage }">
							<c:set var="end" value="${page.totalPage }"></c:set>
							<!-- 需要修改begin值，保证页面显示5个页码 -->
							<c:set var="begin" value="${end-4 }"></c:set>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	
	
		<a href="${page.path }&pageNumber=${page.pageNumber-1 }"> &lt; </a>
		<!-- 遍历生成页码：  begin= 1  end=page.totalPage     -->
		<c:forEach begin="${begin }" end="${end }" var="index">
			<c:choose>
				<c:when test="${index==page.pageNumber }">
					<!-- 当前页  高亮显示  -->
					<span class="current">${page.pageNumber }</span>
				</c:when>
				<c:otherwise>
					<a href="${page.path }&pageNumber=${index }">${index }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<a href="${page.path }&pageNumber=${page.pageNumber+1 }"> &gt; </a>
	共${page.totalPage }页，${page.totalCount }条记录 到第<input
		value="${page.pageNumber }" name="pn" id="pn_input" />页 <input
		id="sendBtn" type="button" value="确定">
		
		<!-- 将分页导航栏的js代码写在分页代码中 -->
		<script type="text/javascript">
			$("#sendBtn").click(function(){
				//获取要跳转的页码
				var pageNumber = $("#pn_input").val();
				//跳转
				//检查字符串是不是一个数字    isNaN(pageNumber): is not a number : 如果参数是一个数字返回false，如果不是返回true
				if(isNaN(pageNumber)){
					alert("请输入正确的页码");
					//并在输入页码的表单项中显示之前正确的页码
					$("#pn_input").val($("#pn_input")[0].defaultValue);
				}else{
					//修改window对象的location属性值，可以让浏览器向新地址发起请求
					window.location = "${page.path }&pageNumber="+pageNumber;
				}
				
			});
		
		</script>
		
	</div>
	</c:if>