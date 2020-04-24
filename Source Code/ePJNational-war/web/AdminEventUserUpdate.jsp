<%@page import="java.util.Calendar"%>
<%@page import="nicky.entitybean.TblUsers"%>
<%@page import="nicky.entitybean.TblEvents"%>
<%@page import="nicky.entitybean.TblEnrolls"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>KSC University Administration</title>
        <% if (request.getAttribute("alert") != null) {
                        String alertInfo = (String) request.getAttribute("alert");
        %>
        <script>
            alert("<%=alertInfo%>");
        </script>
        <%}%>
        <link rel="stylesheet" href="CSS-Image-JQuery/Admin/css/screen.css" type="text/css" media="screen" title="default" />
        <link rel="shortcut icon" type="image/x-icon" href="CSS-Image-JQuery/Page/img/favicon.ico" />
        <script src="CSS-Image-JQuery/Admin/validate/validate.js"></script>
        <!--[if IE]>
        <link rel="stylesheet" media="all" type="text/css" href="CSS-Image-JQuery/Admin/css/pro_dropline_ie.css" />
        <![endif]-->
        <%
                    String username = (String) session.getAttribute("admin");
                    boolean isLoggedin = false;
                    if (username != null) {
                        isLoggedin = true;
                    }
                    if (isLoggedin == false) {
                        request.getRequestDispatcher("404.html").forward(request, response);
                    }
                    if (request.getAttribute("INFO") == null) {
                    }
                    TblEnrolls result = (TblEnrolls) request.getAttribute("INFO");
                    TblEvents event = (TblEvents) result.getTblEvents();
                    TblUsers user = (TblUsers) result.getTblEvents().getTblUsers();
                    int isFinished = (Integer) request.getAttribute("ISFINISHED");
                    //int isWinner = 0;
                    //if (result.getTblUsers().equals(user)) {
                    //    isWinner = 1;
                    //}
                    String date = result.getEnrollDate().toString();
                    String[] arrDate = date.split(" ");
                    date = arrDate[0];
                    arrDate = date.split("-");
                    date = arrDate[2] + "/" + arrDate[1] + "/" + arrDate[0];
                    String date1 = "";
                    String[] arrDate1;
                    if (result.getPaymentDate() != null) {
                        date1 = result.getPaymentDate().toString();
                        arrDate1 = date1.split(" ");
                        date1 = arrDate1[0];
                        arrDate1 = date1.split("-");
                        date1 = arrDate1[2] + "/" + arrDate1[1] + "/" + arrDate1[0];
                    }

        %>
        <!--  jquery core -->
        <script src="CSS-Image-JQuery/Admin/js/jquery/jquery-1.4.1.min.js" type="text/javascript"></script>

        <!--  checkbox styling script -->
        <script src="CSS-Image-JQuery/Admin/js/jquery/ui.core.js" type="text/javascript"></script>
        <script src="CSS-Image-JQuery/Admin/js/jquery/ui.checkbox.js" type="text/javascript"></script>
        <script src="CSS-Image-JQuery/Admin/js/jquery/jquery.bind.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function(){
                $('input').checkBox();
                $('#toggle-all').click(function(){
                    $('#toggle-all').toggleClass('toggle-checked');
                    $('#mainform input[type=checkbox]').checkBox('toggle');
                    return false;
                });
            });
        </script>


        <![if !IE 7]>

        <!--  styled select box script version 1 -->
        <script src="CSS-Image-JQuery/Admin/js/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('.styledselect').selectbox({ inputClass: "selectbox_styled" });
            });
        </script>


        <![endif]>


        <!--  styled select box script version 2 -->
        <script src="CSS-Image-JQuery/Admin/js/jquery/jquery.selectbox-0.5_style_2.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('.styledselect_form_1').selectbox({ inputClass: "styledselect_form_1" });
                $('.styledselect_form_2').selectbox({ inputClass: "styledselect_form_2" });
            });
        </script>

        <!--  styled select box script version 3 -->
        <script src="CSS-Image-JQuery/Admin/js/jquery/jquery.selectbox-0.5_style_2.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('.styledselect_pages').selectbox({ inputClass: "styledselect_pages" });
            });
        </script>

        <!--  styled file upload script -->
        <script src="CSS-Image-JQuery/Admin/js/jquery/jquery.filestyle.js" type="text/javascript"></script>
        <script type="text/javascript" charset="utf-8">
            $(function() {
                $("input.file_1").filestyle({
                    image: "CSS-Image-JQuery/Admin/images/forms/upload_file.gif",
                    imageheight : 29,
                    imagewidth : 78,
                    width : 300
                });
            });
        </script>

        <!-- Custom jquery scripts -->
        <script src="CSS-Image-JQuery/Admin/js/jquery/custom_jquery.js" type="text/javascript"></script>

        <!-- Tooltips -->
        <script src="CSS-Image-JQuery/Admin/js/jquery/jquery.tooltip.js" type="text/javascript"></script>
        <script src="CSS-Image-JQuery/Admin/js/jquery/jquery.dimensions.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function() {
                $('a.info-tooltip ').tooltip({
                    track: true,
                    delay: 0,
                    fixPNG: true,
                    showURL: false,
                    showBody: " - ",
                    top: -35,
                    left: 5
                });
            });
        </script>

        <!--  date picker script -->
        <link rel="stylesheet" href="CSS-Image-JQuery/Admin/css/datePicker.css" type="text/css" />
        <script src="CSS-Image-JQuery/Admin/js/jquery/date.js" type="text/javascript"></script>
        <script src="CSS-Image-JQuery/Admin/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
        <script type="text/javascript" charset="utf-8">
            $(function()
            {

                // initialise the "Select date" link
                $('#date-pick')
                .datePicker(
                // associate the link with a date picker
                {
                    createButton:false,
                    startDate:'01/01/2005',
                    endDate:'31/12/2020'
                }
            ).bind(
                // when the link is clicked display the date picker
                'click',
                function()
                {
                    updateSelects($(this).dpGetSelected()[0]);
                    $(this).dpDisplay();
                    return false;
                }
            ).bind(
                // when a date is selected update the SELECTs
                'dateSelected',
                function(e, selectedDate, $td, state)
                {
                    updateSelects(selectedDate);
                }
            ).bind(
                'dpClosed',
                function(e, selected)
                {
                    updateSelects(selected[0]);
                }
            );

                var updateSelects = function (selectedDate)
                {
                    var selectedDate = new Date(selectedDate);
                    $('#d option[value=' + selectedDate.getDate() + ']').attr('selected', 'selected');
                    $('#m option[value=' + (selectedDate.getMonth()+1) + ']').attr('selected', 'selected');
                    $('#y option[value=' + (selectedDate.getFullYear()) + ']').attr('selected', 'selected');
                }
                // listen for when the selects are changed and update the picker
                $('#d, #m, #y')
                .bind(
                'change',
                function()
                {
                    var d = new Date(
                    $('#y').val(),
                    $('#m').val()-1,
                    $('#d').val()
                );
                    $('#date-pick').dpSetSelected(d.asString());
                }
            );

                // default the position of the selects to today
                var today = new Date() ;
                updateSelects(today.getTime());

                // and update the datePicker to reflect it...
                $('#d').trigger('change');
            });

            $(function()
            {

                // initialise the "Select date" link
                $('#date-pick1')
                .datePicker(
                // associate the link with a date picker
                {
                    createButton:false,
                    startDate:'01/01/2005',
                    endDate:'31/12/2020'
                }
            ).bind(
                // when the link is clicked display the date picker
                'click',
                function()
                {
                    updateSelects($(this).dpGetSelected()[0]);
                    $(this).dpDisplay();
                    return false;
                }
            ).bind(
                // when a date is selected update the SELECTs
                'dateSelected',
                function(e, selectedDate, $td, state)
                {
                    updateSelects(selectedDate);
                }
            ).bind(
                'dpClosed',
                function(e, selected)
                {
                    updateSelects(selected[0]);
                }
            );

                var updateSelects = function (selectedDate)
                {
                    var selectedDate = new Date(selectedDate);
                    $('#d1 option[value=' + selectedDate.getDate() + ']').attr('selected', 'selected');
                    $('#m1 option[value=' + (selectedDate.getMonth()+1) + ']').attr('selected', 'selected');
                    $('#y1 option[value=' + (selectedDate.getFullYear()) + ']').attr('selected', 'selected');
                }
                // listen for when the selects are changed and update the picker
                $('#d1, #m1, #y1')
                .bind(
                'change',
                function()
                {
                    var d = new Date(
                    $('#y1').val(),
                    $('#m1').val()-1,
                    $('#d1').val()
                );
                    $('#date-pick1').dpSetSelected(d.asString());
                }
            );

                // default the position of the selects to today
                var today = new Date();
                updateSelects(today.getTime());

                // and update the datePicker to reflect it...
                $('#d1').trigger('change');
            });
        </script>

        <!-- MUST BE THE LAST SCRIPT IN <HEAD></HEAD></HEAD> png fix -->
        <script src="CSS-Image-JQuery/Admin/js/jquery/jquery.pngFix.pack.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $(document).pngFix( );
            });
        </script>
    </head>
    <body>
        <!-- Start: page-top-outer -->
        <div id="page-top-outer">

            <!-- Start: page-top -->
            <div id="page-top">

                <!-- start logo -->
                <div id="logo">
                    <a href=""><img src="CSS-Image-JQuery/Admin/images/shared/logo.png" width="279" height="57" alt="" /></a>
                </div>
                <!-- end logo -->
                <div class="clear"></div>

            </div>
            <!-- End: page-top -->

        </div>
        <!-- End: page-top-outer -->

        <div class="clear">&nbsp;</div>

        <!--  start nav-outer-repeat................................................................................................. START -->
        <div class="nav-outer-repeat">
            <!--  start nav-outer -->
            <div class="nav-outer">

                <!-- start nav-right -->
                <div id="nav-right">

                    <div class="nav-divider">&nbsp;</div>
                    <div class="showhide-account"><img src="CSS-Image-JQuery/Admin/images/shared/nav/nav_statistic.png" width="93" height="14" alt="" /></div>
                    <div class="nav-divider">&nbsp;</div>
                    <div class="showhide-account1"><img src="CSS-Image-JQuery/Admin/images/shared/nav/nav_myaccount.png" width="93" height="14" alt="" /></div>
                    <div class="nav-divider">&nbsp;</div>

                    <!--  start account-content -->
                    <div class="account-content">
                        <div class="account-drop-inner">
                            <a href="AdminReportWinner.jsp" id="acc-details">Top Winners</a>
                            <div class="clear">&nbsp;</div>
                            <div class="acc-line">&nbsp;</div>
                            <a href="AdminReportActiveUser.jsp" id="acc-details">Top Active Users</a>
                            <div class="clear">&nbsp;</div>
                            <div class="acc-line">&nbsp;</div>
                            <a href="AdminReportPopularEvents.jsp" id="acc-details">Top Popular Events</a>
                            <div class="clear">&nbsp;</div>
                            <div class="acc-line">&nbsp;</div>
                            <a href="AdminReportLeastEvents.jsp" id="acc-details">Top Least Events</a>
                        </div>
                    </div>
                    <div class="account-content1">
                        <div class="account-drop-inner">
                            <a href="AdminChangePass.jsp" id="acc-settings">Change Password</a>
                            <div class="clear">&nbsp;</div>
                            <div class="acc-line">&nbsp;</div>
                            <a href="LogoutServlet" id="acc-details">Logout</a>
                        </div>
                    </div>

                    <!--  end account-content -->

                </div>
                <!-- end nav-right -->


                <!--  start nav -->
                <div class="nav">
                    <div class="table">

                        <ul class="select"><li><a href="Admin.jsp"><b>Home</b><!--[if IE 7]><!--></a><!--<![endif]-->
                            </li>
                        </ul>

                        <div class="nav-divider">&nbsp;</div>

                        <ul class="select"><li><a href="AdminUserShow.jsp"><b>User</b><!--[if IE 7]><!--></a><!--<![endif]-->
                                <!--[if lte IE 6]><table><tr><td><![endif]-->
                                <div class="select_sub">
                                    <ul class="sub">
                                        <li><a href="AdminUserShow.jsp">View all users</a></li>
                                        <li><a href="AdminUserInsert.jsp">Add user</a></li>
                                    </ul>
                                </div>
                                <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                            </li>
                        </ul>

                        <div class="nav-divider">&nbsp;</div>

                        <ul class="current"><li><a href="AdminEventShow.jsp"><b>Event</b><!--[if IE 7]><!--></a><!--<![endif]-->
                                <!--[if lte IE 6]><table><tr><td><![endif]-->
                                <div class="select_sub show">
                                    <ul class="sub">
                                        <li><a href="AdminEventShow.jsp">View all event</a></li>
                                        <li><a href="AdminEventInsert.jsp">Add event</a></li>
                                        <li><a href="AdminEventUploadImage.jsp">Image Upload</a></li>
                                    </ul>
                                </div>
                                <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                            </li>
                        </ul>


                        <div class="nav-divider">&nbsp;</div>

                        <ul class="select"><li><a href="AdminFeedbackShow.jsp"><b>Feedback</b></a></li></ul>

                        <div class="nav-divider">&nbsp;</div>

                        <ul class="select"><li><a href="AdminFAQShow.jsp"><b>FAQ</b></a>
                                <!--[if lte IE 6]><table><tr class="alternate-row"><td><![endif]-->
                                <div class="select_sub">
                                    <ul class="sub">
                                        <li><a href="AdminFAQShow.jsp">View all FAQ</a></li>
                                        <li><a href="AdminFAQInsert.jsp">Add FAQ</a></li>
                                    </ul>
                                </div>
                                <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                            </li>
                        </ul>

                        <div class="clear"></div>
                    </div>
                    <div class="clear"></div>
                </div>
                <!--  start nav -->

            </div>
            <div class="clear"></div>
            <!--  start nav-outer -->
        </div>
        <!--  start nav-outer-repeat................................................... END -->

        <div class="clear"></div>

        <!-- start content-outer -->
        <div id="content-outer">
            <!-- start content -->
            <div id="content">


                <div id="page-heading"><h1>Edit Participants Enrollment</h1></div>


                <table border="0" width="100%" cellpadding="0" cellspacing="0" id="content-table">
                    <tr>
                        <th rowspan="3" class="sized"><img src="CSS-Image-JQuery/Admin/images/shared/side_shadowleft.jpg" width="20" height="300" alt="" /></th>
                        <th class="topleft"></th>
                        <td id="tbl-border-top">&nbsp;</td>
                        <th class="topright"></th>
                        <th rowspan="3" class="sized"><img src="CSS-Image-JQuery/Admin/images/shared/side_shadowright.jpg" width="20" height="300" alt="" /></th>
                    </tr>
                    <tr>
                        <td id="tbl-border-left"></td>
                        <td>
                            <!--  start content-table-inner -->
                            <div id="content-table-inner">

                                <table border="0" width="100%" cellpadding="0" cellspacing="0">
                                    <tr valign="top">
                                        <td>


                                            <!--  start step-holder -->
                                            <div id="step-holder">
                                                <div class="step-no-off">1</div>
                                                <div class="step-light-left">Choose Event</div>
                                                <div class="step-light-right">&nbsp;</div>
                                                <div class="step-no">2</div>
                                                <div class="step-dark-left"><a href="">Edit List details</a></div>
                                                <div class="step-dark-right">&nbsp;</div>
                                                <div class="clear"></div>
                                            </div>
                                            <!--  end step-holder -->
                                            <form action="AdminEventUserUpdateServlet" method="post">
                                                <!-- start id-form -->
                                                <table border="0" cellpadding="0" cellspacing="0"  id="id-form">
                                                    <%
                                                                int enroll = result.getEnrollID();
                                                    %>

                                                    <input type="hidden" name="enrollID" value="<%=result.getEnrollID()%>"/>
                                                    <tr>
                                                        <th valign="top">Username:</th>
                                                        <td>
                                                            <input type="text"  class="inp-form" value="<%=result.getTblUsers().getUsername()%>" readonly="true" /></td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th valign="top">Enroll date:</th>
                                                        <td>
                                                            <input type="text" id="" name="txtDay" value="<%=date%>" class="inp-form"  readonly="true" /></td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th valign="top">Fee ($):</th>
                                                        <td><input type="text" name="txtFee" class="inp-form" value="<%=result.getEnrollFee()%>" placeholder="Ex : 100" pattern="\d{0,}" oninvalid="InvalidFee(this);" oninput="InvalidFee(this);" required="true" readonly="true"/></td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th valign="top">Payment date:</th>
                                                        <td>
                                                            <input type="text" id="" name="txtPayment" value="<%=date1%>" class="inp-form"  readonly="true" /></td>
                                                        <td></td>
                                                    </tr>
                                                    <%if (isFinished == 0) {%>
                                                    <tr>
                                                        <th valign="top">Status:</th>
                                                        <td>
                                                            <select name="txtStt"  class="styledselect_form_1" >
                                                                <option  value="1"<%if (result.getEnrollStt() == 1) {%>selected<%}%> >Activated</option>
                                                                <option <%if (result.getEnrollStt() != 1) {%>selected<%}%> value="0">Inactivated</option>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <%}%>
                                                    <%if (isFinished == 1) {%>
                                                    <tr>
                                                        <th valign="top">Score:</th>
                                                        <td><input type="text" name="txtScore" class="inp-form" value="<%=result.getEventScore()%>" placeholder="Ex : 50" pattern="\d{0,3}" oninvalid="InvalidScore(this);" oninput="InvalidScore(this);" required="true" /></td>
                                                        <td></td>
                                                    </tr>
                                                    <%}%>

                                                    <tr>
                                                        <th>&nbsp;</th>
                                                        <td valign="top">
                                                            <input type="submit" name="action" value="Submit" class="form-submit" />
                                                            <a href="AdminEventUserShowServlet?action=SearchAll&EventID=<%=result.getTblEvents().getEventID()%>"> <input type="button"  value="" class="form-reset"  /></a>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                </table>
                                                <!-- end id-form  -->
                                            </form>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td><img src="CSS-Image-JQuery/Admin/images/shared/blank.gif" width="695" height="1" alt="blank" /></td>
                                        <td></td>
                                    </tr>
                                </table>

                                <div class="clear"></div>

                            </div>
                            <!--  end content-table-inner  -->
                        </td>
                        <td id="tbl-border-right"></td>
                    </tr>
                    <tr>
                        <th class="sized bottomleft"></th>
                        <td id="tbl-border-bottom">&nbsp;</td>
                        <th class="sized bottomright"></th>
                    </tr>
                </table>
                <div class="clear">&nbsp;</div>

            </div>
            <!--  end content -->
            <div class="clear">&nbsp;</div>
        </div>
        <!--  end content-outer -->

        <div class="clear">&nbsp;</div>

        <!-- start footer -->
        <div id="footer">
            <!--  start footer-left -->
            <div id="footer-left">
	Admin Skin &copy; Copyright KSC University Administration Ltd. <a href="">www.netdreams.co.uk</a>. All rights reserved.</div>
            <!--  end footer-left -->
            <div class="clear">&nbsp;</div>
        </div>
        <!-- end footer -->

    </body>
</html>