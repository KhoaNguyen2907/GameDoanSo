drop database if exists gamedoanso;
CREATE database if not exists gamedoanso;
use gamedoanso;

drop table if exists Player;
create table Player (
	username varchar(255) not null,
    password varchar(255) not null,
    name varchar(255) not null,
    primary key (username)
    );

drop table if exists GameSession;
create table GameSession (
	id varchar(15),
    target_num int not null,
    start_time timestamp,
    end_time  timestamp,
    username varchar(255),
    is_completed boolean,
    is_active boolean,
    primary key (id)
    );

drop table if exists Guess;
create table Guess (
	id int auto_increment,
    guess_num int not null,
    result varchar(100),
    timestamp timestamp,
    game_session_id varchar(15) not null,
    primary key (id)
    );

alter table GameSession add constraint fk_gamesession_player
	foreign key (username) references Player(username);
alter table Guess add constraint fk_guess_gamesession
	foreign key (game_session_id) references GameSession(id);

    