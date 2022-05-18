<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['actor_id']) && isset($_POST['movie_id']) && isset($_POST['played_role'])) {
    if ($db->dbConnect()) {
        if ($db->addActInMovie("played_role", $_POST['actor_id'], $_POST['movie_id'], $_POST['played_role'])) {
            echo "Played role added Success";
        } else echo "Played role add Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>