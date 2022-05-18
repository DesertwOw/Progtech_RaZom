<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['dir_id']) && isset($_POST['movie_id'])) {
    if ($db->dbConnect()) {
        if ($db->addDirectionMovie("movie_direction", $_POST['dir_id'], $_POST['movie_id'])) {
            echo "Direction added Success";
        } else echo "Direction add Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>