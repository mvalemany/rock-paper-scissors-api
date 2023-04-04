CREATE TABLE MATCH (
  id INT AUTO_INCREMENT PRIMARY KEY,
  player_one INT NOT NULL,
  player_two INT NOT NULL,
  weapon_one INT NOT NULL,
  weapon_two INT NOT NULL,
  result INT NOT NULL
);

CREATE TABLE SCORE (
  player_id INT NOT NULL PRIMARY KEY,
  victories INT NOT NULL,
  matches INT NOT NULL
);