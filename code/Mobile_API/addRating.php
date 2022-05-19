<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['movie_id']) && isset($_POST['rev_id']) && isset($_POST['rev_stars']) && isset($_POST['num_o_ratings'])) {
    if ($db->dbConnect()) {
        if ($db->addRating("rating", $_POST['movie_id'], $_POST['rev_id'], $_POST['rev_stars'], $_POST['num_o_ratings'])) {
            echo "Rating added Success";
        } else echo "Rating add Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>