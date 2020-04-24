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
    <script type="text/javascript" src="CSS-Image-JQuery/Page/validate/validate.js"></script>
    <script type="text/javascript" src="CSS-Image-JQuery/DatePicker/JavaScript/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="CSS-Image-JQuery/DatePicker/JavaScript/jquery-ui.min.js"></script>
    <link href="CSS-Image-JQuery/DatePicker/Css/jquery-ui.css" rel="stylesheet" type="text/css" />
    <script>
        $(document).ready(function () {
            $("#datepicker").datepicker();
        });
    </script>
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
                                    if (request.getAttribute("USERINFO") == null) {
                                        request.getRequestDispatcher("UserProfileServlet?action=get").forward(request, response);
                                    }
                                    TblUsers user = (TblUsers) request.getAttribute("USERINFO");
                                    String DOB = user.getDob();
                                    String[] date = DOB.split("/");
                                    DOB = date[1] + "/" + date[0] + "/" + date[2];
                                    boolean gender = user.getGender();

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
                <div id="content">
                    <div class="topImg clearfix">
                        <img src="CSS-Image-JQuery/Page/img/headers/header_1.jpg" alt="About Us">
                        <p>User <strong>Profile</strong></p>
                    </div>
                    <div class="wrapper">
                        <div class="colum c-33 last  clearfix">
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

                        <div class="colum c-67 clearfix">
                            <div class="box contactUs ">
                                <h4><span>Upload Avatar</span></h4>
                                <div class="boxInfo contactForm">
                                    <form id="contact" action="upload_1.jsp" method="POST" enctype="multipart/form-data">
                                        <div>
                                            <label>Image:</label>
                                            <input id="contactSubmit" class="submit" type="submit" value="Submit" name="action"/>
                                            <input id="uploadavarta" type="file" required="true" name="filebox"/>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="colum c-67 clearfix">
                            <div class="box contactUs">
                                <h4><span>Change Account Infomation</span></h4>
                                <div class="boxInfo contactForm">
                                    <form id="contact" action="UserProfileServlet" method="post">
                                        <div>
                                            <label>Name:</label>
                                            <input id="contactName" type="text" pattern=".{0,50}" oninvalid="InvalidUserFullName(this);" oninput="InvalidUserFullName(this);" required="true" value="<%=user.getFullname()%>" name="txtName"/>
                                        </div>
                                        <div>
                                            <label>Birthday:</label>
                                            <input type="text" id="datepicker" value="<%=DOB%>" readonly="true" name="txtDOB">
                                        </div>
                                        <div>
                                            <label>Phone:</label>
                                            <input id="contactPhone" type="tel" pattern="\d{8,11}" oninvalid="InvalidPhone(this);" oninput="InvalidPhone(this);" required="true" value="<%=user.getUserPhone()%>" name="txtPhone"/>
                                        </div>
                                        <div>
                                            <label>Address:</label>
                                            <input type="text" pattern=".{0,500}"  required="true" oninvalid="InvalidUserAddress(this);" oninput="InvalidUserAddress(this);"  required="true" value="<%=user.getUserAddress()%>" name="txtAddr"/>
                                        </div>
                                        <div>
                                            <label>Gender:</label>
                                            <select name="cmbGender" >
                                                <option <%if (gender == false) {%> selected <%}%> value="0">Male</option>
                                                <option <%if (gender == true) {%> selected <%}%> value="1">Female</option>
                                            </select>
                                            <%
                                                        String isSub = "Not Yet";
                                                        if (user.getIsSubscribed() == true) {
                                                            isSub = "Already been";
                                                        }
                                            %>                                            
                                        </div>
                                        <label>Is Subscribed : <%=isSub%> </label>
                                        <div>
                                            <input id="contactSubmit" class="submit" type="submit" value="Submit" name="action"/>
                                        </div>
                                    </form>
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
            <script src="CSS-Image-JQuery/Page/js/jquery.flexslider-min.js"></script>
            <script src="CSS-Image-JQuery/Page/js/lightbox.js"></script>
        </div>
    </body>
</html>
