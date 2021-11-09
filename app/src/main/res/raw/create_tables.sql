CREATE TABLE IF NOT EXISTS 'game' (
  'id' int(11) NOT NULL,
  'idTeamOne' int(11) NOT NULL,
  'idTeamTwo' int(11) NOT NULL,
  'localisation' text NOT NULL,
  'finished' text NOT NULL,
  PRIMARY KEY ('id'),
  CONSTRAINT 'game_ibfk_1' FOREIGN KEY ('idTeamOne') REFERENCES 'team' ('id'),
  CONSTRAINT 'game_ibfk_2' FOREIGN KEY ('idTeamTwo') REFERENCES 'team' ('id')
);

CREATE TABLE IF NOT EXISTS 'connected'(
    'userName' text NOT NULL
);

CREATE TABLE IF NOT EXISTS 'player' (
  'id' int(11) NOT NULL,
  'userName' text NOT NULL,
  'firstName' text NOT NULL,
  'lastName' text NOT NULL,
  'password' text NOT NULL,
  'birthday' date DEFAULT NULL,
  'email' text NOT NULL,
  'idTeam' int(11) DEFAULT NULL,
  PRIMARY KEY ('id'),
  CONSTRAINT 'joueur_ibfk_1' FOREIGN KEY ('idTeam') REFERENCES 'team' ('id')
);

CREATE TABLE IF NOT EXISTS 'points' (
  'id' int(11) NOT NULL,
  'idGame' int(11) NOT NULL,
  'idTeam' int(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS 'team' (
  'id' int(11) NOT NULL,
  'name' text NOT NULL,
  'localisation' text NOT NULL,
  PRIMARY KEY ('id')
);

