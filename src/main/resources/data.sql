INSERT INTO teams (name)
            VALUES('LOKO');
INSERT INTO teams (name)
            VALUES('Ferrari Team');
INSERT INTO teams (name)
            VALUES('LADA SEDAN - BAKLAZHAN');


INSERT INTO cars (brand, car_model, engine_power, team_id)
            VALUES('NISSAN', 'TEANA', 182, 1);
INSERT INTO cars (brand, car_model, engine_power, team_id)
            VALUES('TOYOTA', 'LAND CRUISER 200', 235, 1);
INSERT INTO cars (brand, car_model, engine_power, team_id)
            VALUES('PORSCHE', '911 TURBO', 500, 1);

INSERT INTO cars (brand, car_model, engine_power, team_id)
            VALUES('NISSAN', 'QASHQAI', 141, 2);
INSERT INTO cars (brand, car_model, engine_power, team_id)
            VALUES('HONDA', 'ACCORD', 201, 2);
INSERT INTO cars (brand, car_model, engine_power, team_id)
            VALUES('FORD', 'FOCUS', 115, 2);

INSERT INTO cars (brand, car_model, engine_power, team_id)
            VALUES('RANG ROVER', 'SPORT V8', 410, 3);
INSERT INTO cars (brand, car_model, engine_power, team_id)
            VALUES('BENTLEY', 'FLYING SPUR', 625, 3);



INSERT INTO racers (name, team_id)
            VALUES('Lewis Hamilton', 1);
INSERT INTO racers (name, team_id)
            VALUES('Jenson Button', 1);
INSERT INTO racers (name, team_id)
            VALUES('Mika Häkkinen', 1);

INSERT INTO racers (name, team_id)
            VALUES('Sebastian Vettel', 2);
INSERT INTO racers (name, team_id)
            VALUES('Niki Lauda', 2);

INSERT INTO racers (name, team_id)
            VALUES('Fernando Alonso', 3);



INSERT INTO tracks (name_of_track, track_status)
            VALUES('TRACK #1', 'FREE');
INSERT INTO tracks (name_of_track, track_status)
            VALUES('TRACK #2', 'FREE');
INSERT INTO tracks (name_of_track, track_status)
            VALUES('TRACK #3', 'FREE');



INSERT INTO races (status, track_id)
            VALUES('FINISHED', 1);
INSERT INTO races (status, track_id)
            VALUES('FINISHED', 3);



INSERT INTO race_racer_car (race_id, racer_id, car_id, result_time)
            VALUES(1,3,3,110);
INSERT INTO race_racer_car (race_id, racer_id, car_id, result_time)
            VALUES(1,2,2,120);
INSERT INTO race_racer_car (race_id, racer_id, car_id, result_time)
            VALUES(1,1,1,130);

INSERT INTO race_racer_car (race_id, racer_id, car_id, result_time)
            VALUES(2,3,3,110);
INSERT INTO race_racer_car (race_id, racer_id, car_id, result_time)
            VALUES(2,2,2,120);
INSERT INTO race_racer_car (race_id, racer_id, car_id, result_time)
            VALUES(2,1,1,130);

INSERT INTO users (username, password)
            VALUES('alex', '$2a$12$EVgqzaAW5le32On8jjEW1.IseoMv.JQyyZw.uq2IxLr8zDpMFoCdm');