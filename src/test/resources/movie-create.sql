insert into bootcampdb.movie (id,created_date,director,genre,name,release_year,status)
values (1001,now(),'movie-director 1001','ACTION','movie-name 1001',2015,'ACTIVE'),
       (1002,now(),'ACTIVE','COMEDY','movie-name 1002',2015,'ACTIVE');

insert into bootcampdb.actor (id,created_date,name,birth_date)
values (2001,now(),'test actor 2001','2001-01-12 11:00:00'),
       (2002,now(),'test actor 2002','2002-01-12 12:00:00'),
       (2003,now(),'test actor 2003','2003-01-12 13:00:00');

insert into bootcampdb.matching(id,created_date,movie_id,actor_id)
values (3001,now(),1001,2001),
       (3002,now(),1002,2002),
       (3003,now(),1001,2003),
       (3004,now(),1002,2001);
