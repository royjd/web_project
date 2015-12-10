

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container bg-danger">

        <div class="row">
            <div class="col-sm-6">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="home.htm" class="glyphicon glyphicon-home"></a></li>
                    <li><a href="username.htm" class="">WALL</a></li>
                    <li><a href="message.htm" class="glyphicon glyphicon-envelope"></a></li>
                    <li><a href="notification.htm" class="glyphicon glyphicon-bell"></a></li>
                </ul>
            </div>
            <div class="col-sm-6">
                <ul class="nav navbar-nav pull-right">
                    <a class="navbar-brand pull-right" href="home.htm">FanFare</a>
                    <li class="dropdown pull-right">
                        <a class="dropdown-toggle glyphicon glyphicon-user" data-toggle="dropdown" href="#"><span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li> <a href="${user.email}.htm"> ${user.email} </a></li>
                            <li> <a href="logout.htm"> Logout </a></li>
                        </ul>
                    </li>
                    <form method="post" class="navbar-form pull-right" role="form" action="search.htm">

                        <input type="text" name="searchParam" class="form-control" id="friendSearch" placeholder="Search...">

                        <button type="submit" class="btn btn-success">Go</button>
                    </form>
                </ul>
            </div>
        </div>
    </div>
</nav>
