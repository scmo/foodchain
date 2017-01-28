INSERT INTO farm (id, name, description, address, latitude, longitude, website, picture)
	VALUES (1, 'Biohof Schmid', 'Unser Landwirtschaftsbetrieb wird seit 1996 nach den Richtlinien des biologischen Landbaus geführt.', 'Oberfeld 1, 6284 Gelfinfen', 47.21071, 8.27251, 'http://www.bioschmid.ch/', 'http://i.imgur.com/z7FHyJ3.jpg'),
	(2, 'Blumenthal', 'Der schönste Biobauer der Schweiz.', 'Survitg 246D, 7144 Vella', 46.72303, 9.16064, 'http://www.renzo-blumenthal.ch/', 'http://i.imgur.com/AryZ9KO.jpg');


INSERT INTO cow (id, name, breed, birthdate, description, farm_id, animal_id, picture)
VALUES
	(1, 'Elsa', 'Charolais', '2015-10-10', 'Will be great for Salsiccia', 2, 'elsa', 'http://i.imgur.com/B4Q68pa.jpg'),
	(2, 'Olga', 'Angus', '2015-07-28', 'Does not like hey', 1, 'olga', 'http://i.imgur.com/FlUQCj0.jpg');


INSERT INTO movement_measurement (cow_id, nr_of_steps, step_types, duration_in_seconds, time_end)
VALUES
	(1, 31, 1, 1331, '2016-10-11'),
	(1, 313, 0, 2223, '2016-10-12'),
	(1, 118, 1, 2225, '2016-10-13'),
	(1, 21, 0, 2312, '2016-10-14'),
	(1, 276, 1, 131, '2016-10-15'),
	(1, 63, 0, 2124, '2016-10-16'),
	(1, 98, 1, 232, '2016-10-17'),
	(2, 31, 1, 131, '2017-01-23'),
	(2, 313, 0, 21223, '2017-01-24'),
	(2, 118, 1, 2315, '2017-01-25'),
	(2, 21, 0, 532, '2017-01-26'),
	(2, 276, 1, 431, '2017-01-27'),
	(2, 63, 0, 214, '2017-01-28');

