INSERT INTO farm (id, name, description, address, latitude, longitude, website, picture)
	VALUES (1, 'Biohof Schmid', 'Unser Landwirtschaftsbetrieb wird seit 1996 nach den Richtlinien des biologischen Landbaus geführt.', 'Oberfeld 1, 6284 Gelfinfen', 47.21071, 8.27251, 'http://www.bioschmid.ch/', 'http://i.imgur.com/z7FHyJ3.jpg'),
	(2, 'Blumenthal', 'Der schönste Biobauer der Schweiz.', 'Survitg 246D, 7144 Vella', 46.72303, 9.16064, 'http://www.renzo-blumenthal.ch/', 'http://i.imgur.com/AryZ9KO.jpg');


INSERT INTO cow (name, breed, birthdate, description, farm_id, animal_id, picture)
VALUES
	('Elsa', 'Charolais', '2015-10-10', 'Will be great for Salsiccia', 2, 'elsa', 'http://i.imgur.com/B4Q68pa.jpg'),
	('Olga', 'Angus', '2015-07-28', 'Does not like hey', 1, 'olga', 'http://i.imgur.com/FlUQCj0.jpg');
