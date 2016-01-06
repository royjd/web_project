<html lang="fr">

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/library/bootstrap/css/bootstrap.css"/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/library/bootstrap/js/bootstrap.js"></script>

    <head>
        <title>Fanfare</title>
    </head>
    <body>
        <div class="bg-primary">
            <%@ include file="index/header.jsp" %>
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <%@ include file="index/info.jsp" %>
                    </div>
                    <div class="col-sm-6">
                        <%@ include file="index/signUp.jsp" %>
                    </div>

                </div>
            </div>
            <%@ include file="index/footer.jsp" %>
        </div>
    </body>
</html>