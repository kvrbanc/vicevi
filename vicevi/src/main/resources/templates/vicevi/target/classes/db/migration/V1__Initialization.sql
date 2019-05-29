CREATE TABLE category
  (
     id         INTEGER NOT NULL,
     name       VARCHAR(255) NOT NULL,
     PRIMARY KEY (id)
  );
  CREATE TABLE joke
   (
       id         INTEGER NOT NULL,
       content       VARCHAR(255) NOT NULL,
       likes      INTEGER NOT NULL,
       dislikes   INTEGER NOT NULL,
       category_id   INTEGER,
       PRIMARY KEY (id),
       FOREIGN KEY(category_id) REFERENCES category
   );