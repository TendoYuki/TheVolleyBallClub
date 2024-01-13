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
    <title>Créer un compte</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/sign-up.css">
    <script src="/js/preload.js"></script>
    <script src="/js/glassEffect.js" defer></script>
    <script src="/js/signUpValidation.js" defer></script>
</head>
<body class="flex-body preload">
    <?php
        if(isset($_SESSION['error'])) {
            echo("<script>setTimeout(() => alert(`".$_SESSION['error']."`),500);</script>");
            unset($_SESSION['error']);
        }
        include_once("/srv/http/endpoint/components/navbar/navbar.php");
        (new Navbar(NavbarEntry::connection))->display();

        // Checks for form data sent back from controller
        $has_form_data = isset($_SESSION['form-data']);
        $form_data = $has_form_data ? $_SESSION['form-data'] : [];
    ?>
    <div class="sign-up-form-wrapper">
        <div class="bento-box glassy">
            <div class="sign-up-form">
                <h1>Créer un compte</h1>
                <form action="/sign-up/submit" method="post" id="sign-up" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="create">
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
                                    <option <?php echo(($has_form_data && $form_data["gender-field"] == 1) ? "selected" : "") ?> title="" descGroup="" value="1">Mr.</option>
                                    <option <?php echo(($has_form_data && $form_data["gender-field"] == 0) ? "selected" : "") ?> title="" descGroup="" value="0">Mme.</option>
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
                                    value="<?php if($has_form_data) echo htmlspecialchars($form_data["name-field"])?>"
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
                                    value="<?php if($has_form_data) echo htmlspecialchars($form_data["surname-field"])?>"
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
                                    value="<?php if($has_form_data) echo htmlspecialchars($form_data["birthdate-field"])?>"
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
                                        use Database\DatabaseConnection;

                                        $connection = new DatabaseConnection();
                                        $stmt = $connection->getConnection()->prepare("SELECT * FROM `group`;");
                                        $stmt->execute();
                                        $res = $stmt->fetchAll();
                                        foreach($res as $cur) {
                                            echo(
                                                "<option ".
                                                (($has_form_data && $form_data["group-field"] == $cur["idGroup"]) ? "selected" : "").
                                                " title=".$cur["descGroup"].
                                                " value=".$cur["idGroup"].">".
                                                $cur["nameGroup"].
                                                "</option>"
                                            );
                                        }
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
                                    value="<?php if($has_form_data) echo htmlspecialchars($form_data["email-field"])?>"
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
                                    value="<?php if($has_form_data) echo htmlspecialchars($form_data["password-field"])?>"
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
                                    value="<?php if($has_form_data) echo htmlspecialchars($form_data["password-field"])?>"
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
                    <h2><a href="/sign-in">Déja un compte?</a></h2>
                </div>
            </div>
        </div>
    </div>
    
</body>
</html>
<?php
    // Removes the form data from the session if it existed
    if ($has_form_data) unset($_SESSION['form-data']);
?>