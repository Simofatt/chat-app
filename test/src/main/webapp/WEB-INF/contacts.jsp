<%@ page import="java.util.ArrayList" %>
<%@ page import="comm.octest.beans.ChatRoom" %>
<%@ page import="comm.octest.db.Pseudo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
         <jsp:include page="navbar.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
     <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Realtime Chat App</title>
  
    <link rel="stylesheet" type="text/css" href="https://localhost:8085/test/src/main/webapp/WEB-INF/ressources/css/contacts.css">
</head>

<%
    String email               =  (String) session.getAttribute("email") ;
    ArrayList<String> contacts =  (ArrayList<String>) request.getAttribute("contacts");
    ChatRoom chatRoom = (ChatRoom) request.getAttribute("chatRoom");
    ArrayList<String> onlineFriends=  (ArrayList<String>) request.getAttribute("onlineFriends");
    
%>

<body>
<div class="container">
    <div class="wrapper">
        <section class="users">
            <header>
                <div class="content">
                    <img src="ImageProfileServlet" alt="">
                    <div class="details">
                        <span><%=email%></span>
                        <p>status</p>
                    </div>
                </div>
            </header>
                <button id="allContacts" class="btn btn-info login-btn mt-2">Tous les contacts</button>
                <button id="onlineContacts" class="btn login-btn mt-2">Contacts En ligne</button>
            <div class="users-list mt-4">
                <div id="allContacts-list">
                    <% for (String user: contacts) { %>
                    <a href="chat?withClientId=<%=user%>"><%=user%></a>
                    <% } %>
                </div>
                <div id="onlineContacts-list">
                    <% for (String user: onlineFriends) { %>
                    <a href="chat?withClientId=<%=user%>"><%=user%></a>
                    <% } %>
                </div>

            </div>
        </section>
    </div>
</div>

<script src="javascript/users.js"></script>
<style>  <%@include file="/WEB-INF/ressources/css/contacts.css"%> </style>

</body>

<script>
    let logoutBtn = document.getElementById("logoutBtn");
    let allContacts = document.getElementById("allContacts");
    let onlineContacts = document.getElementById("onlineContacts");

    allContacts.addEventListener("click", function () {
        console.log("all");
        onlineContacts.style.background='transparent';
        allContacts.style.background='#5bc0de';
        document.getElementById("onlineContacts-list").style.display="none";
        document.getElementById("allContacts-list").style.display="block";
    });
    onlineContacts.addEventListener("click", function () {
        console.log("online");
        allContacts.style.background='transparent';
        onlineContacts.style.background='#5bc0de';
        document.getElementById("onlineContacts-list").style.display="block";
        document.getElementById("allContacts-list").style.display="none";
    })
</script>
</html>