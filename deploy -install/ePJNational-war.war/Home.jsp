<%@page import="nicky.entitybean.TblEvents"%>
<%@page import="nicky.entitybean.TblUsers"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<head>

    <meta charset="utf-8">

    <title>KSC University</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <script type="text/javascript" src="CSS-Image-JQuery/Page/validate/validate.js" ></script>
    <link rel="stylesheet" href="CSS-Image-JQuery/Page/css/main.css">
    <link rel="shortcut icon" type="image/x-icon" href="CSS-Image-JQuery/Page/img/favicon.ico" />

    <!--[if lt IE 9]>
     <script src="js/html5shiv.js"></script>
    <![endif]-->

</head>
<html>
    <body>
        <%
                    //session.setAttribute("username", "Brroke1404@yahoo.com");
                    if (session.getAttribute("recentEvent") == null || session.getAttribute("topActive") == null || session.getAttribute("topPopular") == null || session.getAttribute("recentEvent") == null || session.getAttribute("topWinner") == null) {
                        response.sendRedirect("PageLoadServlet?action=get&target=Home.jsp");
                    } else {%>
        <div id="pageheader">
            <header>
                <div class="parentContainer">
                    <div id="socialButtons">

                        <%
                                                TblEvents[] recentEvent = (TblEvents[]) session.getAttribute("recentEvent");
                                                TblEvents[] topPopular = (TblEvents[]) session.getAttribute("topPopular");
                                                TblUsers[] topActive = (TblUsers[]) session.getAttribute("topActive");
                                                TblUsers[] topWinner = (TblUsers[]) session.getAttribute("topWinner");
                                                if (session.getAttribute("username") == null) {
                                                    session.setAttribute("username", "guest@yahoo.com");
                                                }
                                                //if (session.getAttribute("updateEvent") == null) {
                                                //    request.getRequestDispatcher("PageLoadServlet").forward(request, response);
                                                //}
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
                                    <li class="selected"><a href="Home.jsp">Home</a></li>
                                    <li><a href="EventShow.jsp">Event</a></li>
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
            <div id="contentBk">
                <div id="content" class="clearfix">

                    <div id="homeSlider" class="clearfix flexslider">

                        <div class="thumbs"></div>

                        <ul class="slides">
                            <li data-thumb="CSS-Image-JQuery/Page/img/slider/1st_thumb.jpg"><img src="CSS-Image-JQuery/Page/img/slider/1st.jpg" alt="A Classroom"></li>
                            <li data-thumb="CSS-Image-JQuery/Page/img/slider/2nd_thumb.jpg"><img src="CSS-Image-JQuery/Page/img/slider/2nd.jpg" alt="Just a different perspective over this new issue"></li>
                            <li data-thumb="CSS-Image-JQuery/Page/img/slider/3rd_thumb.jpg"><img src="CSS-Image-JQuery/Page/img/slider/3rd.jpg" alt="A Classroom"></li>
                            <li data-thumb="CSS-Image-JQuery/Page/img/slider/4th_thumb.jpg"><img src="CSS-Image-JQuery/Page/img/slider/4th.jpg" alt="Just a different perspective over this new issue"></li>
                        </ul>

                        <ul class="captions">
                            <li>
                                <h3>A student <strong>reading</strong></h3>
                                <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo, perspiciatis unde omnis.</p>
                            </li>
                            <li>
                                <h3>Just a different <em>perspective</em> over this new issue</h3>
                                <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                            </li>
                            <li>
                                <h3>Look at <strong>the baloon!</strong></h3>
                                <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo, perspiciatis unde omnis.</p>
                            </li>
                            <li>
                                <h3>90% of the people have back issues</h3>
                                <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                            </li>
                        </ul>

                    </div>

                    <div class="wrapper">

                        <div class="welcome column c-67 clearfix">
                            <h3>Welcome to My University</h3>
                            <div class="cContent clearfix">
                                <img src="CSS-Image-JQuery/Page/img/other/welcome.jpg" alt="">
                                <div>
                                    <p>Welcome to the University of KSC. People from all walks of life and all parts of the world have been visiting us for nine centuries and we are delighted that via this website you are joining that long tradition. KSC was the first University in the English-speaking world. Our aim is to remain at the forefront of centres of learning, teaching and research .<a href="AboutUs.jsp"> Read more</a></p>
                                </div>
                            </div>
                        </div>

                        <div class="searchCourse searchCourseHome column c-33 clearfix">
                            <p>Search Event</p>
                            <form action="HomeServlet" method="post">
                                <input class="focus input" type="text" value=" Search By Event Name" name="txtSearch"/>
                                <input class="submit" type="submit" value="Search" name="action"/>
                            </form>
                        </div>

                        <div class="clear"></div>

                        <div class="news column c-33 clearfix">
                            <h3>Active Users</h3>
                            <div class="arrows"></div>
                            <div class="cContent clearfix rotator">
                                <ul class="slides">
                                    <%int record = 6;
                                                            int count = 0;
                                                            for (int li = 0; li < record / 2; li++) {%>
                                    <li>
                                        <%count++;%>
                                        <div class="post">
                                            <img src="images/userAvatar/<%=topActive[count].getUserImg()%>" alt="" class="imgBorder" style="width: 70px;height: 50px">
                                            <div class="info">
                                                <a href="#"><h5>User <%=count%></h5></a>
                                                <span><%=topActive[count].getUsername()%></span>
                                                <p><%=topActive[count].getFullname()%></p>
                                            </div>
                                        </div>
                                        <%count++;%>
                                        <div class="post">
                                            <img src="images/userAvatar/<%=topActive[count].getUserImg()%>" alt="" class="imgBorder" style="width: 70px;height: 50px">
                                            <div class="info">
                                                <a href="#"><h5>User <%=count%></h5></a>
                                                <span><%=topActive[count].getUsername()%></span>
                                                <p><%=topActive[count].getFullname()%></p>
                                            </div>
                                        </div>
                                    </li>
                                    <%}%>
                                </ul>
                            </div>
                        </div>

                        <div class="news column c-33 clearfix">
                            <h3>Popular Events</h3>
                            <div class="arrows"></div>
                            <div class="cContent clearfix rotator">
                                <ul class="slides">
                                    <%count = 0;
                                                            for (int li = 0; li < record / 2; li++) {%>
                                    <li>
                                        <%count++;%>
                                        <div class="post">
                                            <img src="images/event/<%=topPopular[count].getEventImg()%>" alt="" class="imgBorder" style="width: 70px;height: 50px">
                                            <div class="info">
                                                <a href="#"><h5>Event <%=count%></h5></a>
                                                <span>Total Enroll :  <%=topPopular[count].getTblEnrollsCollection().size()%></span>
                                                <p><%=topPopular[count].getEventName()%></p>
                                            </div>
                                        </div>
                                        <%count++;%>
                                        <div class="post">
                                            <img src="images/event/<%=topPopular[count].getEventImg()%>" alt="" class="imgBorder" style="width: 70px;height: 50px">
                                            <div class="info">
                                                <a href="#"><h5>Event <%=count%></h5></a>
                                                <span>Total Enroll :  <%=topPopular[count].getTblEnrollsCollection().size()%></span>
                                                <p><%=topPopular[count].getEventName()%></p>
                                            </div>
                                        </div>
                                    </li>
                                    <%}%>
                                </ul>
                            </div>
                        </div>
                        <%
                                                if (isLoggedin == false) {
                        %>
                        <div class="news column c-33 clearfix">
                            <h3>Winner List</h3>
                            <div class="arrows"></div>
                            <div class="cContent clearfix rotator">
                                <ul class="slides">
                                    <%count = 0;
                                                                            for (int li = 0; li < record / 2; li++) {%>
                                    <li>
                                        <%count++;%>
                                        <div class="post">
                                            <img src="images/userAvatar/<%=topWinner[count].getUserImg()%>" alt="" class="imgBorder" style="width: 70px;height: 50px">
                                            <div class="info">
                                                <a href="#"><h5>User <%=count%></h5></a>
                                                <span><%=topWinner[count].getUsername()%></span>
                                                <p><%=topWinner[count].getFullname()%></p>
                                            </div>
                                        </div>
                                        <%count++;%>
                                        <div class="post">
                                            <img src="images/userAvatar/<%=topWinner[count].getUserImg()%>" alt="" class="imgBorder" style="width: 70px;height: 50px"  >
                                            <div class="info">
                                                <a href="#"><h5>User <%=count%></h5></a>
                                                <span><%=topWinner[count].getUsername()%></span>
                                                <p><%=topWinner[count].getFullname()%></p>
                                            </div>
                                        </div>
                                    </li>
                                    <%}%>
                                </ul>
                            </div>
                        </div>
                        <%} else {%>
                        <div class="column c-33 last  clearfix">
                            <div class="links">
                                <div class="box contactUs clearfix boxInfo">
                                    <%
                                                                            if (session.getAttribute("USERINFO_PANEL") != null) {
                                                                                TblUsers user = (TblUsers) session.getAttribute("USERINFO_PANEL");
                                                                                String img = user.getUserImg();
                                    %>
                                    <h4>User <strong>Setting </strong> <%=user.getUsername()%></h4>


                                    <img src="images/userAvatar/<%=user.getUserImg()%>" alt="" class="imgBorder" id="avarta" style="width: 70px;height: 50px" >
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

                        <div class="event column c-67 clearfix">
                            <h3>Featured Event</h3>
                            <div class="arrows"></div>
                            <div class="cContent clearfix rotator">
                                <ul class="slides">
                                    <li><a href="CSS-Image-JQuery/Page/img/events/e1_large.jpg" rel="lightbox[events]" class="grayColor"><img data-color="CSS-Image-JQuery/Page/img/events/e1.jpg" src="CSS-Image-JQuery/Page/img/events/e1_gray.jpg" class="imgBorder" alt=""></a></li>
                                    <li><a href="CSS-Image-JQuery/Page/img/events/e2_large.jpg" rel="lightbox[events]" class="grayColor"><img data-color="CSS-Image-JQuery/Page/img/events/e2.jpg" src="CSS-Image-JQuery/Page/img/events/e2_gray.jpg" class="imgBorder" alt=""></a></li>
                                    <li><a href="CSS-Image-JQuery/Page/img/events/e3_large.jpg" rel="lightbox[events]" class="grayColor"><img data-color="CSS-Image-JQuery/Page/img/events/e3.jpg" src="CSS-Image-JQuery/Page/img/events/e3_gray.jpg" class="imgBorder" alt=""></a></li>
                                    <li><a href="CSS-Image-JQuery/Page/img/events/e4_large.jpg" rel="lightbox[events]" class="grayColor"><img data-color="CSS-Image-JQuery/Page/img/events/e4.jpg" src="CSS-Image-JQuery/Page/img/events/e4_gray.jpg" class="imgBorder" alt=""></a></li>
                                    <li><a href="CSS-Image-JQuery/Page/img/events/e5_large.jpg" rel="lightbox[events]" class="grayColor"><img data-color="CSS-Image-JQuery/Page/img/events/e5.jpg" src="CSS-Image-JQuery/Page/img/events/e5_gray.jpg" class="imgBorder" alt=""></a></li>
                                    <li><a href="CSS-Image-JQuery/Page/img/events/e6_large.jpg" rel="lightbox[events]" class="grayColor"><img data-color="CSS-Image-JQuery/Page/img/events/e6.jpg" src="CSS-Image-JQuery/Page/img/events/e6_gray.jpg" class="imgBorder" alt=""></a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="featured column c-33 clearfix">
                            <h3>Featured Professor</h3>
                            <div class="cContent">
                                <img class="imgBorder" src="CSS-Image-JQuery/Page/img/professors/1st.jpg" alt="">
                                <div>
                                    <h5>Jason Wills</h5>
                                    <p>Host picture is here... </p>
                                    <a href="#">Read more</a>
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
                                    <li><a href="EventDetailServlet?EventID=<%=recentEvent[0].getEventID()%>"><%=recentEvent[0].getEventName()%></a></li>
                                    <li><a href="EventDetailServlet?EventID=<%=recentEvent[1].getEventID()%>"><%=recentEvent[1].getEventName()%></a></li>
                                    <li><a href="EventDetailServlet?EventID=<%=recentEvent[2].getEventID()%>"><%=recentEvent[2].getEventName()%></a></li>
                                    <li><a href="EventDetailServlet?EventID=<%=recentEvent[3].getEventID()%>"><%=recentEvent[3].getEventName()%></a></li>
                                    <li><a href="EventDetailServlet?EventID=<%=recentEvent[4].getEventID()%>"><%=recentEvent[4].getEventName()%></a></li>
                                </ul>
                            </div>

                            <div class="news column c-40 clearfix">
                                <h3>Popular Posts Widget</h3>
                                <div class="clearfix">
                                    <div class="post clearfix">
                                        <img src="CSS-Image-JQuery/Page/img/blog/event.jpg" alt="" class="imgBorder">
                                        <div class="footerpost">
                                            <a href="#"><h5>Admissions open for 2012</h5></a>
                                            <span>Posted on 07 jan 2012</span>
                                            <p>Lorem ipsum dolor sit amet set..</p>
                                        </div>
                                    </div>
                                    <div class="post clearfix ">
                                        <img src="CSS-Image-JQuery/Page/img/blog/event.jpg" alt="" class="imgBorder">
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
            <script src="CSS-Image-JQuery/Page/js/scripts.js"></script>
        </div>
        <%}%>
    </body>
</html>