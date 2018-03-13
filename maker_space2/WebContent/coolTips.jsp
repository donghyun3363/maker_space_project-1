<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>MakerSpace</title>

<!-- Bootstrap core CSS -->
<link href="./Resource/mms/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap side-bar menu -->
<link href="./Resource/mms/vendor/bootstrap/css/shop-homepage.css"
	rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="./Resource/mms/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="./Resource/mms/vendor/simple-line-icons/css/simple-line-icons.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template -->
<link href="./Resource/mms/css/landing-page.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</head>

<body>
	<%
      String CONTEXT_PATH = application.getContextPath();
   %>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<% if(session.getAttribute("name")!=null) { %>
		<a class="navbar-brand" href="mainService.jsp">MakerSpace</a>
		<%    }else { %>
		<a class="navbar-brand" href="index.jsp">MakerSpace</a>
		<%   }  %>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor01" aria-controls="navbarColor01"
			aria-expanded="false" aria-label="Toggle navigation"></button>
		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav mr-auto">
			</ul>
			<%@include file="./include/loginInfo.jsp"%>
		</div>
	</nav>


	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<!-- Side Menu -->
			<div class="col-lg-3">
				<h1 class="my-4">Cool Tips</h1>
				<%@include file="./include/sideMenu.jsp"%>
			</div>
			<!-- /.Side Menu -->


			<!-- Table -->
			<div class="col-lg-9">
				<h1 class="my-4">&nbsp;</h1>
				<div class="container">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">Cool Tips</li>
					</ol>
				</div>
				<div class="floatRight">
					<form method="post" action="write.jsp">
						<input type="hidden" name="category"
							value=<%=request.getAttribute("category")%>> <input
							type="submit" class="btn btn-block btn-lg btn-primary pull-right"
							style="display: inline-block;" value="Write" />
					</form>
				</div>
				<div class="container">
					<table class="table table-hover">
						<thead>
							<tr class="table-active">
								<th scope="col">최신순</th>
								<th scope="col">제목</th>
								<th scope="col">작성자</th>
								<th scope="col">조회수</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">1</th>
								<td><a href="#" class="alert-link"> 아이고 세상에 이런일이 </a></td>
								<td>황보영</td>
								<td>11</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">3</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">4</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">5</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">6</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">7</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">8</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">9</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">10</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.Table -->


				<!-- Paginatoin -->
				<div style="display: inline-block; vertical-align: middle;">
					<ul class="pagination">
						<c:if test="${map.prevPage <= 0}">
							<li class="page-item disabled"><a class="page-link">&laquo;</a>
							</li>
						</c:if>
						<c:if test="${map.prevPage > 0}">
							<li class="page-item"><a class="page-link"
								href="boardcontroller?action=getBoards&page=${map.prevPage}&category=<%=request.getAttribute("category")%>>&field=${map.field }">&laquo;</a>
							</li>
						</c:if>
						<c:if test="${map.pageCount <= 5}">
							<c:forEach begin="${map.beginPage}"
								end="${map.beginPage + map.pageCount - 1}" var="page">
								<c:choose>
									<c:when test="${map.currentPage == page}">
										<li class="page-item active"><a class="page-link"
											href="#">${page}</a>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link"
											href="boardcontroller?action=getBoards&page=${page}&field=${map.field}&category=<%=request.getAttribute("category")%>">${page}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
						<c:if test="${map.pageCount > 5}">
							<c:forEach begin="${map.beginPage}" end="${map.endPage}"
								var="page">
								<c:choose>
									<c:when test="${map.currentPage == page}">
										<li class="page-item active"><a class="page-link"
											href="#">${page}</a>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link"
											href="boardcontroller?action=getBoards&page=${page}&field=${map.field}&category=<%=request.getAttribute("category")%>">${page}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
						<c:if test="${map.nextPage <= 0}">
							<li class="page-item disabled"><a class="page-link">&raquo;</a>
							</li>
						</c:if>
						<c:if test="${map.nextPage > 0}">
							<li class="page-item"><a class="page-link"
								href="boardcontroller?action=getBoards&page=${map.nextPage}&field=${map.field }&category=<%=request.getAttribute("category")%>">&raquo;</a>
							</li>
						</c:if>
					</ul>
				</div>

			</div>


			<!-- Search bar -->
			<form id="searchForm" name="searchForm" method="post"
				action="boardcontroller">
				<input type="hidden" name="action" value="find"> <input
					type="hidden" name="category"
					value=<%=request.getAttribute("category") %>>
				<div class="form-row">
					<div class="col-12 col-md-2">
						<select id="searchMethod" name="searchMethod"
							class="btn btn-primary dropdown-toggle">
							<option selected value=0>title
							<option value=1>content
							<option value=2>name
						</select>
					</div>

					<div class="col-12 col-md-8 mb-2 mb-md-0">
						<input id="searchContent" type="text" name="searchContent"
							class="form-control form-control-lg"
							placeholder="# 5G # 1등 KT ... ">
					</div>
					<div class="col-12 col-md-2">
						<form class="form-inline my-2 my-lg-0">
							<button id="searchBtn" type="submit"
								class="btn btn-secondary my-2 my-sm-0">&emsp;검색&emsp;</button>
						</form>
					</div>
				</div>
			</form>
		</div>
	</div>
	</div>


	<%@include file="./include/footer.jsp"%>


	<!-- Bootstrap core JavaScript -->
	<script src="./Resource/mms/vendor/jquery/jquery.min.js"></script>
	<script src="./Resource/mms/vendor/jquery/jquery.slim.min.js"></script>
	<script
		src="./Resource/mms/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="./Resource/mms/vendor/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>