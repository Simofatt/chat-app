<%@ page import="comm.octest.beans.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="comm.octest.db.Pseudo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="navbar.jsp" />
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="/ressources/css/styleProfil.css">
    <title>Profile</title>
</head>
<%
    String email =  (String) session.getAttribute("email") ;
    Pseudo pseudo = new Pseudo(email);
    String username = pseudo.getUsername();
    ArrayList<String> contacts = pseudo.getContacts();
%>
<body>
<div class="container">
    <div class="row gutters-sm">
        <div class="col-md-4 mb-3">
            <div class="card mt-3">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                        <h5 class="d-flex align-items-center mb-3"><i class="material-icons text-info mr-2">Mes contacts</i></h5>
                        <span class="text-secondary">connecté/non connecté</span>
                    </li>
                    <% for (String contact : contacts) { %>
                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                        <h6 class="mb-0"><%=contact%></h6>
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>

        <div class="col-md-8" style="margin-top:20px;" >
            <div class="card mb-3">
                <div class="card-body">
                    <div class="d-flex flex-column align-items-center text-center">
                        <img src="ImageProfileServlet" alt="" class="rounded-circle" width="150">
                        <div class="mt-3">
                            <h4><%=username%></h4>
                            <p class="text-secondary mb-1"><%=email%></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <a class="btn btn-dark " target="__blank" href="editerProfil">Editer Profil</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
