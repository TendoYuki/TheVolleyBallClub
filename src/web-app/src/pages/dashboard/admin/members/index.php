<?php require_once("/srv/http/endpoint/app-config.php") ?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;700;800;900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Archivo:wght@600;700;800;900&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="/public/favicon.ico" type="image/x-icon">
    <title>Contact</title>
    <script defer src="/app.js"></script>
    <script defer src="/components/navbar/dashboard-navbar.js"></script>
</head>
<body>
    <?php
        require("/srv/http/endpoint/components/navbar/navbar.php");
        (new Navbar(NavbarEntry::dashboard))->display();

        require("/srv/http/endpoint/components/navbar/admin_navbar.php");
        (new AdminNavbar(AdminNavbarEntry::members))->display();
    ?>
    <div class="dashboard">
        <h1>Membres</h1>
        <div class="search-bar">
            <h2>Recherche</h2>
            <input type="text">
        </div>
        <div class="result-table">
            
        </div>
    </div>

</body>
</html>