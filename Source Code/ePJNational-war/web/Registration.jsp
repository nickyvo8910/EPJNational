<!DOCTYPE html>
<!-- saved from url=(0060)http://voky.com.ua/showcase/sky-forms/examples/demo-reg.html -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sky Forms</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">

        <link rel="stylesheet" href="CSS-Image-JQuery/Login/Css/demo.css">
        <link rel="stylesheet" href="CSS-Image-JQuery/Login/Css/sky-forms.css">
        <link href="CSS-Image-JQuery/Login/font-awesome/css/font-awesome.css" rel="stylesheet">
        <script type="text/javascript" src="CSS-Image-JQuery/Page/validate/validate.js"></script>
        <script type="text/javascript" src="CSS-Image-JQuery/DatePicker/JavaScript/jquery-1.5.1.min.js"></script>
        <script type="text/javascript" src="CSS-Image-JQuery/DatePicker/JavaScript/jquery-ui.min.js"></script>
        <link href="CSS-Image-JQuery/DatePicker/Css/jquery-ui.css" rel="stylesheet" type="text/css" />
        <script>
            $(document).ready(function () {
                $("#datepicker").datepicker();
            });
        </script>
        <!--[if lt IE 9]>
			<link rel="stylesheet" href="css/sky-forms-ie8.css">
		<![endif]-->

        <!--[if lt IE 10]>
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
			<script src="js/jquery.placeholder.min.js"></script>
		<![endif]-->
        <!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<script src="js/sky-forms-ie8.js"></script>
		<![endif]-->
        <%
                    String abc = null;
                    if (abc == null) {
                    } else {
        %>
        <script>alert("<%=abc%>")</script>
        <% }%>
    </head>

    <body class="bg-cyan">
        <div class="body body-s">

            <form action="RegistrationServlet" method="post" class="sky-form">
                <header><img id="logo1" src="CSS-Image-JQuery/Login/Images/Logo1.png" align="middle"/></header>

                <fieldset>
                    <section>
                        <label class="input">
                            <i class="icon-append fa fa-envelope-o"></i>
                            <input type="email" placeholder="Email ID" oninvalid="InvalidEmail(this);" oninput="InvalidEmail(this);" required="true" name="txtMail">
                        </label>
                    </section>

                    <section>
                        <label class="input">
                            <i class="icon-append icon-lock"></i>
                            <input type="password" placeholder="Password" id="newpass" pattern="^[a-zA-Z0-9][\w\s]{5,50}$" oninvalid="InvalidPass(this);" oninput="InvalidPass(this);" required="true" name="txtPass">
                        </label>
                    </section>

                    <section>
                        <label class="input">
                            <i class="icon-append icon-lock"></i>
                            <input type="password" placeholder=" Confirm Password" id="confirmpass" patttern=".{6,50}" oninvalid="InvalidConfirmPass(this);" oninput="InvalidConfirmPass(this);" required="true">
                        </label>
                    </section>

                </fieldset>

                <fieldset>

                    <section>
                        <label class="input">
                            <i class="icon-append icon-user"></i>
                            <input type="text" placeholder="Fullname" pattern=".{0,50}" oninvalid="InvalidUserFullName(this);" oninput="InvalidName(this);" required="true" name="txtName" >
                        </label>
                    </section>

                    <section>
                        <label class="input">
                            <i class="icon-append fa fa-calendar"></i>
                            <input type="text" placeholder="Birthday" id="datepicker" value="01/01/1990" readonly="true" name="txtDOB">
                        </label>
                    </section>

                    <div class="row">
                        <section class="col col-6">
                            <label class="input">
                                <i class="icon-append fa fa-home"></i>
                                <input type="text" placeholder="Address" oninvalid="InvalidUserAddress(this);" oninput="InvalidUserAddress(this);" pattern=".{0,500}"  required="true" name="txtAddr">
                            </label>
                        </section>
                        <section class="col col-6">
                            <label class="input">
                                <i class="icon-append fa fa-phone"></i>
                                <input type=tel' pattern="\d{8,11}" oninvalid="InvalidPhone(this);" oninput="InvalidPhone(this);" placeholder="Phone" required="true" name="txtPhone"/>
                            </label>
                        </section>
                    </div>

                    <section>
                        <label class="select">
                            <select name="cmbGender">
                                <option value="0">Male</option>
                                <option value="1">Female</option>
                            </select>
                            <i></i>
                        </label>
                    </section>

                    <section>
                        <label class="checkbox"><input type="checkbox" name="chkSubscribe"><i></i>I agree to Subscribe</label>
                    </section>
                </fieldset>
                <footer>
                    <button type="submit" class="button" name="action">Submit</button>
                    <a href="Home.jsp" class="button button-secondary">Cancel</a>
                </footer>
            </form>

        </div>

    </body></html>