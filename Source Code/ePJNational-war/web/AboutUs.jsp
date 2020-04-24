<%@page import="nicky.entitybean.TblUsers"%>
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
                                    <li><a href="EventShow.jsp">Event</a></li>
                                    <li class="selected"><a href="AboutUs.jsp">About</a></li>
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
                    <div class="topImg clearfix">
                        <img src="CSS-Image-JQuery/Page/img/headers/header_1.jpg" alt="About Us">
                        <p>About <strong>Us</strong></p>
                    </div>
                    <div class="wrapper">
                        <div class="column c-67 clearfix">
                            <div class="box">
                                <h4>Welcome to My University</h4>
                                <div class="boxInfo">
                                    <img src="CSS-Image-JQuery/Page/img/other/welcome.jpg" alt="">
                                    <div>
                                        <p>Welcome to the University of KSC. People from all walks of life and all parts of the world have been visiting us for nine centuries and we are delighted that via this website you are joining that long tradition. KSC was the first University in the English-speaking world. Our aim is to remain at the forefront of centres of learning, teaching and research .</p>
                                        <p>KSC'’s remarkable global appeal continues to grow. Students from more than a hundred and forty countries and territories make up a student population of over twenty thousand.</p>
                                        <p>But it is not just longevity and global reach that mark KSC out and give the University its special character. There is also our distinctive college and tutorial system which underpins a culture of close academic supervision and careful personal support for our outstanding students.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                                    if (isLoggedin == false) {
                        %>
                        <div class="column c-33 last  clearfix">
                            <div class="box contactUs">
                                <h4><span>We are here</span></h4>
                                <div class="boxInfo">
                                    <p>Find the best routes to and around KSC.
                                        For information on how to get to KSC by road and rail, including transport links from the major London airport</p>
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
                                                                                TblUsers user = (TblUsers) session.getAttribute("USERINFO_PANEL");
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
                                    <%}%>
                                </div>
                            </div>
                        </div>

                        <div class="clear"></div>

                        <div class="column c-33 clearfix">
                            <div class="box">
                                <h4>Principal:<strong>  Jasson Wilss</strong></h4>
                                <div class="boxInfo">
                                    <img class="fwidth" src="CSS-Image-JQuery/Page/img/professors/p1.jpg" alt="">
                                    <h6>Maths professor</h6>
                                    <p>Dr Eamonn Gaffney's research objectives are typically to extract the macroscale consequences of mechanisms operating at much smaller scales, usually the microbiological level, for instance how cells interact and signal, together with the associated biophysics of reaction, .</p>
                                </div>
                            </div>
                        </div>
                        <div class="column c-33 clearfix">
                            <div class="box">
                                <h4>Professor:<strong>  Jasson Wilss</strong></h4>
                                <div class="boxInfo">
                                    <img class="fwidth" src="CSS-Image-JQuery/Page/img/professors/p2.jpg" alt="">
                                    <h6>Chemestry professor</h6>
                                    <p>
                                        Numerical analysis and applied mathematics.
                                        Numerical methods for problems arising in biology and electrochemistry, in particular adaptive finite element methods, high order methods and methods for inverse problems.
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="column c-33 clearfix">
                            <div class="box">
                                <h4>Professor:<strong>  Jasson Wilss</strong></h4>
                                <div class="boxInfo">
                                    <img class="fwidth" src="CSS-Image-JQuery/Page/img/professors/p3.jpg" alt="">
                                    <h6>Communication professor</h6>
                                    <p>I am interested in several areas of pure mathematics. Much of my research lies at the interface of analysis, discrete mathematics and number theory, a particular interest being the application of the first two subjects to the third. I have also worked on topics in group theory and discrete geometry</p>
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
                        <form>
                            <div class="mailInfo">
                                <div>
                                    <h4>Subscribe</h4>
                                    <input id="mailInput" type="email" required="true"/>
                                    <input id="mailSubmit" type="submit" value="Go"/>
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
                                <form>
                                    <textarea id="message" pattern="^[a-zA-Z[\w\s]+$]" required="true" oninvalid="InvalidFeedback(this);" oninput="InvalidFeedback(this);" placeholder="Message"></textarea>
                                    <input id="submit" class="submit" type="submit" value="Submit"/>
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