DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;

USE NotesDB;

CREATE TABLE Note( 
    noteId int NOT NULL AUTO_INCREMENT,
    dateCreated DATE NOT NULL,
    contents VARCHAR(10000) NOT NULL,
    PRIMARY KEY (noteId));

INSERT INTO Note (dateCreated, contents) VALUES(CURDATE(), 'Something something dark side...');