<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['movie_name']) && isset($_POST['movie_length'])){
    if ($db->dbConnect()){
        if ($db->addMovie("movie",$_POST['movie_name'],$_POST['movie_length'])){
            echo "Movie added successfully";
        } else echo "Movie doesnt added!";
    }else echo "Error: Database conenction";
}else echo "All fields are required";
?>