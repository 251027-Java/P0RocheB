CREATE SCHEMA IF NOT EXISTS ChessGames;
CREATE TABLE IF NOT EXISTS ChessGames.games (
game_id INT PRIMARY KEY,
date TIMESTAMP NOT NULL,
white VARCHAR(50) NOT NULL,
black VARCHAR(50) NOT NULL,
event VARCHAR(100) NOT NULL,
result VARCHAR(10) NOT NULL
);  
CREATE TABLE IF NOT EXISTS ChessGames.moves ( 
move_number INT NOT NULL, 
game_id INT NOT NULL, 
color VARCHAR(5),  
notation VARCHAR (10),
PRIMARY KEY (move_number, game_id)
);
CREATE TABLE IF NOT EXISTS ChessGames.notations ( 
notation VARCHAR (10) PRIMARY KEY, 
piece VARCHAR (10), 
square VARCHAR (2),
isCheck boolean, 
isMate boolean, 
isShortCastle boolean, 
isLongCastle boolean, 
isCapture boolean,  
isPromotion boolean 
); 
ALTER TABLE ChessGames.moves 
ADD FOREIGN KEY (game_id) REFERENCES ChessGames.games(game_id) ON DELETE CASCADE; 
ALTER TABLE ChessGames.moves 
ADD FOREIGN KEY (notation) REFERENCES ChessGames.notations(notation); 
;;