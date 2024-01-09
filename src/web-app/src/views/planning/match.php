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
        include_once("/srv/http/endpoint/components/navbar/navbar.php");
        (new Navbar(NavbarEntry::planning))->display();

        include_once(MODELS.'database.php');

        $stmt = $con->prepare("SELECT idCompetition,
            startDateTimeCompetition,
            endDateTimeCompetition,
            Result_idResult,
            Location_idLocation,
            idResult,
            victoriesCount,
            defeatCount,
            ranking,
            totalClubsCount,
            idLocation,
            cityLocation,
            postCodeLocation,
            addressLocation,
            nameLocation,
            maxParticipantCompetition,
            count(User_idUser) AS `participant_ct`
            FROM competition
            LEFT JOIN `result`
            ON Result_idResult=idResult
            JOIN `location` ON Location_idLocation=idLocation
            LEFT JOIN `user_has_competition` ON `user_has_competition`.`Competition_idCompetition`=idCompetition
            WHERE idCompetition=?
        ");
        $stmt->bindValue(1, $_GET['match_id']);
        $stmt->execute();

        $competition = $stmt->fetch();

        $formatter = new IntlDateFormatter(
            'fr_FR',
            IntlDateFormatter::LONG,
            IntlDateFormatter::NONE,
            'Europe/Paris'
        );

        $s_time = strtotime($competition["startDateTimeCompetition"]);

        $e_time = strtotime($competition["endDateTimeCompetition"]);

        // Formats the competition date to dd/MM/yyyy
        $competition_date_str = $formatter->format($s_time);

        $competition_s_date_str = date("H:m", $s_time);
        $competition_e_date_str = date("H:m", $e_time);

        $remaining_places = $competition["maxParticipantCompetition"]-$competition["participant_ct"];
    ?>

    <div class="planning-page">
        <div class="planning-section left">
            <span class="top-bar"></span>
            <h1>Match du <?php echo $competition_date_str?></h1>
            <div class="address">
                <?php echo get_public_file("symbols/map-point-symbol.svg")?>
                <div>
                    <h2><?php echo $competition["nameLocation"]?></h2>
                    <h2><?php echo $competition["addressLocation"]?></h2>
                    <h2><?php echo $competition["postCodeLocation"].' '.$competition["cityLocation"]?></h2>
                </div>
            </div>
            <div class="time">
                <?php echo get_public_file("symbols/clock-symbol.svg")?>
                <h2><?php echo $competition_s_date_str.' - '.$competition_e_date_str?></h2>
            </div>
            <div class="participate">
                <h2><?php echo $competition["participant_ct"]?> Participants - <?php echo (($remaining_places <= 0) ? "Aucune" : $remaining_places) ?> Place(s) restantes </h2>
                <?php if($remaining_places>0) : ?>
                    <a href="" class="btn filled">Je participe</a>
                <?php endif; ?>
            </div>
        </div>
    </div>
</body>
</html>