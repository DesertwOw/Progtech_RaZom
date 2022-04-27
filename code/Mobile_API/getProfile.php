<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['passwd'])) {
    if ($db->dbConnect()) {
        if ($db->getProfile("user", $_POST['username'], $_POST['passwd'])) {
            echo "Get datas successfull";
        } else echo "Username or Password wrong";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>

