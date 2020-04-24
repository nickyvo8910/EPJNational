<%@page import="nicky.entitybean.TblUsers"%>
<%@page import="nicky.entitybean.TblEnrolls"%>
<%@page import="java.util.Calendar"%>
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
<script type="text/javascript" src="CSS-Image-JQuery/DatePicker/JavaScript/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="CSS-Image-JQuery/DatePicker/JavaScript/jquery-ui.min.js"></script>
    <link href="CSS-Image-JQuery/DatePicker/Css/jquery-ui.css" rel="stylesheet" type="text/css" />
    <script>
        $(document).ready(function () {
            $("#datepicker").datepicker();
        });

        $(document).ready(function () {
            $("#datepicker1").datepicker();
        });
    </script>
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
                                    Calendar calCrr = Calendar.getInstance();
                                    Calendar cal = Calendar.getInstance();
                        %>
                    </div>
                </div>
                <div>

                    <div id="menu">
                        <div id="pageheader">
                            <section>
                                <a href="index.jsp"><img src="CSS-Image-JQuery/Page/img/logo.png" alt=""></a>
                                <ul class="clearfix">
                                    <li><a href="Home.jsp">Home</a></li>
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
            <div id="contentBk" class="clearfix">
                <%
                            TblEvents[] events;
                            TblEnrolls[] enrolls;
                            int b;
                            if (request.getAttribute("INFO") == null || request.getAttribute("ENROLLINFO") == null) {
                                request.getRequestDispatcher("MyEventServlet?action=Search").forward(request, response);
                            }
                            events = (TblEvents[]) request.getAttribute("INFO");
                            enrolls = (TblEnrolls[]) request.getAttribute("ENROLLINFO");
                            b = events.length;

                %>
                <div id="content">
                    <link rel="stylesheet" href="CSS-Image-JQuery/Page/css/style.css" type="text/css" />
                    <script src="CSS-Image-JQuery/Page/js/jquery.paginate.js" type="text/javascript"></script>
                    <script type="text/javascript">
                        $(document).ready(function () {
                            var a = <%=b%>;
                            $('#green').smartpaginator({ totalrecords: a, recordsperpage: 3, length: 2,
                                datacontainer: 'product-table', controlsalways: true, initval: 1,
                                dataelement: 'span', next: 'Next', prev: 'Prev', first: 'First', last: 'Last',
                                go: 'Go',theme: 'green'
                            });
                        });
                    </script>
                    <div class="topImg clearfix">
                        <img src="CSS-Image-JQuery/Page/img/headers/header_1.jpg" alt="About Us">
                        <p>My <strong>Event</strong></p>
                    </div>

                    <div class="wrapper">

                        <div id="product-table" class="course column c-67 clearf ix">

                            <h3 class="title">My Event <strong>View</strong></h3>

                            <%
                                        if (events != null && enrolls != null) {
                                            for (int i = 0; i < b; i++) {

                                                cal.setTime(enrolls[i].getEnrollDate());
                                                String dateEnroll = cal.get(Calendar.YEAR) + "." + (cal.get(Calendar.MONTH)+1) + "." + cal.get(Calendar.DAY_OF_MONTH);

                                                cal.setTime(events[i].getEventDate());
                                                String date = cal.get(Calendar.YEAR) + "." + (cal.get(Calendar.MONTH)+1) + "." + cal.get(Calendar.DAY_OF_MONTH);
                                                String statusEvent;
                                                if (events[i].getEventStt() == 1) {
                                                    statusEvent = "Happened";
                                                } else if (events[i].getEventStt() == 2) {
                                                    statusEvent = "Canceled";
                                                } else {
                                                    statusEvent = "Not Yet Happened";
                                                }
                                                String des = events[i].getEventDes();
                                                if (des.length() > 50) {
                                                    des = des.substring(0, 50) + "...";
                                                }
                                                String name = events[i].getEventName();
                                                if (name.length() > 50) {
                                                    name = name.substring(0, 50) + "...";
                                                }
                                                String host = events[i].getEventHost();
                                                if (host.length() > 30) {
                                                    host = host.substring(0, 30) + "...";
                                                }
                            %>
                            <span>
                                <div class="box">

                                    <ul class="courseId">
                                        <li>Event Name: <%=name%></li>
                                        <li>Event Status: <%=statusEvent%></li>
                                    </ul>
                                    <div class="boxInfo">
                                        <p><%=des%> <a href="EventDetailServlet?EventID=<%=events[i].getEventID()%>">Read more</a> </p>
                                        <form action="MyEventServlet" method="post">
                                            <input type="hidden" name="eventID" value="<%=events[i].getEventID()%>"/>
                                            <%if (cal.getTime().after(calCrr.getTime())) {%>
                                            <input id="UnEnroll" class="submit" type="submit" value="Unenroll" name="action"/>
                                            <input id="Upload" class="submit" type="submit" value="Contents" name="action"/>
                                            <%}%>
                                        </form>
                                    </div>
                                    <div class="courseInfo">
                                        <p class="courseTime">Event Date: <%=date%>  ||  Enroll Date : <%=dateEnroll%></p>
                                        <ul>
                                            <li class="courseInstructor">Host: <%=host%></li>
                                        </ul>
                                    </div>
                                </div>

                            </span>
                            <%}
                                        }%>

                            <div id="green"></div>




                        </div>
                         <div class="searchCourse searchCourseHome column c-33 clearfix">
                            <p>Search Event</p>
                            <form action="MyEventServlet" method="post">
                                <input class="focus input" type="text" id="datepicker" placeholder="Search From" name="txtFrom"/>
                                <input class="focus input" type="text" id="datepicker1" placeholder="Search To" name="txtTo"/>
                                <input class="submit" type="submit" value="Search by Date" name="action"/>
                           </form>
                                 <form action="MyEventServlet" method="post">
                                <input class="focus input" name="txtSearch" type="text" placeholder=" Search By Event Name" />
                                <input class="submit" name="action" type="submit" value="Search by Name" />
                                </form>

                        </div>
                               <div class="column c-33 last  clearfix">
                            <div class="links">
                                <div class="box contactUs clearfix boxInfo">
                                    <%
                                                                            if (session.getAttribute("USERINFO_PANEL") != null) {
                                                                                TblUsers user = (TblUsers) session.getAttribute("USERINFO_PANEL");
                                    %>
                                    <h4>User <strong>Setting </strong> <%=user.getUsername()%></h4>


                                    <img src="images/userAvatar/<%=user.getUserImg()%>" alt="" class="imgBorder" id="avarta" style="width: 70px;height: 50px" >
                                    <%}else{%>
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