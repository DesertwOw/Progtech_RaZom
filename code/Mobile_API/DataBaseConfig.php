<?php

class DataBaseConfig
{
    public $servername;
    public $username;
    public $passwd;
    public $databasename;

    public function __construct()
    {

        $this->servername = 'localhost';
        $this->username = 'root';
        $this->passwd = '';
        $this->databasename = 'movies';

    }
}

?>
