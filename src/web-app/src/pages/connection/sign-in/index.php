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
    <title>Connexion</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/sign-in.css">
    <script src="/js/preload.js"></script>
    <script src="/js/glassEffect.js" defer></script>
</head>
<body class="flex-body preload">
    <?php
        if(isset($_SESSION['userConnect']) || isset($_SESSION['adminConnect'])) {
            header("Location: /"); 
        }
        if(isset($_SESSION['error'])) {
            echo("<script>setTimeout(() => alert('".$_SESSION['error']."'),500);</script>");
            unset($_SESSION['error']);
        }
        require("/srv/http/endpoint/components/navbar/navbar.php");
        (new Navbar(NavbarEntry::connection))->display();
    ?>

    <div class="login-form-wrapper">
        <div class="bento-box glassy">
            <div class="login-form">
                <h1>Connexion</h1>
                <form action="endpoint.php" method="post" id="sign-in-form">
                    <div class="text-field">
                        <input 
                            type="text"
                            name="email-field"
                            id="email-field"
                            placeholder="Email"
                            form="sign-in-form"
                            value="<?php if(isset($_SESSION["email_back"])) {echo $_SESSION["email_back"]; unset($_SESSION["email_back"]);}?>"
                        >
                    </div>
                    <div class="text-field">
                        <input type="password" name="password-field" id="password-field" placeholder="Mot de passe" form="sign-in-form">
                    </div>
                    <button class="btn filled" type="submit" form="sign-in-form">Connexion</button>
                </form>
                <div class="other-actions">
                    <h2><a href="/connection/sign-up">Créer un compte</a></h2>
                    <h2><a href="/connection/forgot-password">Mot de passe oublié ?</a></h2>
                </div>
            </div>
        </div>
    </div>
</body>
</html>