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
    <title>Mot de passe oublié</title>
    <script defer src="/app.js"></script>
    <script src="/connection/forgot-password/forgotPassword.js" defer></script>
</head>
<body class="preload">
    <?php
        if(isset($_SESSION['userConnect']) || isset($_SESSION['adminConnect'])) {
            header("Location: /"); 
        }
        if(isset($_SESSION['error'])) {
            echo("<script>setTimeout(() => alert(`".$_SESSION['error']."`),500);</script>");
            unset($_SESSION['error']);
        }
        require("/srv/http/endpoint/components/navbar/navbar.php");
        (new Navbar(NavbarEntry::connection))->display();
    ?>
    <div class="forgot-password-form">
        <h1>Réinitialiser votre mot de passe</h1>
        <form action="endpoint.php" method="post" id="reset-password-form">
            <div class="text-field">
                <input type="text" name="email-field" id="email-field" placeholder="Email" form="reset-password-form">
            </div>
            <button class="btn filled" type="submit" form="reset-password-form" id="reset-btn">Réinitialiser</button>
            <button class="btn" type="submit" form="reset-password-form" id="cancel-btn">Annuler</button>
        </form>
    </div>
    
</body>
</html>