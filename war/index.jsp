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
        <form action="<%= blobstoreService.createUploadUrl("/saveImg") %>" method="post" enctype="multipart/form-data">
            <input type="text" name="num">
            <input type="text" name="ID">
            <input type="file" name="image">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>