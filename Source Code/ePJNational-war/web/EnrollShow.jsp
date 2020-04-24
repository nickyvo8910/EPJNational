<%@page import="java.util.Calendar"%>
<%@page import="nicky.entitybean.TblUsers"%>
<%@page import="nicky.entitybean.TblEvents"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<head>

    <meta charset="utf-8">

    <title>KSC University</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="CSS-Image-JQuery/Page/css/main.css">
    <link rel="shortcut icon" type="image/x-icon" href="CSS-Image-JQuery/Page/img/favicon.ico" />
    <script type="text/javascript" src="CSS-Image-JQuery/Page/validate/validate.js" ></script>

    <!--[if lt IE 9]>
     <script src="js/html5shiv.js"></script>
    <![endif]-->

</head>
<html>
    <body>
        <div id="pageheader">
            <header>
                <div class="parentContainer">
                    <div id="socialButtons">
                        <%
                                    String username = (String) session.getAttribute("username");
                                    boolean isLoggedin = false;
                                    if (username != null && !username.equals("guest@yahoo.com")) {
                                        isLoggedin = true;
                                    }
                                    if (isLoggedin == false) {
                                        request.getRequestDispatcher("404.html").forward(request, response);
                                    }
                        %>
                    </div>
                </div>
                <div>

                    <div id="menu">
                        <div id="pageheader">
                            <section>
                                <a href="index.jsp"><img src="CSS-Image-JQuery/Page/img/logo.png" alt=""></a>
                                <ul class="clearfix">
                                    <li><a href="MyEvent.jsp">My Event</a></li>
                                    <li><a href="Feedback.jsp">Feedback</a></li>
                                    <li><a href="UserProfile.jsp">User Profile</a></li>
                                    <li><a href="ChangePass.jsp">Change Pass</a></li>
                                    <li><a href="Subscribe.jsp">(Un)Subscribe</a></li>
                                    <li><a href="LogoutServlet">Logout</a></li>
                                </ul>
                            </section>
                        </div>
                    </div>
                    <div id="tagline">
                        <section>
                            <a href="#" id="slogan">“ In <strong>Knowledge</strong> There Is Opportunity ”</a>
                        </section>
                    </div>
                    <div id="info">
                        <section>
                            <div>
                                <img src="CSS-Image-JQuery/Page/img/phone.png" alt="">
                                <h2>Welcome</h2>
                                <h4>© 2014 KSC University. All Rights Reserved</h4>
                            </div>
                            <span class="element leftElement"></span>
                            <span class="triangle">triangle</span>
                        </section>
                    </div>
                </div>
            </header>
        </div>
        <div id="body">
            <div id="contentBk" class="clearfix">
                <div id="content">
                    <div class="topImg clearfix">
                        <img src="CSS-Image-JQuery/Page/img/headers/header_1.jpg" alt="About Us">
                        <p>Enroll <strong>Show</strong></p>
                    </div>
                    <div class="wrapper">
                        <div class="column c-67 clearfix">
                            <div class="box">
                                <%
                                            if (request.getAttribute("EVENT") != null && request.getAttribute("USER") != null && request.getAttribute("FEE") != null) {
                                                TblEvents event = (TblEvents) request.getAttribute("EVENT");
                                                TblUsers user = (TblUsers) request.getAttribute("USER");
                                                Double fee = (Double) request.getAttribute("FEE");
                                                Calendar cal = Calendar.getInstance();
                                                cal.setTime(event.getEventDate());
                                                String date = cal.get(Calendar.YEAR) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.DAY_OF_MONTH);

                                %>
                                <h4>Enrollment</h4>
                                <div class="boxInfo">
                                    <p style="color: red">Notice : Enroll Fee is free to KSC University's Students</p>
                                    <table class="boxEnroll" >
                                        <tr class="tableHeader">
                                            <th class="tableTh">Event Name</th>
                                            <td class="tableTd"><%=event.getEventName()%></td>
                                        </tr>
                                        <tr class="tableHeader">
                                            <th class="tableTh">Event Date</th>
                                            <td class="tableTd"><%=date%></td>
                                        </tr>
                                        <tr class="tableHeader">
                                            <th class="tableTh">Event Price</th>
                                            <td class="tableTd"><%=fee%></td>
                                        </tr>
                                        <tr class="tableHeader">
                                            <th class="tableTh">Hosted By</th>
                                            <td class="tableTd"><%=event.getEventHost()%></td>
                                        </tr>
                                        <tr class="tableHeader">
                                            <th class="tableTh">Username</th>
                                            <td class="tableTd"><%=user.getUsername()%></td>
                                        </tr>
                                        <tr class="tableHeader">
                                            <th class="tableTh">Fullname</th>
                                            <td class="tableTd"><%=user.getFullname()%></td>
                                        </tr>
                                    </table>
                                </div>
                                <div>
                                    <form action="EnrollShowServlet" method="post">
                                        <input type="hidden" name="eventID" value="<%=event.getEventID()%>"/>
                                        <input type="hidden" name="username" value="<%=user.getUsername()%>"/>
                                        <input id="CancelBT" class="cancel" type="button" onclick="doLink()" value="Cancel"/>
                                        <input id="EnrollBT" class="submit" type="submit" value="Confirm" name="action"/>
                                    </form>
                                </div>
                            </div>
                            <%}%>
                        </div>

                        <div class="column c-33 last  clearfix">
                            <div class="links">
                                <div class="box contactUs clearfix boxInfo">
                                    <%
                                                if (session.getAttribute("USERINFO_PANEL") != null) {
                                                    TblUsers userPanel = (TblUsers) session.getAttribute("USERINFO_PANEL");
                                    %>
                                    <h4>User <strong>Setting </strong> <%=userPanel.getUsername()%></h4>


                                    <img src="images/userAvatar/<%=userPanel.getUserImg()%>" alt="" class="imgBorder" id="avarta" style="width: 70px;height: 50px" >
                                    <%} else {%>
                                    <h4>User <strong>Setting </strong></h4>
                                    <%}%>
                                    <div class="boxInfo1">
                                        <ul>
                                            <li><a href="MyEvent.jsp">My Event</a></li>
                                            <li><a href="Feedback.jsp">Feedback</a></li>
                                            <li><a href="UserProfile.jsp">User Profile</a></li>
                                            <li><a href="ChangePass.jsp">Change Pass</a></li>
                                            <li><a href="Subscribe.jsp">(Un)Subscribe</a></li>
                                            <li><a href="LogoutServlet">Logout</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
        <div id="pagefooter">
            <footer>

                <div class="stripe clearfix">
                    <div class="twitter">
                        <img src="CSS-Image-JQuery/Page/img/twitter.png" alt=""><div class="twitterList" data-user="rubenbristian"></div>
                    </div>
                </div>

                <div class="mail">
                    <div>
                        <form action="PageLoadServlet" method="post">
                            <div class="mailInfo">
                                <div>
                                    <h4>Subscribe</h4>
                                    <input id="mailInput" type="email" required="true" name="txtSubscribe"/>
                                    <input id="mailSubmit" type="submit" value="Go" name="action"/>
                                </div>
                            </div>
                        </form>
                        <span class="triangle">triangle</span>
                        <span class="element rightElement"></span>
                    </div>
                </div>

                <div id="footerContent" >
                    <section>
                        <div class="clearfix">

                            <div class="links column c-30 clearfix ">
                                <h3>Recent Events</h3>
                                <ul>
                                    <li><a href="#">After Graduation</a></li>
                                    <li><a href="#">Continuing Education</a></li>
                                    <li><a href="#">Introducing Genetic</a></li>
                                    <li><a href="#">Celebrating Founder's Day</a></li>
                                    <li><a href="#">Celebrating Founder's Day</a></li>
                                </ul>
                            </div>

                            <div class="news column c-40 clearfix">
                                <h3>Featured Event</h3>
                                <div class="clearfix">
                                    <div class="post clearfix">
                                        <a id="footerA" href="CSS-Image-JQuery/Page/img/events/e1_large.jpg" rel="lightbox[events]" class="grayColor"><img data-color="CSS-Image-JQuery/Page/img/events/e1Footer.jpg" src="CSS-Image-JQuery/Page/img/events/e1Footer_gray.jpg" class="imgBorder" alt=""></a>
                                        <div class="footerpost">
                                            <a href="#"><h5>Admissions open for 2012</h5></a>
                                            <span>Posted on 07 jan 2012</span>
                                            <p>Lorem ipsum dolor sit amet set..</p>
                                        </div>
                                    </div>
                                    <div class="post clearfix ">
                                        <a id="footerA" href="CSS-Image-JQuery/Page/img/events/e2_large.jpg" rel="lightbox[events]" class="grayColor"><img data-color="CSS-Image-JQuery/Page/img/events/e2Footer.jpg" src="CSS-Image-JQuery/Page/img/events/e2Footer_gray.jpg" class="imgBorder" alt=""></a>
                                        <div class="footerpost">
                                            <a href="#"><h5>Admissions open for 2012</h5></a>
                                            <span>Posted on 07 jan 2012</span>
                                            <p>Lorem ipsum dolor sit amet set..</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="contact column c-30 clearfix ">
                                <h3>Feedback Form</h3>
                                <form action="PageLoadServlet" method="post">
                                    <textarea id="message" pattern="^[a-zA-Z[\w\s]+$]" required="true" oninvalid="InvalidFeedback(this);" oninput="InvalidFeedback(this);" placeholder="Message" name="txtFeedback"></textarea>
                                    <input id="submit" class="submit" type="submit" value="Submit" name="action"/>
                                </form>
                            </div>
                        </div>
                        <div id="bottomFooter">
                            <p>© 2014 KSC University. All Rights Reserved</p>
                        </div>
                    </section>
                </div>
            </footer>
            <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
            <script src="CSS-Image-JQuery/Page/js/jquery.flexslider-min.js"></script>
            <script src="CSS-Image-JQuery/Page/js/lightbox.js"></script>
        </div>
    </body>
</html>
