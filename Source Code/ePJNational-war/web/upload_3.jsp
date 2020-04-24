<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="java.util.List, java.util.Iterator, java.io.File" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Processing</title>
    </head>
    <body>

        <h1>Processing Upload</h1>

        <%
                    boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
                    if (!isMultiPart) {
                        //todo
                    } else {
                        FileItemFactory factory = new DiskFileItemFactory();
                        ServletFileUpload upload = new ServletFileUpload(factory);
                        List items = null;
                        try {
                            items = upload.parseRequest(request);
                        } catch (FileUploadException e) {
                            e.printStackTrace();
                        }
                        Iterator iter = items.iterator();
                        while (iter.hasNext()) {
                            FileItem item = (FileItem) iter.next();
                            if (item.isFormField()) {
                                //todo
                            } else {
                                try {
                                    String itemName = item.getName();
                                    String fileName = itemName.substring(
                                            itemName.lastIndexOf("\\") + 1);

                                    String RealPath = config.getServletContext().getRealPath("/")
                                            + "contents\\" + fileName;

                                    File savedFile = new File(RealPath);
                                    item.write(savedFile);
                                    String id = (String) session.getAttribute("ADMINUPLOADEVENT");
                                    request.getRequestDispatcher("AdminEventUploadContentServlet?eventIDUpload=" + id+"&URL="+fileName).forward(request, response);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
        %>
        <h2>Upload Finish!!!!</h2>
    </body>
</html>
