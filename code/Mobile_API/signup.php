<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['passwd']) && isset($_POST['first_name']) && isset($_POST['last_name']) && isset($_POST['email'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("user", $_POST['username'], $_POST['passwd'], $_POST['first_name'], $_POST['last_name'], $_POST['email'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
