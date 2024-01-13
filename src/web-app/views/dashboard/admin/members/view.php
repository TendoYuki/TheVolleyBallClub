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
    <title>Fiche utilisateur</title>
    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/preload.js"></script>
    <script src="/js/glassEffect.js" defer></script>
</head>
<body class="preload">
    <?php
        use Components\Navigation\Navbar\Navbar;
        use Components\Navigation\Navbar\NavbarEntry;
        use Database\DatabaseConnection;
        
        (new Navbar(NavbarEntry::dashboard))->display();

        if(!isset($_GET['user'])) {
            header("Location: /dashboard/members"); 
        }

        $connection = new DatabaseConnection();

        $stmt = $connection->getConnection()->prepare("SELECT * FROM user JOIN `group` ON Group_idGroup=idGroup WHERE idUser=?");
        $stmt->bindValue(1, $_GET['user']);
        $stmt->execute();

        $user = $stmt->fetch();
    ?>
    <div class="bento-box glassy entry-display-box">
        <div class="entry-display">
            <div class="entry-infos-wrapper">
                <div class="inline">
                    <img class="entry-img-display" src="data:image/png;base64,<?php echo(base64_encode($user["imageUser"])); ?>" alt="">
                    <div class="ml">
                        <h1 class="big-title"><?php echo($user["nameUser"]." ".$user["surnameUser"]); ?></h1>
                        <h2 class="big-subtitle"><?php echo($user["idUser"]); ?></h2>
                    </div>
                </div>
                <h2>Identité</h2>
                <div class="form-section">
                    <div class="form-section-field">
                        <label for="gender-field">Civilité</label>
                        <div class="field">
                            <input type="text" value="<?php echo($user["gender"] == 0 ? "Mme." : "Mr.") ?>" disabled>
                        </div>
                    </div>
                    <div class="form-section-field">
                        <label for="name-field">Prenom</label>
                        <div class="field">
                            <input type="text" value="<?php echo($user["nameUser"]) ?>" disabled>
                        </div>
                    </div>
                    <div class="form-section-field">
                        <label for="surname-field">Nom de famille</label>
                        <div class="field">
                            <input type="text" value="<?php echo($user["surnameUser"]) ?>" disabled>
                        </div>
                    </div>
                    <div class="form-section-field">
                        <label for="birthdate-field">Date de naissance</label>
                        <div class="field">
                            <input type="text" value="<?php echo($user["birthdateUser"]) ?>" disabled>
                        </div>  
                    </div>
                </div>
                <h2>Compte</h2>
                <div class="form-section">
                    <div class="form-section-field">
                        <label for="group-field">Groupe</label>
                        <div class="field">
                            <input type="text" value="<?php echo($user["nameGroup"]) ?>" disabled>
                        </div>
                    </div>
                    <div class="form-section-field">
                        <label for="email-field">Email</label>
                        <div class="field">
                            <input type="text" value="<?php echo($user["emailUser"]) ?>" disabled>
                        </div>
                    </div>
                </div>
                <div class="btn-wrapper">
                    <a class="btn" id="cancel-btn" href="/dashboard/members">Retour</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>