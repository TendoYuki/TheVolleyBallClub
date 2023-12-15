<div class="user-display">
    <?php 
        if(!(isset($_SESSION['adminConnect']))) {
            header("Location: /"); 
        }
    ?>
    <img src="data:image/png;base64,{user_avatar_url}" alt="" class="user-avatar">
    <div class="user-infos">
        <h1>{user_name}</h1>
        <h2 class="user-id">{user_id}</h2>
    </div>
    <div class="user-actions">

    </div>
</div>