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
    <title>Tableau de bord</title>
    <link rel="stylesheet" href="/css/styles.css">
    <script src="/js/preload.js"></script>
    <script defer src="/components/navbar/dashboard-navbar.js"></script>
</head>
<body class="preload">
    <?php
        if(!(isset($_SESSION['adminConnect']))) {
            header("Location: /"); 
        }
        require("/srv/http/endpoint/components/navbar/navbar.php");
        (new Navbar(NavbarEntry::dashboard))->display();
    ?>
    <div class="dashboard-wrapper">
        <?php    
            require("/srv/http/endpoint/components/navbar/admin_navbar.php");
            (new AdminNavbar(AdminNavbarEntry::trainings))->display();
        ?>
        <div class="dashboard">
            <h1></h1>
        </div>
    </div>

</body>
</html>