<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	String num = request.getParameter("num");
	String ID = request.getParameter("ID");
%>


<html>
    <head>
        <title>Upload Test</title>
    </head>
    <body>
        
		<form action="<%= blobstoreService.createUploadUrl("/member") %>" method="post" enctype="multipart/form-data">
			<!-- <input type="text" name="title" >
			<input type="text" name="author" >
			<input type="text" name="publisher">
			<input type="text" name="price" >
			<input type="text" name="condition" >
			<input type="text" name="method" >
			<input type="text" name="contents" > -->
            <input type="text" name="num" value="<%=num%>">
            <input type="text" name="ID" value="<%=ID%>">

            <input type="file" name="image">
            
            <input type="text" name="passwd" >
            <input type="text" name="GCMID" >
            <input type="text" name="name" >
            <input type="text" name="phone">

            <input type="submit" value="Submit">
        </form>
    </body>
</html>