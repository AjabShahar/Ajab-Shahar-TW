The application contains:

An angular js web front end : src/main/resources/assets/app
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
grant all privileges on database "platform-dev" to "pg-dev";


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

npm install
-------------------------------------------
This pulls all the js dependancies, it also runs bower install in the end to pull bower dependancies

developing server side code
---------------------------
use idea to open the project
enable hot reload code in idea settings, so that when u compile the code is hot swappedcode is hot swapped

########
Running migrations
------------------

java -jar target/platform-1.0-SNAPSHOT.jar server development.yml
This runs the migration against the connection string specified in developer.yml file

########

Coverage report file(index.html) location:
	Ajab-Shahar-TW ▸ src ▸ main ▸ resources ▸ assets ▸ app ▸ coverage ▸ PhantomJS 1.9.8 (Mac OS X)