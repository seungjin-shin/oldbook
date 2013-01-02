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
        <title>Upload</title>
    </head>
    
        <script language="javascript">
function file_browse()
{
document.form.file.click();
document.form.text1.value=document.form.file.value;
}
</script>

   <body>
        
		<form action="<%= blobstoreService.createUploadUrl("/image") %>" method="post" enctype="multipart/form-data">
			<!-- <input type="text" name="title" >
			<input type="text" name="author" >
			<input type="text" name="publisher">
			<input type="text" name="price" >
			<input type="text" name="condition" >
			<input type="text" name="method" >
			<input type="text" name="contents" > -->
            <input type="hidden" name="num" value="<%=num%>">
            <input type="hidden" name="ID" value="<%=ID%>">

           <!--  <input type="file" name="image"> -->

<span id="fileInputForm" style="position:relative; float:left; width:469px; height:469px; overflow:hidden; cursor:pointer; background-image:url(img/search.png);">
   <input type="file" id="image" name="image" value="" style='position：absolute; margin-left:-10px; width:469px; height:469px; filter:alpha(opacity=0); opacity:0; -moz-opacity:0; cursor:pointer;' onChange="fileUpload()">
</span>
            
          <!-- <input type="text" name="passwd" >
            <input type="text" name="GCMID" >
            <input type="text" name="name" >
            <input type="text" name="phone"> -->

            <input type="submit" value="올리기" style="position:relative; float:left; font-size:60pt; width:400px; height:200px;">
        </form>
    </body>
</html>