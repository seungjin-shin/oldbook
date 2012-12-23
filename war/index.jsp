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
        <form action="<%= blobstoreService.createUploadUrl("/saveImg") %>" method="post" enctype="multipart/form-data">
            <input type="text" name="num" value="<%=num%>">
            <input type="text" name="ID" value="<%=ID%>">
            <input type="file" name="image">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>