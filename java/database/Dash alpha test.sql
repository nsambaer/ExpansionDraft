BEGIN TRANSACTION;

INSERT INTO federations (fifa_code, name, confederation)
VALUES ('USA', 'United States Soccer Federation', 'CONCACAF'),
        ('ENG', 'Football Association', 'UEFA'),
        ('CAN', 'Canadian Soccer Association', 'CONCACAF'),
        ('JAM', 'Jamaica Football Federation', 'CONCACAF'); 


INSERT INTO leagues (name, abbr, fifa_code)
VALUES ('National Women''s Soccer League', 'NWSL', 'USA');

INSERT INTO teams (name, league_id)
VALUES ('Houston Dash', (SELECT league_id FROM leagues WHERE abbr LIKE 'NWSL'));

INSERT INTO players (first_name, last_name, number, position, fifa_code, nationality, college)
VALUES ('Jane', 'Campbell', 1, 'GK', 'USA', 'USA', 'Stanford'),
        ('Allysha', 'Chapman', 2, 'DF', 'CAN', 'CAN', 'LSU'),
        ('Megan', 'Oyster', 11, 'DF', 'USA', 'USA', 'UCLA'),
        ('Katie', 'Naughton', 25, 'DF', 'USA', 'USA', 'Notre Dame'),
        ('Ally', 'Prisock', 23, 'DF', 'USA', 'USA', 'USC'),
        ('Kristie', 'Mewis', 19, 'MF', 'USA', 'USA', 'Boston College'),
        ('Haley', 'Hanson', 9, 'MF', 'USA', 'USA', 'Nebraska'),
        ('Shea', 'Groom', 6, 'MF', 'USA', 'USA', 'Texas A&M'),
        ('Veronica', 'Latsko', 12, 'FW', 'USA', 'USA', 'Virginia'),
        ('Rachel', 'Daly', 3, 'FW', 'ENG', 'ENG', 'St. John''s'),
        ('Katie', 'Stengel', 7, 'FW', 'USA', 'USA', 'Wake Forest');

INSERT INTO player_team_history (player_id, team_id, start_date, end_date)
VALUES ((SELECT player_id FROM players WHERE last_name LIKE 'Campbell'), (SELECT team_id FROM teams WHERE name LIKE 'Houston Dash'), '2017-01-12', NULL),
       ((SELECT player_id FROM players WHERE last_name LIKE 'Chapman'), (SELECT team_id FROM teams WHERE name LIKE 'Houston Dash'), '2018-05-09', NULL),
        ((SELECT player_id FROM players WHERE last_name LIKE 'Oyster'), (SELECT team_id FROM teams WHERE name LIKE 'Houston Dash'), '2020-02-03', NULL),
        ((SELECT player_id FROM players WHERE last_name LIKE 'Groom'), (SELECT team_id FROM teams WHERE name LIKE 'Houston Dash'), '2020-02-03', NULL),
        ((SELECT player_id FROM players WHERE last_name LIKE 'Naughton'), (SELECT team_id FROM teams WHERE name LIKE 'Houston Dash'), '2020-01-06', NULL),
        ((SELECT player_id FROM players WHERE last_name LIKE 'Prisock'), (SELECT team_id FROM teams WHERE name LIKE 'Houston Dash'), '2019-01-10', NULL),
        ((SELECT player_id FROM players WHERE last_name LIKE 'Mewis'), (SELECT team_id FROM teams WHERE name LIKE 'Houston Dash'), '2017-08-30', NULL),
        ((SELECT player_id FROM players WHERE last_name LIKE 'Hanson'), (SELECT team_id FROM teams WHERE name LIKE 'Houston Dash'), '2018-01-18', NULL),
        ((SELECT player_id FROM players WHERE last_name LIKE 'Latsko'), (SELECT team_id FROM teams WHERE name LIKE 'Houston Dash'), '2018-01-18', NULL),
        ((SELECT player_id FROM players WHERE last_name LIKE 'Stengel'), (SELECT team_id FROM teams WHERE name LIKE 'Houston Dash'), '2020-01-08', NULL),
        ((SELECT player_id FROM players WHERE last_name LIKE 'Daly'), (SELECT team_id FROM teams WHERE name LIKE 'Houston Dash'), '2016-01-15', NULL);




SELECT * FROM players;
SELECT * FROM teams;
SELECT * FROM player_team_history;


COMMIT;