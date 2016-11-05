<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="bookId"  value="<%= request.getSession().getAttribute(\"bookId\") %>" />
<c:set var="getAction"  value="<%= request.getSession().getAttribute(\"action\") %>" />
<c:set var="bookList"  value="<%= request.getSession().getAttribute(\"bookList\") %>" />
<c:set var="getKeyMap"  value="<%= request.getSession().getAttribute(\"getKeyMap\") %>" />
<c:set var="checked"  value="<%= request.getSession().getAttribute(\"checked\") %>" />
<c:set var="titleBook"  value="<%= request.getSession().getAttribute(\"titleBook\") %>" />

<html>
<head>
<title>Book addition</title>
</head>
<body>
	<h1>Book addition!</h1>

	<form  method="post" action="../ActionServlet">
	    CatalogNumber:<br> <input type="text" name="catalog_number" 
	    value="<c:if test="${not empty getKeyMap}" >${getKeyMap.getCatalogNumber()}</c:if>"/><br>
		Title:<br> <input type="text" name="title" 
		value="<c:if test="${not empty getKeyMap}" >${titleBook}</c:if>" /> <br>
		Author:<br> <input type="text" name="author" 
		value="<c:if test="${not empty getKeyMap}" >${getKeyMap.getAuthor()}</c:if>"/><br>
		Price:<br> <input type="text" name="price" 
		value="<c:if test="${not empty getKeyMap}" >${getKeyMap.getPrice()}</c:if>"/><br>
		Publishing:<br> <input type="text" name="name_of_publishing" 
		value="<c:if test="${not empty getKeyMap}" >${getKeyMap.getNameOfPublishing()}</c:if>"/><br>
		ForeignLiterature:<br> <input type="checkbox" name="foreign_literature" 
		${checked}
		value="<c:if test="${not empty getKeyMap}" >${getKeyMap.IsForeignLiterature()}</c:if>"/><br>
		NumbersOfBook:<br> <input type="text" name="numbers_of_book" 
		value="<c:if test="${not empty getKeyMap}" >${getKeyMap.getNumbersOfBook()}</c:if>"/><br>
		<input type="hidden" name="bookId" id="bookId" 
		value="<c:if test="${not empty getKeyMap}" >${getKeyMap.getBookId()}</c:if>"/>
		<input type="hidden" name="hiddenBookAction" value="${getAction}" />
		<br> <input type="submit" id="add_book" name="${getAction}" value="${getAction}">
	</form>



</body>
</html>