<%@ page import="comm.octest.beans.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="comm.octest.db.Pseudo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="navbar.jsp" />

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Visiter Profile</title>
</head>
<%
    User user = (User) request.getAttribute("user");
    String email =  (String) session.getAttribute("email") ;
    Pseudo pseudo = new Pseudo(email);
    ArrayList<String> contacts = pseudo.getContacts();
    boolean isFriend = false;
    for (String contact: contacts) {
        if (contact.equals(user.getEmail())) {
            isFriend = true;
            break;
        }
    }	
%>
<body>

<div class="carte">
    <center><img src="ImageProfileServlet"  style="width:50%;margin-top: 20px;"></center>
    <h3 style="margin-top:30px;"><%=user.getUsername()%></h3>
    <p class="title"><%=user.getEmail()%></p>
    <div class="mt-3">
        <% if (isFriend == false) { %>
        <form method="post" action="AddContactServlet">
            <button name="addContact" class="btn btn-dark" value="<%=user.getEmail()%>">Ajouter</button>
        </form>
        <% } else { %>
            <button  class="btn btn-success">Ami</button>
        <% } %>
        <a href="chat?withClientId=<%=user.getEmail()%>"><button class="btn btn-outline-dark">Envoyer un message</button></a>
    </div>
</div>
</body>
</html>
<style>

    .carte {
        margin-top :100px !important;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        max-width: 300px;
        margin: auto;
        text-align: center;
    }

    .carte.title {
        color: grey;
        font-size: 18px;
    }
    .carte button{
        margin-bottom: 40px;
    }

    .carte a {
        text-decoration: none;
        font-size: 22px;
        color: black;
    }

    .carte button:hover, a:hover {
        opacity: 0.7;
    }
</style>