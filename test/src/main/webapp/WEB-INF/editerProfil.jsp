<%@ page import="java.util.ArrayList" %>
<%@ page import="comm.octest.db.Pseudo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="navbar.jsp" />
<html>
<head>
    <link rel="stylesheet" href="/ressources/css/styleEditer.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Editer</title>
</head>
<%
    String email =  (String) session.getAttribute("email") ;
    Pseudo pseudo = new Pseudo(email);
    String username = pseudo.getUsername();
    ArrayList<String> contacts = pseudo.getContacts();
%>
<body>
<div class="container">
    <div class="main-body">
        <div class="row">
            <div class="col-lg-4">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="ImageProfileServlet" alt="" class="rounded-circle p-1 bg-primary" width="110">
                            <div class="mt-3">
                                <h4><%=username%></h4>
                                <p class="text-secondary mb-1"><%=email%></p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-body">
                        <form method="post" name="change_form" action="editerProfil">
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Nom d'Utilisateur</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" name="username" class="form-control" placeholder="<%=username%>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="submit" name="save" class="btn btn-primary px-4" value="Enregistrer les modifications">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="d-flex align-items-center mb-3">Gestion des contacts</h5>
                                <form method="post" name="remove_form" action="editerProfil">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">Contacts</th>
                                            <th scope="col">Supprimer de la liste des contacts</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <% for (String contact : contacts) { %>
                                        <tr>
                                            <td><%=contact%></td>
                                            <td><button type="submit" name="remove" class="btn btn-danger" value="<%=contact%>">Supprimer</button></td>
                                        </tr>
                                        <% } %>
                                        </tbody>
                                    </table>

                                </form>
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
