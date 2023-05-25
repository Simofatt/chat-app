<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="margin-bottom: 40px">
    <div class="container-fluid">
        <a href ="ContactsServlet" class="navbar-brand" href="#" style="margin-left: 30px;">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"  fill="currentColor" class="bi bi-chat-left-quote" viewBox="0 0 16 16">
                <path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H4.414A2 2 0 0 0 3 11.586l-2 2V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12.793a.5.5 0 0 0 .854.353l2.853-2.853A1 1 0 0 1 4.414 12H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
                <path d="M7.066 4.76A1.665 1.665 0 0 0 4 5.668a1.667 1.667 0 0 0 2.561 1.406c-.131.389-.375.804-.777 1.22a.417.417 0 1 0 .6.58c1.486-1.54 1.293-3.214.682-4.112zm4 0A1.665 1.665 0 0 0 8 5.668a1.667 1.667 0 0 0 2.561 1.406c-.131.389-.375.804-.777 1.22a.417.417 0 1 0 .6.58c1.486-1.54 1.293-3.214.682-4.112z"/>
            </svg>
            Chatt-App</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        </div>
        <ul class="navbar-nav ms-auto" >
            <li class="nav-item" style="margin-left: 50px;">

                <a class="nav-link active" aria-current="page" href="ContactsServlet">Accueil</a>
            </li>
            <li class="nav-item" style="margin-left: 20px; margin-right: 20px;">
                <a class="nav-link" href="Profile">Profil</a>
            </li>
            <form method="post" action="NavbarServlet" class="d-flex">
                <input class="form-control me-2" type="search" name="searchInput" placeholder="Saisir l'adresse email" aria-label="Search">
                <button class="btn btn-outline-light" type="submit">Chercher</button>
            </form>
            <li class="nav-item" style="margin-left: 20px;">
                <a class="nav-link" href="SignIn" style="color:red;">Déconnexion</a>
            </li>

        </ul>

    </div>
</nav>
</body>
</html>
