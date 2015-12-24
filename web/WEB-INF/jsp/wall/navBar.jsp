<%-- 
    Document   : navBar
    Created on : Dec 10, 2015, 6:22:08 PM
    Author     : Karl Lauret
--%>

<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="${user.username}.htm">${user.username}</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Wall</a></li>
        <li><a href="#">Friends</a></li>
        <li><a href="#">Medias</a></li>
        <li><a href="${pageContext.request.contextPath}/${user.username}/profile.htm">About Me</a></li>  
      </ul>
    </div>
  </div>
</nav>