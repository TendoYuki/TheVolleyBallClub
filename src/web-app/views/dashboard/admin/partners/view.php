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
    <link rel="stylesheet" href="/css/dashboard.css">
    <script src="/js/preload.js"></script>
    <script src="/js/glassEffect.js" defer></script>
</head>
<body class="preload">
    <?php
        use Components\Navigation\Navbar\Navbar;
        use Components\Navigation\Navbar\NavbarEntry;
        use Components\Navigation\Dashboard\AdminNavbar;
        use Components\Navigation\Dashboard\AdminNavbarEntry;
        use Models\Partner;

        (new Navbar(NavbarEntry::dashboard))->display();

        $partner = Partner::fetch($_GET['partner']);
    ?>
    <div class="dashboard-wrapper">
        <?php (new AdminNavbar(AdminNavbarEntry::partner))->display(); ?>
        <div class="bento-box glassy entry-display-box">
            <div class="entry-display">
                <div class="entry-infos-wrapper">
                    <div class="inline">
                        <img class="entry-img-display" src="data:image/png;base64,<?php echo(base64_encode($partner->getLogo())); ?>" alt="">
                        <div class="ml">
                            <h1 class="big-title"><?php echo($partner->getName()); ?></h1>
                            <h2 class="big-subtitle"><?php echo($partner->getWebpage()); ?></h2>
                        </div>
                    </div>
                    <div class="btn-wrapper inline">
                        <a class="btn filled" id="edit-btn" href="/dashboard/partners/edit/?partner=<?php echo ($partner->getId());?>">Editer</a>
                        <a class="btn outline" id="cancel-btn" href="/dashboard/partners">Retour</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>