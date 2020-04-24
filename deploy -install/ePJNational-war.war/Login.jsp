<!DOCTYPE html>
<!-- saved from url=(0062)http://voky.com.ua/showcase/sky-forms/examples/demo-login.html -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sky Forms</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <script type="text/javascript" src="CSS-Image-JQuery/Page/validate/validate.js"></script>
        <link rel="stylesheet" href="CSS-Image-JQuery/Login/Css/demo.css">
        <link rel="stylesheet" href="CSS-Image-JQuery/Login/Css/sky-forms.css">
        <link href="CSS-Image-JQuery/Login/font-awesome/css/font-awesome.css" rel="stylesheet">

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
    </head>
    <body class="bg-cyan">
        <div class="body body-s">

            <form action="LoginServlet" class="sky-form" method="post">
                <header><img id="logo" src="CSS-Image-JQuery/Login/Images/Logo.png" align="middle"/></header>

                <fieldset>
                    <section>
                        <div class="row">
                            <label class="label col col-4">E-mail</label>
                            <div class="col col-8">
                                <label class="input">
                                    <i class="icon-append fa fa-envelope-o"></i>
                                    <input type="email" placeholder="Email ID" oninvalid="InvalidEmail(this);" oninput="InvalidEmail(this);" required="true" name="txtEmail">
                                </label>
                            </div>
                        </div>
                    </section>

                    <section>
                        <div class="row">
                            <label class="label col col-4">Password</label>
                            <div class="col col-8">
                                <label class="input">
                                    <i class="icon-append icon-lock"></i>
                                    <input type="password" placeholder="Password" oninvalid="InvalidLoginPass(this);" oninput="InvalidLoginPass(this);" pattern=".{6,50}" required="true" name="txtPass">
                                </label>
                            </div>
                        </div>
                    </section>

                </fieldset>
                <footer>
                    <button type="submit" name="action" value="LogIn" class="button">Log in</button>
                    <a href="Registration.jsp" class="button button-secondary">Register</a>
                </footer>
            </form>
        </div>

    </body></html>