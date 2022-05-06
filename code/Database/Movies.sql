CREATE Database movies;

Use movies;

Create table Movie(
    movie_id int NOT NULL AUTO_INCREMENT,
    movie_studio varchar(250) not null,
    movie_category varchar(250) not null,
    movie_name varchar(250) not null ,
    movie_length INT not null,
    PRIMARY KEY (movie_id)
);

Create table Actors(
    actor_id INT NOT NULL AUTO_INCREMENT,
    actor_name varchar(250) not null,
    actor_age int not null,
    actor_gender varchar(20) not null,
    PRIMARY KEY(actor_id)
);

Create table Played_role(
    actor_id INT,
    movie_id INT,
    played_role varchar(250) not null,
    FOREIGN KEY(actor_id) References Actors(actor_id),
    FOREIGN KEY(movie_id) References Movie(movie_id) 
);

Create table Genres(
    gen_id int AUTO_INCREMENT,
    gen_title varchar(250) not null ,
    PRIMARY KEY (gen_id)
);

Create table Director(
    director_id int AUTO_INCREMENT,
    director_name  varchar(250) not null,
    PRIMARY KEY(director_id)
);

Create table Movie_genres(
    movie_id int,
    gen_id int,
    FOREIGN KEY(movie_id) References Movie(movie_id),
    FOREIGN KEY(gen_id) References Genres(gen_id)
);

Create table Movie_direction(
    dir_id int,
    movie_id int,
    PRIMARY KEY(dir_id),
    FOREIGN KEY(movie_id) References Movie(movie_id)
);

Create table Reviewer(
    rev_id int AUTO_INCREMENT,
    rev_name varchar(250) not null,
    PRIMARY KEY(rev_id)
);

Create table Rating(
    movie_id int,
    rev_id int,
    rev_stars int not null,
    num_o_ratings int not null,
    FOREIGN KEY(movie_id) References Movie(movie_id),
    FOREIGN KEY(rev_id) References Reviewer(rev_id)
);


Create table User(
    id int AUTO_INCREMENT,
    username varchar(250) not null,
    passwd varchar(250) not null,
    first_name varchar(250) not null,
    last_name varchar(250) not null,
    email varchar(250) not null,
    isAdmin tinyInt(1) not null DEFAULT "0",
    PRIMARY KEY(id),
    UNIQUE KEY username_UNIQUE (username)
);