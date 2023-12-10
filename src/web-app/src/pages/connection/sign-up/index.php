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
    <title>Créer un compte</title>
    <script src="/components/navbar/navbar.js" defer></script>
    <script src="/connection/sign-up/sign-up-validation.js" defer></script>
    <script src="/app.js" defer></script>
</head>
<body>
    <?php
        if(isset($_SESSION['userConnect']) || isset($_SESSION['adminConnect'])) {
            header("Location: /"); 
        }
    ?>
    <ul class="navbar">
        <ul class="navbar-menu">
            <li><a href="/">ACCUEIL</a></li>
            <li><a href="/informations">INFORMATIONS</a></li>
            <li><a href="/planning">PLANNING</a></li>
            <li><a href="/contact">CONTACT</a></li>
            <li class="selected"><a href="/connection/sign-in">CONNEXION</a></li>
        </ul>
        <li class="navbar-menu-opener">
            <svg width="420" height="420" viewBox="0 0 420 420" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M63.0022 210L357.002 210M63 333.5L357 333.5M63.0022 87L357.002 87" stroke-width="73" stroke-linecap="round"/>
            </svg>
        </li>
    </ul>
    <div class="sign-up-form">
        <h1>Créer un compte</h1>
        <form action="endpoint.php" method="post" id="sign-up" enctype="multipart/form-data">
            <div class="form-section">
                <div class="form-section-field">
                    <label for="avatar-field">Photo d'identité</label>
                    <div class="avatar-selector">
                        <label for="avatar-field"></label>
                        <input type="file" name="avatar-field" form="sign-up" id="avatar-field" accept="image/png, image/jpg, image/jpeg" required>
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
                            <option title="" descGroup="" value="1">Mr.</option>
                            <option title="" descGroup="" value="0">Mme.</option>
                        </select>
                    </div>
                </div>
                <div class="form-section-field">
                    <label for="name-field">Prenom</label>
                    <div class="field">
                        <input type="text" name="name-field" id="name-field" placeholder="Prenom" required>
                    </div>
                </div>
                <div class="form-section-field">
                    <label for="surname-field">Nom de famille</label>
                    <div class="field">
                        <input type="text" name="surname-field" id="surname-field" placeholder="Nom" required>
                    </div>
                </div>
                <div class="form-section-field">
                    <label for="birthdate-field">Date de naissance</label>
                    <div class="field">
                        <input type="date" name="birthdate-field" id="birthdate-field" placeholder="" required>
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
                                    echo("<option title=".$cur["descGroup"]." value=".$cur["idGroup"].">".$cur["nameGroup"]."</option>");
                                }
                            ?>
                        </select>
                    </div>
                </div>
                <div class="form-section-field">
                    <label for="email-field">Email</label>
                    <div class="field">
                        <input type="text" name="email-field" id="email-field" placeholder="Email" required>
                    </div>
                </div>
                <div class="form-section-field">
                    <label for="password-field">Mot de passe</label>
                    <div class="field">
                        <input type="password" name="password-field" id="password-field" placeholder="Mot de passe" required>
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