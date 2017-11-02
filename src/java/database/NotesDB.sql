DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;

USE NotesDB;

DROP TABLE User;

CREATE TABLE User( 
    noteId int NOT NULL AUTO_INCREMENT,
    dateCreated DATE NOT NULL,
    contents VARCHAR(10000) NOT NULL,
    PRIMARY KEY (noteId)
);

INSERT INTO Note values(CURDATE(), 'Something something dark side...';