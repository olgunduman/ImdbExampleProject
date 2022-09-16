insert into bootcampdb.movie (id,director,genre,name,release_year,status)
values (1001,'movie-director 1001','ACTION','movie-name 1001',2015,'ACTIVE'),
       (1002,'ACTIVE','COMEDY','movie-name 1002',2015,'ACTIVE');

insert into bootcampdb.actor (id,name,birth_date)
values (2001,'test actor 2001','2001-01-12 11:00:00'),
       (2002,'test actor 2002','2002-01-12 12:00:00'),
       (2003,'test actor 2003','2003-01-12 13:00:00');

insert into bootcampdb.matching(id,movie_id,actor_id)
values (3001,1001,2001),
       (3002,1002,2002),
       (3003,1001,2003),
       (3004,1002,2001);
