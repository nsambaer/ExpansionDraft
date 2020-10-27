BEGIN TRANSACTION;


DROP TABLE IF EXISTS players CASCADE;

CREATE TABLE players (
player_id serial NOT NULL,
name varchar(64) NOT NULL,
position varchar(2),
team_name varchar(64) NOT NULL,
protected boolean DEFAULT false NOT NULL,
allocated boolean DEFAULT false NOT NULL,

CONSTRAINT pk_player_player_id PRIMARY KEY (player_id),
CONSTRAINT check_player_position CHECK (position IN ('GK', 'DF', 'MF', 'FW'))
);

        
INSERT INTO players (name, position, team_name)
VALUES ('Veronica Latsko', 'FW', 'Houston Dash'),
        ('Haley Hanson', 'MF', 'Houston Dash'),
        ('Zoe Morse', 'DF', 'Chicago Red Stars');

SELECT * FROM players;



COMMIT;