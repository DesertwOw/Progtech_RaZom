<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $passwd;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->passwd = $dbc->passwd;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->passwd, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $username, $passwd)
    {
        $username = $this->prepareData($username);
        $passwd = $this->prepareData($passwd);
        $this->sql = "select * from " . $table . " where username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['username'];
            $dbpasswd = $row['passwd'];
            if ($dbusername == $username && password_verify($passwd, $dbpasswd)) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $username, $passwd, $first_name, $last_name, $email)
    {
        $username = $this->prepareData($username);
        $passwd = password_hash($passwd, PASSWORD_DEFAULT);
        $first_name = $this->prepareData($first_name);
        $last_name = $this->prepareData($last_name);       
        $email = $this->prepareData($email);
        $this->sql =
            "INSERT INTO " . $table . " (username, passwd, first_name, last_name, email) VALUES ('" . $username . "','" . $passwd . "','" . $first_name . "','" . $last_name . "','" . $email . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function modifyProfile($table, $id, $username, $passwd, $first_name, $last_name, $email){
        $id = $this->prepareData($id);
        $username = $this->prepareData($username);
        $passwd = $this->prepareData($passwd);
        $first_name = $this->prepareData($first_name);
        $last_name = $this->prepareData($last_name);       
        $email = $this->prepareData($email);
        $this->sql =
            "UPDATE" . $table . " SET username = '" . $username . "', passwd = '" . $passwd . "', first_name = '" . $first_name . "', last_name = '" . $last_name . "', email = '" . $email . "' WHERE id = '" . $id . "';";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;

    }

    function addMovie($table, $movie_studio, $movie_category, $movie_name, $movie_length)
    {
        $movie_studio = $this->prepareData($movie_studio);
        $movie_category = $this->prepareData($movie_category);
        $movie_name = $this->prepareData($movie_name);
        $movie_length = $this->prepareData($movie_length);
        $this->sql =
            "INSERT INTO ". $table . "(movie_studio,movie_category,movie_name, movie_length) VALUES ('". $movie_studio . "','". $movie_category . "','". $movie_name . "','" . $movie_length . "')";
        if (mysqli_query($this->connect, $this->sql)){
            return true;
        } else return false;
    }

    function showMovie($movie_studio, $movie_category, $movie_name, $movie_length )
    {
        $movie_studio = $this->prepareData($movie_studio);
        $movie_category = $this->prepareData($movie_category);
        $movie_name = $this->prepareData($movie_name);
        $movie_length = $this->prepareData($movie_length);
        $this->sql = 
            "SELECT (id,movie_studio,movie_category,movie_name, movie_length) FROM movie";
        if (mysqli_query($this->connect, $this->sql)){
            return true;
        } else return false;
    }

    function addActor($table, $name, $age, $gender)
    {
        $name = $this->prepareData($name);
        $age = $this->prepareData($age);
        $gender = $this->prepareData($gender);       
        $this->sql =
            "INSERT INTO " . $table . " (actor_name, actor_age, actor_gender) VALUES ('" . $name . "','" . $age . "','" . $gender . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function addDirector($table, $name)
    {
        $name = $this->prepareData($name);     
        $this->sql =
            "INSERT INTO " . $table . " (director_name) VALUES ('" . $name . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

}

?>
