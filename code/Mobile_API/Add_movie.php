<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['movie_studio']) && isset($_POST['movie_category']) && isset($_POST['movie_name']) && isset($_POST['movie_length']) && isset($_POST['movie_rate'])){
    if ($db->dbConnect()){
        if ($db->addMovie("movie",$_POST['movie_studio'],$_POST['movie_category'],$_POST['movie_name'],$_POST['movie_length'], $_POST['movie_rate'])){
            echo "Movie added successfully";
        } else echo "Movie doesnt added!";
    }else echo "Error: Database conenction";
}else echo "All fields are required";
?>