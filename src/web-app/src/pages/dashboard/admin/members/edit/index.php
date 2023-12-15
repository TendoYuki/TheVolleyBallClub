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
        if(!(isset($_SESSION['adminConnect']))) {
            header("Location: /"); 
        }
        if(isset($_SESSION['error'])) {
            echo("<script>setTimeout(() => alert(`".$_SESSION['error']."`),500);</script>");
            unset($_SESSION['error']);
        }
        require("/srv/http/endpoint/components/navbar/navbar.php");
        (new Navbar(NavbarEntry::connection))->display();
    ?>
    <div class="sign-up-form">
        <h1>Créer un compte</h1>
        <form action="endpoint.php" method="post" id="sign-up" enctype="multipart/form-data">
            <div class="form-section">
                <div class="form-section-field">
                    <label for="avatar-field">Photo d'identité</label>
                    <div class="avatar-selector">
                        <label for="avatar-field"></label>
                        <input type="file" name="avatar-field" form="sign-up" id="avatar-field" accept="image/png, image/jpg, image/jpeg, image/webp" required>
                        <span></span>
                    </div>
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
                        <input
                            type="text"
                            name="name-field"
                            id="name-field"
                            placeholder="Prenom"
                            value="<?php if(isset($_SESSION["name_back"])) {echo $_SESSION["name_back"]; unset($_SESSION["name_back"]);}?>"
                            required
                        >
                    </div>
                </div>
                <div class="form-section-field">
                    <label for="surname-field">Nom de famille</label>
                    <div class="field">
                        <input
                            type="text"
                            name="surname-field"
                            id="surname-field"
                            placeholder="Nom"
                            value="<?php if(isset($_SESSION["surname_back"])) {echo $_SESSION["surname_back"]; unset($_SESSION["surname_back"]);}?>"
                            required
                        >
                    </div>
                </div>
                <div class="form-section-field">
                    <label for="birthdate-field">Date de naissance</label>
                    <div class="field">
                        <input
                            type="date"
                            name="birthdate-field"
                            id="birthdate-field"
                            placeholder=""
                            value="<?php if(isset($_SESSION["birthdate_back"])) {echo $_SESSION["birthdate_back"]; unset($_SESSION["birthdate_back"]);}?>"
                            required
                        >
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
                <button class="btn" id="cancel-btn">Annuler</button>
            </div>
        </form>
        <div class="other-actions">
            <h2><a href="/connection/sign-in">Déja un compte?</a></h2>
        </div>
    </div>
    
</body>
</html>