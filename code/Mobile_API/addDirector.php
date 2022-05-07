<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['name'])) {
    if ($db->dbConnect()) {
        if ($db->addDirector("director", $_POST['name'])) {
            echo "Director added Success";
        } else echo "Director add Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>