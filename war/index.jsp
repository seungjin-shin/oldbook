<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>


<html>
    <head>
        <title>Upload Test</title>
    </head>
    <body>
        <form action="<%= blobstoreService.createUploadUrl("/saveArticle") %>" method="post" enctype="multipart/form-data">
            <input type="text" name="ID">
            <input type="text" name="title">
            <input type="text" name="author">
            <input type="text" name="publisher">
            <input type="text" name="price">
            <input type="text" name="condition">
            <input type="text" name="method">
            <input type="file" name="image">
            <input type="text" name="contents">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>