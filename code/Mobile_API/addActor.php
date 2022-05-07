<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['name']) && isset($_POST['age']) && isset($_POST['gender'])) {
    if ($db->dbConnect()) {
        if ($db->addActor("actors", $_POST['name'], $_POST['age'], $_POST['gender'])) {
            echo "Actor added Success";
        } else echo "Actor add Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>