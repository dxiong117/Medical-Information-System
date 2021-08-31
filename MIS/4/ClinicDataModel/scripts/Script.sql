--<ScriptOptions statementTerminator=";"/>

CREATE TABLE drugtreatment (
		id INT8 NOT NULL,
		dosage FLOAT8,
		drug VARCHAR(255)
	);

CREATE TABLE surgery (
		id INT8 NOT NULL,
		surgerydate DATE
	);

CREATE TABLE treatment (
		id INT8 NOT NULL,
		treatment_type VARCHAR(31),
		diagnosis VARCHAR(255),
		patient_id INT8 NOT NULL,
		providers_id INT8 NOT NULL
	);

CREATE TABLE radiology_date (
		id INT8 NOT NULL,
		patient_id VARCHAR(255) NOT NULL,
		providers_id VARCHAR(255) NOT NULL,
		treatmentdates DATE
	);

CREATE TABLE provider (
		id INT8 NOT NULL,
		npi INT8,
		name VARCHAR(255)
	);

CREATE TABLE patient (
		id INT8 NOT NULL,
		patientid INT8,
		name VARCHAR(255)
	);

CREATE TABLE radiology (
		id INT8 NOT NULL,
		rid INT8
	);

CREATE UNIQUE INDEX drugtreatment_pkey ON drugtreatment (id ASC);

CREATE UNIQUE INDEX surgery_pkey ON surgery (id ASC);

CREATE UNIQUE INDEX patient_pkey ON patient (id ASC);

CREATE UNIQUE INDEX radiology_pkey ON radiology (id ASC);

CREATE UNIQUE INDEX provider_pkey ON provider (id ASC);

CREATE UNIQUE INDEX treatment_pkey ON treatment (id ASC, patient_id ASC, providers_id ASC);

ALTER TABLE drugtreatment ADD CONSTRAINT drugtreatment_pkey PRIMARY KEY (id);

ALTER TABLE treatment ADD CONSTRAINT treatment_pkey PRIMARY KEY (id, patient_id, providers_id);

ALTER TABLE radiology ADD CONSTRAINT radiology_pkey PRIMARY KEY (id);

ALTER TABLE patient ADD CONSTRAINT patient_pkey PRIMARY KEY (id);

ALTER TABLE provider ADD CONSTRAINT provider_pkey PRIMARY KEY (id);

ALTER TABLE surgery ADD CONSTRAINT surgery_pkey PRIMARY KEY (id);

ALTER TABLE treatment ADD CONSTRAINT fk_treatment_providers_id FOREIGN KEY (providers_id)
	REFERENCES provider (id);

ALTER TABLE treatment ADD CONSTRAINT fk_treatment_patient_id FOREIGN KEY (patient_id)
	REFERENCES patient (id);

