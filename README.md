This is the AjabShahar Platform

It contains

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
enable hot reload code in idea settings, so that when u compile the code is hot swapped