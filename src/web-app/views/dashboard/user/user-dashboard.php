<?php include_once("/srv/http/endpoint/config/config.php"); ?>
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
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/dashboard.css">
    <script src="/js/glassEffect.js" defer></script>
    <script src="/js/preload.js"></script>
    <script defer src="/js/pageControls.js"></script>
    <script defer src="/js/searchControls.js"></script>
    <script defer src="/js/userControls.js"></script>
</head>
<body class="preload">
    <?php
        include_once("/srv/http/endpoint/components/navbar/navbar.php");
        (new Navbar(NavbarEntry::dashboard))->display();

        use Components\Navbar\UserNavbar;
        use Components\Navbar\UserNavbarEntry;
        
        (new UserNavbar(UserNavbarEntry::profile))->display();
    ?>
    <div class="dashboard-wrapper">
        <div class="bento-box glassy dashboard-box">
            <div class="dashboard">
                <?php
                    use Models\User;
                    $user = new User($_SESSION['userConnect']);
                    echo $user->getName().' '.$user->getSurname();
                ?>
            </div>
        </div>
    </div>
</body>
</html>