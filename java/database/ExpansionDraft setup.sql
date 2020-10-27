BEGIN TRANSACTION;

CREATE TABLE players (
player_id serial NOT NULL,
name varchar(64) NOT NULL,
position varchar(2),
team_name varchar(64) NOT NULL,

CONSTRAINT pk_player_player_id PRIMARY KEY (player_id),
CONSTRAINT check_player_position CHECK (position IN ('GK', 'DF', 'MF', 'FW'))
);

SELECT * FROM players;

COMMIT;