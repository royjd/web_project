<%-- 
    Document   : signUp
    Created on : Dec 6, 2015, 10:14:00 AM
    Author     : Karl Lauret
--%>
<div class="row">
    <div class="col-sm-12 bg-success">
        <h2>Sign Up</h2>
        <div class="col-sm-12">
            <form method="post" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/singUp.htm">
                <div class="form-group">
                    <input class="form-control" name="username" id="firstName" placeholder="username">
                </div>
                <div class="form-group">
                    <input class="form-control" name="firstName" id="firstName" placeholder="First Name">
                </div>
                <div class="form-group">
                    <input class="form-control" name="lastName" id="lastName" placeholder="Last Name">
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" name="email" id="email" placeholder="E-Mail">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" id="pwd" placeholder="password">
                </div>

                <button type="submit" class="btn btn-success">Sign Up</button>
            </form>
        </div>
    </div>
</div>