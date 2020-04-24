<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.io.*, javax.activation.*" %>
<%@page trimDirectiveWhitespaces="true" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Download Page</title>
    </head>
    <body>
        <h1>Download starting ...</h1>
        <%
                    try {
                        String fileName = (String) request.getAttribute("fileName");
                        String path = request.getRealPath("/") + "contents/";
                        File file = new File(path + fileName);

                        //System.out.println("file path: " + path + fileName);
                        //System.out.println("Name: " + file.getName());
                        FileInputStream fis = new FileInputStream(file);
                        ServletOutputStream outStream = response.getOutputStream();
                        FileTypeMap ftm = new MimetypesFileTypeMap();
                        response.setContentType(ftm.getContentType(path + fileName));
                        response.setHeader("Content-Disposition", "attachment; filename="
                                + file.getName() + ";");
                        int iRead;
                        byte[] outputByte = new byte[4096];

                        while (fis.read(outputByte, 0, 4096) != -1) {
                            outStream.write(outputByte, 0, 4096);
                        }

                        /* while ((iRead = fis.read()) != -1) {
                        response.getOutputStream().write(iRead);
                        }*/

                        fis.close();
                        outStream.flush();
                        outStream.close();
                        //out.println("Download complete!!!");
                    } catch (Exception e) {
                        request.setAttribute("ERROR", "File Not Existed");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
        %>
    <html:link action="/main">Return Main page</html:link>
</body>
</html>
