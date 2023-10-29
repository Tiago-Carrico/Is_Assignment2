--TODO add here the db initial configuration
--AKA the CREATE TABLE, foreign keys and etc.
--Basically do the same as in BD with Onda and get the db configuration text file from there

CREATE TABLE pet (
	id	 BIGSERIAL,
	name	 TEXT NOT NULL,
	species	 TEXT NOT NULL,
	birthdate DATE NOT NULL,
	weight	 FLOAT(8) NOT NULL,
	owner_id	 BIGINT NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE owner (
	id	 BIGSERIAL,
	name	 TEXT NOT NULL,
	phone INTEGER NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE test (
	id SERIAL,
	name TEXT,
	PRIMARY KEY(id)
);

ALTER TABLE pet ADD CONSTRAINT pet_fk1 FOREIGN KEY (owner_id) REFERENCES owner(id);