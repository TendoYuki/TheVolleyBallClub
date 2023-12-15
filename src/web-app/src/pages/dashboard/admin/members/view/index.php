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
    <title>Fiche utilisateur</title>
    <script src="/connection/sign-up/sign-up-validation.js" defer></script>
    <script src="/app.js" defer></script>
</head>
<body>
    <?php
        if(!isset($_GET['user'])) {
            header("Location: /dashboard/admin/members"); 
        }
        require("/srv/http/endpoint/components/navbar/navbar.php");
        (new Navbar(NavbarEntry::connection))->display();

        require("/srv/http/endpoint/connection/db_connect.php");

        $stmt = $con->prepare("SELECT * FROM user WHERE idUser=?");
        $stmt->bindValue(1, $_GET['user']);
        $stmt->execute();

        $user = $stmt->fetch();
    ?>
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
                        <select name="gender-field" id="gender-field" required>
                            <option disabled selected value>--</option>
                            <option <?php echo((isset($_SESSION["gender_back"]) && $_SESSION["gender_back"] == 1) ? "selected" : "") ?> title="" descGroup="" value="1">Mr.</option>
                            <option <?php echo((isset($_SESSION["gender_back"]) && $_SESSION["gender_back"] == 0) ? "selected" : "") ?> title="" descGroup="" value="0">Mme.</option>
                            <?php if(isset($_SESSION["gender_back"])) unset($_SESSION["gender_back"]); ?>
                        </select>
                    </div>
                </div>
                <div class="form-section-field">
                    <label for="name-field">Prenom</label>
                    <div class="field">
                        <p><?php echo($user["nameUser"]) ?></p>
                    </div>
                </div>
                <div class="form-section-field">
                    <label for="surname-field">Nom de famille</label>
                    <div class="field">
                        <p><?php echo($user["surnameUser"]) ?></p>
                    </div>
                </div>
                <div class="form-section-field">
                    <label for="birthdate-field">Date de naissance</label>
                    <div class="field">
                        <p><?php echo($user["birthdateUser"]) ?></p>
                    </div>  
                </div>
            </div>
            <h2>Compte</h2>
            <div class="form-section">
                <div class="form-section-field">
                    <label for="group-field">Groupe</label>
                    <div class="field">
                        <select name="group-field" id="group-field" required>
                            <option disabled selected value>--------</option>
                            <?php
                                require("../db_connect.php");
                                $stmt = $con->prepare("SELECT * FROM `group`;");
                                $stmt->execute();
                                $res = $stmt->fetchAll();
                                foreach($res as $cur) {
                                    echo(
                                        "<option ".
                                        ((isset($_SESSION["group_back"]) && $_SESSION["group_back"] == $cur["idGroup"]) ? "selected" : "").
                                        " title=".$cur["descGroup"].
                                        " value=".$cur["idGroup"].">".
                                        $cur["nameGroup"].
                                        "</option>"
                                    );
                                }
                                if(isset($_SESSION["group_back"])) unset($_SESSION["group_back"]);
                            ?>
                        </select>
                    </div>
                </div>
                <div class="form-section-field">
                    <label for="email-field">Email</label>
                    <div class="field">
                        <input
                            type="text"
                            name="email-field"
                            id="email-field"
                            placeholder="Email"
                            value="<?php if(isset($_SESSION["email_back"])) {echo $_SESSION["email_back"]; unset($_SESSION["email_back"]);}?>"
                            required
                        >
                    </div>
                </div>
                <div class="form-section-field">
                    <label for="password-field">Mot de passe</label>
                    <div class="field">
                        <input
                            type="password"
                            name="password-field"
                            id="password-field"
                            placeholder="Mot de passe"
                            required
                        >
                    </div>
                </div>
                <div class="form-section-field">
                    <label for="confirm-password-field">Confirmer mot de passe</label>
                    <div class="field">
                        <input
                            type="password"
                            name="confirm-password-field"
                            id="confirm-password-field"
                            placeholder="Mot de passe"
                            required
                        >
                    </div>
                </div>
            </div>
            <div class="btn-wrapper">
                <button class="btn filled" id="sign-up-btn">S'inscrire</button>
                <button class="btn" id="cancel-btn">Retour</button>
            </div>
        </div>
    </div>
    
</body>
</html>