BEGIN TRANSACTION;


DROP TABLE IF EXISTS players CASCADE;
DROP TABLE IF EXISTS teams CASCADE;
DROP TABLE IF EXISTS int_players CASCADE;

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

CREATE TABLE int_players (
player_id serial NOT NULL,
name varchar(64) NOT NULL,
position varchar(2),
team_name varchar(64),
price int NOT NULL,

CONSTRAINT pk_intplayer_id PRIMARY KEY (player_id),
CONSTRAINT check_player_position CHECK (position IN ('GK', 'DF', 'MF', 'FW'))
);

        
INSERT INTO players (name, position, team_name)
VALUES ('Veronica Latsko', 'FW', 'Houston Dash'),
        ('Haley Hanson', 'MF', 'Houston Dash'),
        ('Zoe Morse', 'DF', 'Chicago Red Stars'),
        ('Madison Pogarch', 'DF', 'Portland Thorns'),
        ('Savannah McCaskill', 'FW', 'Racing Louisville'),
        ('Yuki Nagasato', 'FW', 'Racing Louisville');

INSERT INTO players (name, position, team_name, allocated)       
VALUES ('Lindsey Horan', 'MF', 'Portland Thorns', true);

INSERT INTO teams (name, money)
VALUES ('Houston Dash', 300),
        ('Portland Thorns', 300),
        ('Chicago Red Stars', 300),
        ('Racing Louisville', 300);
        
INSERT INTO int_players (name, position, team_name, price)
VALUES ('Ellen White', 'FW', 'Manchester City', 200),
        ('Amandine Henry', 'MF', 'Olympique Lyonnais', 210),
        ('Maria Thorisdottir', 'DF', 'Chelsea FC', 100);
        
SELECT * FROM players;
SELECT * FROM teams;
SELECT * FROM int_players;


COMMIT;