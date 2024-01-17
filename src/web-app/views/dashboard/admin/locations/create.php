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
    <title>Nouveau gymnase</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/dashboard.css">
    <script src="/js/glassEffect.js" defer></script>
    <script src="/js/preload.js"></script>
    <script defer src="/js/editControls.js"></script>
    <script defer src="/js/passwordValidation.js"></script>
    <script defer src="/js/avatarField.js"></script>
</head>
<body class="preload">
    <?php

        use Components\Navigation\Navbar\Navbar;
        use Components\Navigation\Navbar\NavbarEntry;
        use Components\Navigation\Dashboard\AdminNavbar;
        use Components\Navigation\Dashboard\AdminNavbarEntry;

        (new Navbar(NavbarEntry::dashboard))->display();

        if(isset($_SESSION['error'])) {
            echo("<script>setTimeout(() => alert(`".$_SESSION['error']."`),500);</script>");
            unset($_SESSION['error']);
        }
    ?>
    <div class="dashboard-wrapper">
        <?php (new AdminNavbar(AdminNavbarEntry::location))->display(); ?>
        <div class="bento-box glassy entry-display-box">
            <div class="entry-display">
                <div class="entry-infos-wrapper">
                    <form action="/dashboard/locations/create/submit" method="post" id="create" enctype="multipart/form-data">
                        <input type="hidden" name="action" value="create">
                        <input type="hidden" name="redirect-success" value="/dashboard/locations/">
                        <input type="hidden" name="redirect-error" value="/dashboard/locations/create/">
                        <h1>Nouveau gymnase</h1>
                        <h2>Nom du gymnase</h2>
                        <div class="form-section">
                            <div class="form-section-field">
                                <div class="field">
                                    <input name="name-field" id="name-field" type="text" value="" placeholder="Nom du gymnase">
                                </div>
                            </div>
                        </div>
                        <h2>Addresse du gymnase</h2>
                        <div class="form-section">
                            <div class="form-section-field">
                                <div class="field">
                                    <input name="address-field" id="link-field" type="text" value="" placeholder="Addresse">
                                </div>
                            </div>
                            <div class="form-section-field">
                                <div class="field">
                                    <input name="post-code-field" id="link-field" type="text" value="" placeholder="Code postal">
                                </div>
                            </div>
                            <div class="form-section-field">
                                <div class="field">
                                    <input name="city-field" id="link-field" type="text" value="" placeholder="Ville">
                                </div>
                            </div>
                        </div>
                        <div class="btn-wrapper inline">
                            <button class="btn filled" id="create-btn" type="submit" form="create">Enregistrer</button>
                            <a class="btn outline" id="cancel-btn" href="/dashboard/locations">Annuler</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
</body>
</html>