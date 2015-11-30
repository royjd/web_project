<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <p>Hello! This is the default welcome page for a Spring Web MVC project.</p>
        <p><i>To display a different welcome page for this project, modify</i>
            <tt>index.jsp</tt> <i>, or create your own welcome page then change
                the redirection in</i> <tt>redirect.jsp</tt> <i>to point to the new
                welcome page and also update the welcome-file setting in</i>
            <tt>web.xml</tt>.</p>
        <form method="post" action="addUser.htm">
            <fieldset>
                Username* : <input type="text" name="username" /><br />
                <input type="submit" name="action" value="Add User"/>
            </fieldset>
        </form>
        <h2>${username}</h2>
        <h2>${sessionUsername}</h2>
        <form method="post" action="sendMessage.htm">
            <fieldset>
                Subject : <input type="text" name="subject" /><br />
                Message : <input type="text" name="message" /><br />
                Target : <input type="text" name="target" /><br />
                <input type="submit" name="action" value="Add User"/>
            </fieldset>
        </form>

        <form id="loginForm" method="post" action="login.htm" modelAttribute="loginBean">
            <fieldset>

                <label path="username">Enter your user-name</label>

                <input id="username" name="username" path="username" /><br>

                <label path="username">Please enter your password</label>

                <password id="password" name="password" path="password" /><br>

                <input type="submit" value="Submit" />
            </fieldset>
        </form>
        <a href="logout.htm" > Logout</a>
    </body>
</html>
