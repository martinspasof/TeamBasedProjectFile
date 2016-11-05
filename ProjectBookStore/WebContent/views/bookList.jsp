<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<c:set var="bookStore"  value="<%= request.getSession().getAttribute(\"bookStore\") %>" />
<c:set var="bookList"  value="<%= request.getSession().getAttribute(\"bookList\") %>" />
<c:set var="addBook"  value="${contextPath}/ActionServlet"/>
<c:set var="showMsg"  value="<%= request.getSession().getAttribute(\"showMessage\") %>" />
<c:set var="msg"  value="<%= request.getSession().getAttribute(\"messageText\") %>" />
<c:set var="userAccess"  value="<%= request.getSession().getAttribute(\"userAccess\") %>" />
<c:set var="display"  value="<%= \"display:none\" %>" />

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book List</title>
<script src="../js/jquery-3.1.1.min.js"	type="text/javascript"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!--<link rel="stylesheet" href="../css/bootstrap.min.css">-->



</head>
<body id="bodyBookList">
<h1 style="margin-left:20px;">Books List</h1>
<br/>	

<div id="actionMessage" class="alert alert-success" style="width:60%;margin-left:15%;<c:if test="${empty showMsg}"> ${display}</c:if>" >
   <strong  style="margin-left:32%">${msg}</strong>
</div>
<c:if  test="${userAccess==2}" >
<button class="btn btn-primary btn-sm" style="margin-left: 17px;margin-bottom:5px;" type="button" onclick="location='${addBook}'">Add Book</button>
		
</c:if>
<div style="float:right;margin-right: 54%;"> Enter search book title: <input type="text" id="searchBook" /></div>
	<br/>
<form name="actionBookForm" action="../ActionServlet" method="post" >	
<div class="col-sm-12">
	<table id="bookList" class="table table-bordered">
		<thead>
			<tr>
			    <th>No</th>				
				<th>Catalog Number</th>
				<th>Title</th>		
				<th>Author</th>					
				<th>Price</th>
				<th>Number of books</th>
				<th>Name of Publishing</th>		
				<th>Foreigner Literature</th>
				<th>Action</th>	
			</tr>
		</thead>

		  <c:if test="${not empty bookList}">
		  <input type="hidden" name="hiddenBookId" />
		   <input type="hidden" name="hiddenBookAction" />
		    <input type="hidden" name="hiddenBookTitle" />
	    	<tbody>
	    	
		      <c:forEach var="s" items="${bookList}" varStatus="loop" >
		         <c:if test="${s.value.getNumbersOfBook()>0}">
		            <tr>
		                <td>${loop.index+1}</td>		               
		                <td>${s.value.getCatalogNumber()}</td>
		                <td>${s.key}</td>
		                <td>${s.value.getAuthor()}</td>
		                <td>${s.value.getPrice()}</td>	
		                <td>${s.value.getNumbersOfBook()}</td>		                		                
		                <td>${s.value.getNameOfPublishing()}</td>
		                <td>${bookStore.enumsForeignBook(s.value.IsForeignLiterature())}</td>
		                <td>	
		                 <input type="button" class="btn btn-info btn-md" style="margin-bottom:2px;" id="sale_${s.value.getBookId()}_${s.key}" name="saleBook_${s.value.getBookId()}_${s.key}" value="Sale" onclick="actionButton(this)"/>
			                <c:if  test="${userAccess==2}" >	                
			                  <input type="button" class="btn btn-primary btn-sm" style="margin-bottom:2px;" id="editBook_${s.value.getBookId()}_${s.key}" name="editBook_${s.value.getBookId()}_${s.key}" value="Edit" onclick="actionButton(this)"/>
			                  <input type="button" class="btn btn-danger btn-sm" style="margin-bottom:2px;" id="deleteBook_${s.value.getBookId()}_${s.key}" name="deleteBook_${s.value.getBookId()}_${s.key}" value="Delete" onclick="actionButton(this)"/>  
			                </c:if>		                
		                </td>
		            </tr>
		         </c:if>
		      </c:forEach>
		        
	        </tbody>
		</c:if>

	</table>
	</div>
</form>	
	


	
<script>
$(document).ready(function() {
    //hide message after add, delete, sale book after 2000ms
	setTimeout(function() {  $('#actionMessage').hide() }, 2000);
	
	
	
	//actionMessage
    $('#searchBook').keyup(function(event) {
            var name = $('#searchBook').val();
            $.get('../BookController', {
            	searchBook : name
            }, function(responseText) {
                   // $('#ajaxGetUserServletResponse').text(responseText);
                    
                    $('#bodyBookList').html(responseText);
                    $('#searchBook').focus();
                   // document.getElementById("searchBook").focus();
                    $('#searchBook').val(name);
                    //setTimeout(function() { $('#searchBook').focus() }, 10);
                    //$('#searchBook').focus();
            });
    });
});

function actionButton(el){
	var res = el.id.split("_");
	console.log(res);
	document.actionBookForm.hiddenBookAction.value=res[0];
	document.actionBookForm.hiddenBookId.value=res[1];	
	document.actionBookForm.hiddenBookTitle.value=res[2];	
	document.actionBookForm.submit();
}

</script>
	
</body>
</html>

