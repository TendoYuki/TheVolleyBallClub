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
    <title>Volleyball club</title>
</head>
<body>
    <ul class="navbar">
        <li><a>INFORMATIONS</a></li>
        <li><a>PLANNING</a></li>
        <li><a>ESPACE ADHERENT</a></li>
        <li class="selected"><a>CONTACT</a></li>
    </ul>
    <div class="text-field">
        <input type="text" name="" id="" placeholder="Email">
    </div>
    <div class="text-field">
        <input type="text" name="" id="" placeholder="Password">
    </div>
    <button class="btn filled">Click me</button>

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

</body>
</html>