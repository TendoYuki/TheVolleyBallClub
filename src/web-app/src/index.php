<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;700;800;900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Archivo:wght@600;700;800;900&display=swap" rel="stylesheet">
    <script src="./components/carrousel/carrousel.js" defer></script>
    <script src="./components/navbar/navbar.js" defer></script>
    <title>Volleyball club</title>
</head>
<body>
    <ul class="navbar">
        <ul class="navbar-menu active">
            <li><a>ACCUEIL</a></li>
            <li><a>INFORMATIONS</a></li>
            <li><a>PLANNING</a></li>
            <li><a>ESPACE ADHERENT</a></li>
            <li class="selected"><a>CONTACT</a></li>
        </ul>
        <li class="navbar-menu-opener">
            <svg width="420" height="420" viewBox="0 0 420 420" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M63.0022 210L357.002 210M63 333.5L357 333.5M63.0022 87L357.002 87" stroke-width="73" stroke-linecap="round"/>
            </svg>
        </li>
    </ul>

    <div class="carrousel-wrapper">
        <div class="controls">
            <div class="prev">
                <span></span>
            </div>
            <div class="selectors">
                <span></span>
                <span></span>
                <span></span>
            </div>
            <div class="next">
                <span></span>
            </div>
        </div>
        <div class="images-container">
            <img src="public/2.jpg" alt="">
            <img src="public/1.jpg" alt="">
            <img src="public/3.jpg" alt="">
        </div>
    </div>
    <?php
    // <div class="text-field">
    //     <input type="text" name="" id="" placeholder="Email">
    // </div>
    // <div class="text-field">
    //     <input type="text" name="" id="" placeholder="Password">
    // </div>
    // <button class="btn filled">Click me</button>
    ?>
</body>
</html>