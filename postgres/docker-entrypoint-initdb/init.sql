CREATE DATABASE security_database;

\connect security_database;

CREATE ROLE security_admin WITH
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'postgres_security';
	
CREATE SCHEMA security_schema
	AUTHORIZATION security_admin;
	
GRANT ALL ON DATABASE security_database TO security_admin;
ALTER DATABASE security_database OWNER TO security_admin;