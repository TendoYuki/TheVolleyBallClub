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
    <title>Planning</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/planning.css">
    <script src="/js/preload.js"></script>
    <script src="/js/glassEffect.js" defer></script>
    <script src="/js/inlineScroller.js" defer></script>
</head>
<body class="preload">
    <?php
        use Components\Navigation\Navbar\Navbar;
        use Components\Navigation\Navbar\NavbarEntry;
        
        (new Navbar(NavbarEntry::planning))->display();
    ?>
    <div class="planning-page">
        <div class="planning-section left">
            <span class="top-bar"></span>
            <h1>Prochaines Compétitions</h1>
            <div class="scroller-wrapper">
                <span class="left-control">
                    <?php echo get_public_file("symbols/arrow-left-symbol.svg"); ?>
                </span>
                <div class="scroller-fade fade-right">
                    <ul class="inline-scroller">
                        <li class="bento-box glassy">
                            <h1>10 Janvier</h1>
                            <h2>18h00 - 20h00</h2>
                            <a href="/planning/view/?match_id=1" class="btn outline">En savoir plus</a>
                        </li>
                        <li class="bento-box glassy">
                            <h1>10 Janvier</h1>
                            <h2>18h00 - 20h00</h2>
                            <a href="" class="btn outline">En savoir plus</a>
                        </li>
                        <li class="bento-box glassy">2</li>
                        <li class="bento-box glassy">3</li>
                        <li class="bento-box glassy">4</li>
                        <li class="bento-box glassy">5</li>
                        <li class="bento-box glassy">6</li>
                    </ul>
                </div>
                <span class="right-control">
                    <?php echo get_public_file("symbols/arrow-right-symbol.svg"); ?>
                </span>
            </div>
        </div>
        <div class="planning-section right">
            <span class="top-bar"></span>
            <h1>Prochains Entrainements</h1>
            <div class="scroller-wrapper rtl">
                <span class="left-control">
                    <?php echo get_public_file("symbols/arrow-left-symbol.svg"); ?>
                </span>
                <div class="scroller-fade fade-right">
                    <ul class="inline-scroller">
                        <li class="bento-box glassy">1</li>
                        <li class="bento-box glassy">2</li>
                        <li class="bento-box glassy">3</li>
                        <li class="bento-box glassy">4</li>
                        <li class="bento-box glassy">5</li>
                        <li class="bento-box glassy">6</li>
                    </ul>
                </div>
                <span class="right-control">
                    <?php echo get_public_file("symbols/arrow-right-symbol.svg"); ?>
                </span>
            </div>
        </div>
    </div>
</body>
</html>