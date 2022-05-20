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


    function addMovie($table, $movie_studio, $movie_category, $movie_name, $movie_length, $rate)
    {
        $movie_studio = $this->prepareData($movie_studio);
        $movie_category = $this->prepareData($movie_category);
        $movie_name = $this->prepareData($movie_name);
        $movie_length = $this->prepareData($movie_length);
        $rate = $this->prepareData($rate);
        $this->sql =
            "INSERT INTO ". $table . "(movie_studio,movie_category,movie_name, movie_length, movie_rate) VALUES ('". $movie_studio . "','". $movie_category . "','". $movie_name . "','" . $movie_length . "', '" . $rate . "')";
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

    function addActInMovie($table, $actorid, $movieid,  $playrole)
    {
        $actorid = $this->prepareData($actorid);
        $movieid = $this->prepareData($movieid);
        $playrole = $this->prepareData($playrole);
        $this->sql =
            "INSERT INTO " . $table . " (actor_id, movie_id, played_role) VALUES ('" . $actorid . "','" . $movieid . "','" . $playrole . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function addDirectionMovie($table, $dirid, $movieid)
    {
        $dirid = $this->prepareData($dirid);
        $movieid = $this->prepareData($movieid);
        $this->sql =
            "INSERT INTO " . $table . " (dir_id, movie_id) VALUES ('" . $dirid . "','" . $movieid . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function addRating($table, $movieid, $revid, $revstars, $numorating)
    {
        $movieid = $this->prepareData($movieid);
        $revid = $this->prepareData($revid);
        $revstars = $this->prepareData($revstars);  
        $numorating = $this->prepareData($numorating);
        $this->sql =
            "INSERT INTO " . $table . " (movie_id, rev_id, rev_stars, num_o_ratings) VALUES ('" . $movieid . "','" . $revid . "','" . $revstars . "', '" . $numorating . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

}

?>
