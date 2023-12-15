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
    <title>Tableau de bord</title>
    <script defer src="/app.js"></script>
    <script defer src="pageControls.js"></script>
    <script defer src="searchControls.js"></script>
    <script defer src="userControls.js"></script>
</head>
<body>
    <?php
        if(!(isset($_SESSION['adminConnect']))) {
            header("Location: /"); 
        }
        require("/srv/http/endpoint/components/navbar/navbar.php");
        (new Navbar(NavbarEntry::dashboard))->display();

        require("/srv/http/endpoint/components/navbar/admin_navbar.php");
        (new AdminNavbar(AdminNavbarEntry::members))->display();
    ?>
    <div class="dashboard">
        <h1>Membres</h1>
        <div class="search-bar">
            <h2>Recherche</h2>
            <div class="search-inline">
                <input id="search-field" type="text" placeholder="Prénom, Nom" value="<?php echo isset($_GET["search"]) ? $_GET["search"] : "" ?>">
                <button id="search-btn" class="btn">Rechercher</button>
            </div>
        </div>
        <div class="result-table">
            <?php
                require("/srv/http/endpoint/connection/db_connect.php");
                $DISPLAY_COUNT_PER_PAGE = 5;

                // Gets the search query if exists
                $search = "%";
                if(isset($_GET["search"])) {
                    $search = $search.$_GET["search"];
                }
                $search = $search."%";
                
                // Gets the total number of entries in the table
                $stmtCount = $con->prepare("SELECT COUNT(*) AS CT FROM user WHERE nameUser LIKE ? OR surnameUser LIKE ? OR idUser LIKE ?;");
                $stmtCount->bindValue(1, $search);
                $stmtCount->bindValue(2, $search);
                $stmtCount->bindValue(3, $search);
                $stmtCount->execute();
                $rowCt = $stmtCount->fetch()["CT"];
                $TOTAL_PAGE_CT = ceil($rowCt/$DISPLAY_COUNT_PER_PAGE);


                
                // Gets the current page
                $stmt = $con->prepare("SELECT * FROM user WHERE nameUser LIKE ? OR surnameUser LIKE ? OR idUser LIKE ? LIMIT $DISPLAY_COUNT_PER_PAGE OFFSET ? ;");
                $page = 1;
                if(isset($_GET["page"])) {
                    $page = $_GET["page"];
                }

                // Redirects to correct URL if requested page is out of bounds
                if($page<1) {
                    echo "<script>window.location.href='/dashboard/admin/members/?page=1';</script>";
                }
                if($page>$TOTAL_PAGE_CT) {
                    echo "<script>window.location.href='/dashboard/admin/members/?page=$TOTAL_PAGE_CT';</script>";
                }
                
                $offset = (($page-1)*$DISPLAY_COUNT_PER_PAGE);
                $stmt->bindValue(1, $search);
                $stmt->bindValue(2, $search);
                $stmt->bindValue(3, $search);
                $stmt->bindValue(4, (int)$offset, PDO::PARAM_INT);
                try{
                    $stmt->execute();
                } catch(Exception $e) {
                    echo $e;
                }

                // Displays the current page
                foreach($stmt->fetchAll() as $res) {
                    $template = file_get_contents("user_display_template.php");
                    $template = str_replace("{user_avatar_url}", base64_encode($res["imageUser"]), $template);
                    $template = str_replace("{user_name}", $res["nameUser"].' '.$res["surnameUser"], $template);
                    $template = str_replace("{user_id}", $res["idUser"], $template);
                    echo($template);
                }
            ?>
            <div class="page-control">
                <?php if($page > 1):?>
                    <a class="prev">Precedent</a>
                <?php endif?>
                <span>...</span>
                <?php if($page < $TOTAL_PAGE_CT):?>
                    <a class="next">Suivant</a>
                <?php endif?>
            </div>
        </div>
    </div>

</body>
</html>