<?php

$host='localhost';
$username='root';
$pwd='';
$db='movies';

$con=mysqli_connect($host,$username,$pwd,$db) or die('Unable to connect');

if(mysqli_connect_error())
{
    echo "Failed to connect";
}

$query=mysqli_query($con,"SELECT actor_id, actor_name from actors");

if($query)
{
    while($row=mysqli_fetch_array($query))
    {
        $flag[] =$row;
    }
    print(json_encode($flag));
}

mysqli_close($con);
?>