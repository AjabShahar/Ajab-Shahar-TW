The application contains:

An angular js web front end : src/main/resources/assets
Dropwizard REST api

STEPS TO RUN/SET-UP THE APP:
============================

install maven - brew install maven (osx)
install nvm - https://github.com/creationix/nvm
install nodejs using nvm - nvm install 0.10.31
install postgres - brew install postgres (osx) 

Setting up postgres
-------------------
create user pg-dev(no password) with ability to create databases
create a platform-dev database


mvn clean install 
-----------------
This will download all java dependencies and build the application
and create target/platform-1.0-SNAPSHOT.jar
This is a uber jar which contains all the dependant jars packaged into it

java -jar target/platform-1.0-SNAPSHOT.jar server development.yml
-----------------------------------------------------------------
this starts the server with configuration specified in development.yml

read about dropwizard here - http://dropwizard.io/getting-started.html
----------------------------

cd src/main/resources/assets && npm install
-------------------------------------------
This pulls all the js dependancies, it also runs bower install in the end to pull bower dependancies

developing client side code
---------------------------
npm test | run tests
npm start | start the development server
npm run-script dev |  start in dev-mode which will keep running tests as code is edited 
see package.json for script implementations

developing server side code
---------------------------
use idea to open the project
enable hot reload code in idea settings, so that when u compile the code is hot swappedcode is hot swapped

########

Rolling back your migration scripts
-----------------------------------

"To roll back change sets which have already been applied, run the db rollback command. You will need to specify either a tag, a date, or a number of change sets to roll back to."
Link - http://dropwizard.readthedocs.org/en/latest/manual/migrations.html

In case you get an error like this - "No inverse to liquibase.change.core.RawSQLChange created"

You need the fix this via adding a rollback comment to each of your scripts, like this:
–liquibase formatted sql
–changeset henkbl:PROD111111
CREATE VIEW all_employees AS
SELECT *
FROM employee;
–rollback DROP VIEW all_employees;

Testing rolling back scripts
----------------------------
​To test your migration whether the syntax is correct for them to rollback, run the command:
java -jar target/platform-1.0-SNAPSHOT.jar db test development.yml​
