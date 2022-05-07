 <?php 
 
 
 define('DB_HOST', 'localhost');
 define('DB_USER', 'root');
 define('DB_PASS', '');
 define('DB_NAME', 'movies');
 
 $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);

 if (mysqli_connect_errno()) {
 echo "Failed to connect to MySQL: " . mysqli_connect_error();
 die();
 }
 
 $stmt = $conn->prepare("SELECT movie_id, movie_studio, movie_category, movie_name, movie_length FROM movie;");
 
 $stmt->execute();

 $stmt->bind_result($movie_id, $movie_studio, $movie_category, $movie_name, $movie_length);
 
 $movies = array(); 
 
 while($stmt->fetch()){
 $temp = array();
 $temp['movie_id'] = $movie_id; 
 $temp['movie_studio'] = $movie_studio; 
 $temp['movie_category'] = $movie_category; 
 $temp['movie_name'] = $movie_name; 
 $temp['movie_length'] = $movie_length; 
 array_push($movies, $temp);
 }
 
 echo json_encode($movies);