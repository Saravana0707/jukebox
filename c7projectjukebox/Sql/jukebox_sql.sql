create database jukebox;

use jukebox;

 CREATE TABLE genres_details
   (
      genres_id bigint AUTO_INCREMENT PRIMARY KEY, 
      genres_name varchar(255) not null
   )
   AUTO_INCREMENT = 70000;
   
    CREATE TABLE artist_details
   (
      artist_id bigint AUTO_INCREMENT PRIMARY KEY, 
      artist_name varchar(255) not null
   )
   AUTO_INCREMENT = 60000;
   
   
       CREATE TABLE album_details
   (
      album_id bigint AUTO_INCREMENT PRIMARY KEY, 
      album_name varchar(255) not null,
      year_released char(10) not null
   )
   AUTO_INCREMENT = 50000;
   
CREATE TABLE song_details
   (
      song_id bigint AUTO_INCREMENT PRIMARY KEY, 
      song_name varchar(255) not null,
      artist_id bigint,
      album_id bigint,
      duration char(10),
      genres_id bigint,
      FOREIGN KEY (artist_id) REFERENCES artist_details(artist_id) ON DELETE CASCADE,
      FOREIGN KEY (album_id) REFERENCES album_details(album_id) ON DELETE CASCADE,
      FOREIGN KEY (genres_id) REFERENCES genres_details(genres_id) ON DELETE CASCADE
   )
   AUTO_INCREMENT = 10000;
   
  create view vw_song_details
  as (
	select sd.song_name songname,ald.album_name album_name,ad.artist_name artist_name,sd.duration duration,ald.year_released year_released,gd.genres_name genres_name
    from song_details sd join artist_details ad
   on sd.artist_id=ad.artist_id join album_details ald
   on sd.album_id=ald.album_id join genres_details gd
   on sd.genres_id=gd.genres_id
   );
   
   
      CREATE TABLE narrator_details
   (
      narrator_id bigint AUTO_INCREMENT PRIMARY KEY, 
      narrator_name varchar(255) not null
   )
   AUTO_INCREMENT = 60000;
   

         CREATE TABLE celebrity_details
   (
      celebrity_id bigint AUTO_INCREMENT PRIMARY KEY, 
      celebrity_name varchar(255) not null
   )
   AUTO_INCREMENT = 50000;
   
   CREATE TABLE podcast_details
   (
      podcast_id bigint AUTO_INCREMENT PRIMARY KEY, 
      podcast_name varchar(255) not null,
      narrator_id bigint,
      celebrity_id bigint,
      number_of_episodes int,
      genres_id bigint,
      FOREIGN KEY (narrator_id) REFERENCES narrator_details(narrator_id) ON DELETE CASCADE,
      FOREIGN KEY (celebrity_id) REFERENCES celebrity_details(celebrity_id) ON DELETE CASCADE,
      FOREIGN KEY (genres_id) REFERENCES genres_details(genres_id) ON DELETE CASCADE
   )
   AUTO_INCREMENT = 10000;
   
   
   CREATE TABLE podcast_episode_details
   (
      podcast_episode_id bigint AUTO_INCREMENT PRIMARY KEY, 
      podcast_episode_number int  ,
      podcast_episode_name varchar(255) not null,
      short_description varchar(255),
      duration_in_min char(10),
      podcast_id bigint,
      upload_date date,
      FOREIGN KEY (podcast_id) REFERENCES podcast_details(podcast_id) ON DELETE CASCADE
   )
   AUTO_INCREMENT = 20000;
   
ALTER TABLE podcast_episode_details
MODIFY COLUMN podcast_episode_number INT;

alter table podcast_episode_details drop index podcast_episode_number
   
    create view vw_podcast_details
  as (
select pd.podcast_id podcast_id,pd.podcast_name podcast_name,ped.podcast_episode_number podcast_episode_number,ped.podcast_episode_name podcast_episode_name,ped.short_description short_description,ped.duration_in_min duration_in_min,cd.celebrity_name celebrity_name,nd.narrator_name narrator_name,gd.genres_name genres_name,ped.upload_date upload_date,pd.number_of_episodes
   from podcast_episode_details ped join podcast_details pd
   on ped.podcast_id=pd.podcast_id join celebrity_details cd
   on pd.celebrity_id=cd.celebrity_id join narrator_details nd
   on pd.narrator_id=nd.narrator_id join genres_details gd
   on pd.genres_id=gd.genres_id
   );


select * from vw_podcast_details;
   
   select pd.podcast_name podcast_name,ped.podcast_episode_number podcast_episode_number,ped.podcast_episode_name podcast_episode_name,ped.short_description short_description,ped.duration_in_min duration_in_min,cd.celebrity_name celebrity_name,nd.narrator_name narrator_name,gd.genres_name genres_name,ped.upload_date upload_date
   from podcast_episode_details ped join podcast_details pd
   on ped.podcast_id=pd.podcast_id join celebrity_details cd
   on pd.celebrity_id=cd.celebrity_id join narrator_details nd
   on pd.narrator_id=nd.narrator_id join genres_details gd
   on pd.genres_id=gd.genres_id;
   
   select * from podcast_episode_details;
   select * from podcast_details;
   select * from narrator_details;
   select * from celebrity_details;
    select * from genres_details;
    
       select * from genres_details;
   select * from album_details;
   select * from song_details;
   select * from artist_details;
      
   select * from vw_song_details;
   
   create table user
   (
   user_id bigint AUTO_INCREMENT PRIMARY KEY, 
    user_name varchar(255) not null,
    user_password char(8) not null
   )
   AUTO_INCREMENT = 1;
   
    CREATE TABLE playlist_list_temp
   (
      playlist_id bigint AUTO_INCREMENT PRIMARY KEY, 
      playlist_name varchar(255) not null,
      playlist_added_date date  DEFAULT (current_date),
      user_id bigint,
      FOREIGN KEY (user_id) REFERENCES user(user_id)
   )
   AUTO_INCREMENT = 80000;
   
   CREATE TABLE playlist_list_temp
   (
      playlist_id bigint AUTO_INCREMENT PRIMARY KEY, 
      playlist_name varchar(255) not null,
      playlist_added_date date  DEFAULT (current_date)
   )
   AUTO_INCREMENT = 80000;
   
       CREATE TABLE playlist_details_temp
   (
      playlist_id bigint, 
      album_id bigint,
      track_id bigint,
      album_name varchar(255) not null,
      track_name varchar(255) not null,
      artist_name varchar(255) not null,
      duration_in_min char(10),
      FOREIGN KEY (playlist_id) REFERENCES playlist_list_temp(playlist_id),
      FOREIGN KEY (album_id) REFERENCES album_details(album_id),
	  FOREIGN KEY (album_id) REFERENCES podcast_details(podcast_id),
      FOREIGN KEY (track_id) REFERENCES song_details(song_id),
      FOREIGN KEY (track_id) REFERENCES podcast_episode_details(podcast_episode_id)
   )
   
   select * from playlist_details_temp1;
   
   describe playlist_details_temp1;
   
    CREATE TABLE playlist_details_temp1
   (
      playlist_id bigint, 
      album_id bigint,
      track_id bigint,
      album_name varchar(255) not null,
      track_name varchar(255) not null,
      artist_name varchar(255) not null,
      duration_in_min char(10),
      FOREIGN KEY (playlist_id) REFERENCES playlist_list_temp(playlist_id),
  CONSTRAINT fk_album_details FOREIGN KEY (album_id) REFERENCES album_details(album_id),
CONSTRAINT fk_podcast_details FOREIGN KEY (album_id) REFERENCES podcast_details(podcast_id),
CONSTRAINT fk_playlist_list_temp FOREIGN KEY (track_id) REFERENCES song_details(song_id),
CONSTRAINT fk_podcast_episode_details FOREIGN KEY (track_id) REFERENCES podcast_episode_details(podcast_episode_id)
   )
   
    CREATE TABLE playlist_details_temp2
   (
      playlist_id bigint, 
      album_id bigint,
      track_id bigint,
      album_name varchar(255) not null,
      track_name varchar(255) not null,
      artist_name varchar(255) not null,
      duration_in_min char(10),
      FOREIGN KEY (playlist_id) REFERENCES playlist_list_temp(playlist_id)
   )
   
   create view vw_playlist_details_temp2 as
(
select plt.playlist_name,pdt.album_name,pdt.track_name,pdt.artist_name,pdt.duration_in_min from playlist_details_temp2 pdt join playlist_list_temp plt
on pdt.playlist_id=plt.playlist_id
);

select * from vw_playlist_details_temp2;
select * from playlist_details_temp2;
select * from playlist_list_temp;

select * from playlist_details_temp2 where playlist_id='80030'


 CREATE TABLE playlist_details_temp4
   (
      playlist_id bigint, 
      album_id bigint,
      track_id bigint,
      artist_id bigint,
      FOREIGN KEY (playlist_id) REFERENCES playlist_list_temp(playlist_id)
   )
   
      create view vw_playlist_details_temp2 as
(
select plt.playlist_name,pdt.album_name,pdt.track_name,pdt.artist_name,pdt.duration_in_min from playlist_details_temp2 pdt join playlist_list_temp plt
on pdt.playlist_id=plt.playlist_id 
);

insert into playlist_details_temp4 (playlist_id,album_id,track_id,artist_id) values(80000,50000,10002,60000);

insert into playlist_details_temp4 (playlist_id,album_id,track_id,artist_id) values(80000,50000,10003,60000);


insert into playlist_details_temp4 (playlist_id,album_id,track_id,artist_id) values(80000,10003,20016,50001);

create view vw_playlist_details_temp5
as
select playlist_name,album_name,song_name,artist_name,duration from (
select plt.playlist_name,ad.album_name,sd.song_name,art.artist_name,sd.duration from playlist_details_temp4 pdt join playlist_list_temp plt
on pdt.playlist_id=plt.playlist_id 
join album_details ad on ad.album_id=pdt.album_id 
join song_details sd on sd.song_id=pdt.track_id
join artist_details art on art.artist_id=pdt.artist_id
union all
select plt.playlist_name,pod.podcast_name,ped.podcast_episode_name,concat(cd.celebrity_name,',',nd.narrator_name),ped.duration_in_min from playlist_details_temp4 pdt join playlist_list_temp plt
on pdt.playlist_id=plt.playlist_id 
join podcast_episode_details ped on ped.podcast_episode_id=pdt.track_id
join podcast_details pod on pod.podcast_id=pdt.album_id
join celebrity_details cd on cd.celebrity_id=pdt.artist_id
join narrator_details nd on nd.narrator_id=pod.narrator_id) a;


select * from  vw_playlist_details_temp5;


select * from narrator_details


select * from podcast_episode_details;
   select * from podcast_details;

   select * from celebrity_details;


select * from album_details;
   select * from song_details;
   select * from artist_details;
   
   
   

   