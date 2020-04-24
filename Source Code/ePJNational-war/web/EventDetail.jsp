
<%@page import="java.util.Calendar"%>
<%@page import="nicky.entitybean.TblUsers"%>
<%@page import="java.util.Collection"%>
<%@page import="nicky.entitybean.TblComments"%>
<%@page import="nicky.sessbean.NickySessionBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="nicky.entitybean.TblEvents"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<head>

    <meta charset="utf-8">

    <title>KSC University</title>
    <script type="text/javascript" src="CSS-Image-JQuery/Page/validate/validate.js" ></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
    <link rel="stylesheet" href="CSS-Image-JQuery/Page/css/main.css">
    <link rel="shortcut icon" type="image/x-icon" href="CSS-Image-JQuery/Page/img/favicon.ico" />

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
                                    Calendar calCrr = Calendar.getInstance();
                                    Calendar cal = Calendar.getInstance();
                                    String username = (String) session.getAttribute("username");
                                    boolean isLoggedin = false;
                                    if (username != null && !username.equals("guest@yahoo.com")) {
                                        isLoggedin = true;
                                    }
                                    if (isLoggedin == false) {
                        %>
                        <a href="Registration.jsp" id="linked">
                            <p>Sign Up</p>
                            <span>linked </span>
                        </a>
                        <a href="Login.jsp" id="facebook">
                            <p>Sign In</p>
                            <span>Facebook</span>
                        </a>
                        <% } else {%>
                        <% }%>
                    </div>
                </div>
                <div>

                    <div id="menu">
                        <div id="pageheader">
                            <section>
                                <a href="index.jsp"><img src="CSS-Image-JQuery/Page/img/logo.png" alt=""></a>
                                <ul class="clearfix">
                                    <li><a href="Home.jsp">Home</a></li>
                                    <li class="selected"><a href="EventShow.jsp">Event</a></li>
                                    <li><a href="AboutUs.jsp">About</a></li>
                                    <li><a href="ContactUs.jsp">Contact</a></li>
                                    <li><a href="FAQ.jsp">FAQ</a></li>
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
                    <%int b = 5;
                                if (request.getAttribute("TOTALCOMMENT") != null) {
                                    b = (Integer) request.getAttribute("TOTALCOMMENT");
                                }
                    %>
                    <link rel="stylesheet" href="CSS-Image-JQuery/Page/css/style.css" type="text/css" />
                    <script src="CSS-Image-JQuery/Page/js/jquery.paginate.js" type="text/javascript"></script>
                    <script type="text/javascript">
                        $(document).ready(function () {
                            var a = <%=b%>;
                            $('#green').smartpaginator({ totalrecords: a, recordsperpage: 5, length: 4,
                                datacontainer: 'product-table', controlsalways: true, initval: 1,
                                dataelement: 'ul', next: 'Next', prev: 'Prev', first: 'First', last: 'Last',
                                go: 'Go',theme: 'green'
                            });
                        });
                    </script>
                    <div class="topImg clearfix">
                        <img src="CSS-Image-JQuery/Page/img/headers/header_2.jpg" alt="About Us">
                        <p>Event <strong>Detail</strong></p>
                    </div>
                    <div class="wrapper">

                        <div class="blog column c-67 clearfix">
                            <%
                                        if (request.getAttribute("INFO") == null) {
                                            request.getRequestDispatcher("Home.jsp").forward(request, response);
                                        } else {
                                            TblEvents event = (TblEvents) request.getAttribute("INFO");
                                            cal.setTime(event.getEventDate());
                                            String date = cal.get(Calendar.YEAR) + "." + (cal.get(Calendar.MONTH) + 1) + "." + cal.get(Calendar.DAY_OF_MONTH);
                                            String stt;
                                            int totalComment = 0;
                                            if (request.getAttribute("TOTALCOMMENT") != null) {
                                                totalComment = (Integer) request.getAttribute("TOTALCOMMENT");
                                            }
                                            if (event.getEventStt() == 1) {
                                                stt = "Happened";
                                            } else if (event.getEventStt() == 2) {
                                                stt = "Cancel";
                                            } else {
                                                stt = "Not yet Happened";
                                            }
                            %>
                            <div class="box">
                                <h4><%=event.getEventName()%></h4>
                                <div class="boxInfo examInfo">
                                    <ul>
                                        <li class="proExm"><a href="#"><%=event.getEventHost()%></a></li>
                                        <li class="dateExm"><%=date%></li>
                                        <li class="exm"><a href="#"><%=stt%></a></li>


                                    </ul>
                                    <div>
                                        <img class="fwidth" src="images/event/<%=event.getEventImg()%>" alt="" />
                                        <p>Fee :<%=event.getEventPrice()%>(USD)</p>
                                        <p>Winner :
                                            <%if (event.getTblUsers() != null) {%>
                                            <%=event.getTblUsers().getFullname()%>
                                            <%} else {%> Event Have No Winner<%}%>
                                        </p>
                                        <p style="color: red">Notice : Enroll Fee is free to KSC University's Students</p>
                                        <%if (cal.getTime().after(calCrr.getTime()) && isLoggedin) {%>
                                        <form action="EventShowServlet" method="post">
                                            <input type="hidden" name="eventID" value="<%=event.getEventID()%>"/>
                                            <input class="submit" name="action" type="submit" value="Enroll" name="action"/>
                                        </form>
                                        <a href="AdminDownloadContentServlet?action=download&eventID=<%=event.getEventID()%>&username=Admin@mail.com"  title="Download" class="submit">Content</a>
                                        <%}%>
                                        <p><%=event.getEventDes()%></p>
                                    </div>
                                </div>
                            </div>

                            <div  id="product-table" class="box">
                                <%if (request.getAttribute("COMMENTS") != null) {
                                                                                TblComments[] comment = (TblComments[]) request.getAttribute("COMMENTS");
                                %>
                                <h4>Comment</h4>
                                <%

                                    for (int i = 0; i < comment.length; i++) {
                                        TblUsers user = comment[i].getTblUsers();
                                %>
                                <ul class="boxInfo comment">

                                    <li class="clearfix">
                                        <img src="<%=user.getUserImg()%>" alt="" class="imgBorder">
                                        <div class="first">
                                            <div class="box boxInfo">
                                                <h6><%=user.getFullname()%> <span><%=user.getUsername()%></span></h6>

                                                <%
                                                                                        String ANS = comment[i].getEventCom();
                                                                                        String ANSProcess = "";
                                                                                        for (int j = 0; j < ANS.length() - 20; j += 10) {
                                                                                            if (!ANS.substring(j, j + 10).contains(" ")) {
                                                                                                ANSProcess += ANS.substring(j, j + 10) + " " + ANS.substring(j + 11, j + 20);
                                                                                            }
                                                                                        }
                                                %>
                                                <%if (ANSProcess.length() < 50) {%>
                                                <p><%=ANS%></p>
                                                <%} else {%>
                                                <p><%=ANSProcess%></p>
                                                <%}%>
                                                <span class="postDate"> <%=comment[i].getCmtDate()%> </span>
                                            </div>
                                        </div>
                                    </li>



                                </ul>
                                <%}
                                                                            }
                                %>
                                <div id="green"></div>
                            </div>
                            <%
                                                                        boolean enrolled = false;
                                                                        if (request.getAttribute("Enrolled") != null) {
                                                                            enrolled = (Boolean) request.getAttribute("Enrolled");
                                                                        }
                                                                        if (enrolled) {
                            %>
                            <div class="box contactUs ">
                                <h4><span>Leave a Comment</span></h4>
                                <form action="InsertCommentServlet" method="post">
                                    <div class="boxInfo contactForm">
                                        <div>
                                            <label>Message:</label>
                                            <textarea id="contactMessage" pattern="^[a-zA-Z[\w\s]+$]" oninvalid="InvalidComment(this);" oninput="InvalidComment(this);" required="true" type="text" name="txtComment"></textarea>
                                        </div>
                                        <div class="contact">
                                            <input type="hidden" name="EventID" value="<%=event.getEventID()%>"/>
                                            <input id="contactSubmit" class="submit" type="submit" value="Submit Comment"/>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <%}
                                        }%>
                        </div>
                        <%
                                    if (isLoggedin == false) {
                        %>
                        <div class="column c-33 last  clearfix">
                            <div class="box contactUs">
                                <h4><span>We are here</span></h4>
                                <div class="boxInfo">
                                    <p>E104 Dharti II,<br> Ahmedabad <br>Gujarat, India <br><br><strong><a href="#">behappy@tmail.com</a><br> +91 123 456 7890</strong></p>
                                </div>
                            </div>
                        </div>
                        <%} else {%>
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
                        <%}%>
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

            <script src="CSS-Image-JQuery/Page/js/jquery.flexslider-min.js"></script>
            <script src="CSS-Image-JQuery/Page/js/lightbox.js"></script>
        </div>
    </body>
</html>
