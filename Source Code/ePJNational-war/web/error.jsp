<%-- 
    Document   : error
    Created on : Dec 4, 2013, 8:09:09 AM
    Author     : KieuTrinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link rel="stylesheet" href="CSS-Image-JQuery/Admin/css/screen.css" type="text/css" media="screen" title="default" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
        String error = (String)request.getAttribute("ERROR");
        %>
     
        <!-- Start: page-top-outer -->


        <!-- start content-outer ........................................................................................................................START -->
        <div id="content-outer">
            <!-- start content -->
            <div id="content">





                <!--  start page-heading -->

                <!-- end page-heading -->
                <!-- start logo-->
                <div id="logo-report">
                    <td><img src="CSS-Image-JQuery/Page/img/logo.png" width="279" height="57" alt="" /></td>
                </div>
                <div id="page-heading-logo"><h1>ERROR PAGE</h1></div>
                <div id="page-heading-Name"><h1  style="color: red">Error Name: <%=error%></h1></div>


                </div>

                    <br/>
                    <br/>
                    <!--end logo-->
                    <!-- start logo-->

                    <!--end logo-->
               
                    <div class="clear">&nbsp;</div>
                 
                    <div  style="text-align: center; margin-top: 30px;"><%= new java.util.Date()%>
                    </div>
                      <a href="Home.jsp">Back To Home Page</a>
                </div>
                <!--  end content -->
                <div class="clear">&nbsp;</div>
           
            <!--  end content-outer........................................................END -->
        
        <div class="clear">&nbsp;</div>

        <!-- start footer -->
        <div id="footer">
            <!--  start footer-left -->
            <div id="footer-left">

	Admin Skin &copy; Copyright KSC University Administration Ltd. <span id="spanYear"></span> <a href="">www.netdreams.co.uk</a>. All rights reserved.</div>
            <!--  end footer-left -->
            <div class="clear">&nbsp;</div>
        </div>
        <!-- end footer -->


      
    </body>
</html>
