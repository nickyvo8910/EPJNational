<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>KSC University Administration</title>
        <link rel="stylesheet" href="CSS-Image-JQuery/Admin/css/screen.css" type="text/css" media="screen" title="default" />
        <link rel="shortcut icon" type="image/x-icon" href="CSS-Image-JQuery/Page/img/favicon.ico" />
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
                    image: "CSS-Image-JQuery/Admin/images/forms/choose-file.gif",
                    imageheight : 21,
                    imagewidth : 78,
                    width : 310
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
                var today = new Date();
                updateSelects(today.getTime());

                // and update the datePicker to reflect it...
                $('#d').trigger('change');
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
                <div id="page-heading-logo"><h1>STATISTIC REPORT</h1></div>
                <div id="page-heading-Name"><h1>Report Name: TOP WINNER</h1></div>
                 <div id="page-bottom-date1"><h1 class="DateStig">From: 12/04/2014</h1>
                   
                </div>
                <div id="page-bottom-date1"><h1 class="DateStig">To : 21/04/2014</h1>
                <br/>
                <br/>
                </div>
                <!--end logo-->
                <!-- start logo-->

                <!--end logo-->
                <table border="0" width="100%" cellpadding="0" cellspacing="0" id="content-table">
                    <tr class="alternate-row">
                        <th rowspan="3" class="sized"><img src="CSS-Image-JQuery/Admin/images/shared/side_shadowleft.jpg" width="20" height="300" alt="" /></th>
                        <th class="topleft"></th>
                        <td id="tbl-border-top">&nbsp;</td>
                        <th class="topright"></th>
                        <th rowspan="3" class="sized"><img src="CSS-Image-JQuery/Admin/images/shared/side_shadowright.jpg" width="20" height="300" alt="" /></th>
                    </tr>
                    <tr class="alternate-row">
                        <td id="tbl-border-left"></td>
                        <td>
                            <!--  start content-table-inner ...................................................................... START -->
                            <div id="content-table-inner">

                                <!--  start table-content  -->
                                <div id="table-content">




                                    <!--  start product-table ..................................................................................... -->
                                    <form id="mainform" action="">
                                      <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
                                                <tr class="alternate-row">
                                                    <th class="table-header-repeat line-left"><a href="">No.</a></th>
                                                    <th class="table-header-repeat line-left minwidth-1"><a href="">Username</a></th>
                                                    <th class="table-header-repeat line-left minwidth-1"><a href="">Fullname</a></th>
                                                    <th class="table-header-repeat line-left minwidth-1"><a href="">Phone</a></th>
                                                    <th class="table-header-repeat line-left"><a href="">Total Score</a></th>
                                                    <th class="table-header-repeat line-left"><a href="">Total Event</a></th>


                                                </tr>
                                                <tr class="alternate-row">
                                                    <td>1</td>
                                                    <td>Sabev</td>
                                                    <td>George</td>
                                                    <td>George</td>
                                                    <td>George</td>
                                                    <td>11</td>

                                                </tr>
                                                <tr class="alternate-row">
                                                    <td>2</td>
                                                    <td>Sabev</td>
                                                    <td>George</td>
                                                    <td>George</td>
                                                    <td>George</td>
                                                    <td>12</td>


                                                </tr>
                                                <tr class="alternate-row">
                                                     <td>3</td>
                                                    <td>Sabev</td>
                                                    <td>George</td>
                                                    <td>George</td>
                                                    <td>George</td>
                                                    <td>Ke</td>

                                                </tr>
                                                <tr class="alternate-row">
                                                    <td>4</td>
                                                    <td>Sabev</td>
                                                    <td>George</td>
                                                    <td>George</td>
                                                    <td>George</td>
                                                    <td>13</td>


                                                </tr>
                                                <tr class="alternate-row">
                                                      <td>5</td>
                                                    <td>Sabev</td>
                                                    <td>George</td>
                                                    <td>George</td>
                                                    <td>George</td>
                                                    <td>14</td>


                                                </tr>
                                                <tr class="alternate-row">
                                                     <td>6</td>
                                                    <td>Sabev</td>
                                                    <td>George</td>
                                                    <td>George</td>
                                                    <td>George</td>
                                                    <td>15</td>


                                                </tr>
                                            </table>
                                        <!--  end product-table................................... -->
                                    </form>
                                </div>
                                <!--  end content-table  -->

                                <!--  start actions-box ............................................... -->

                                <!-- end actions-box........... -->

                                <!--  start paging..................................................... -->

                                <!--  end paging................ -->



                            </div>
                            <!--  end content-table-inner ............................................END  -->
                        </td>
                        <td id="tbl-border-right"></td>
                    </tr>
                    <tr class="alternate-row">
                        <th class="sized bottomleft"></th>
                        <td id="tbl-border-bottom">&nbsp;</td>
                        <th class="sized bottomright"></th>
                    </tr>
                </table>
                <div class="clear">&nbsp;</div>
                <div id="page-bottom-date"><h1 class="DateofReport">Date of Report</h1>
                    <h1 class="Stig">KSC University</h1>
                </div>
                <div id="page-bottom-date"><h1 class="DateofReport"><%= new java.util.Date()%></h1>
                </div>
            </div>
            <!--  end content -->
            <div class="clear">&nbsp;</div>
        </div>
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