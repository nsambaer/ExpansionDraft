BEGIN TRANSACTION;

DROP TABLE IF EXISTS players CASCADE;
DROP TABLE IF EXISTS teams CASCADE;

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

CREATE TABLE teams (
team_id serial NOT NULL,
name varchar(64) NOT NULL,
money int NOT NULL,

CONSTRAINT pk_team_team_id PRIMARY KEY (team_id)
);

SELECT * FROM players;
SELECT * FROM teams;

COMMIT;