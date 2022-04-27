<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['id']) && isset($_POST['username']) && isset($_POST['passwd']) && isset($_POST['first_name']) && isset($_POST['last_name']) && isset($_POST['email'])) {
    if ($db->dbConnect()) {
        if ($db->modifyProfile("user", $_POST['id'], $_POST['username'], $_POST['passwd'], $_POST['first_name'], $_POST['last_name'], $_POST['email'])) {
            echo "Modify Success";
        } else echo "Modify Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>