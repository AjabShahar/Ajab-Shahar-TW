TO RUN/SET-UP THE APP:
======================
Ensure you have java 1.8 or above installed and environment variable $JAVA_HOME is also pointing to it.

#####Install maven (osx)-
```
brew install maven
```
#####Install postgres(osx)-
```
brew install postgres
```
#####Installing nodeJS (osx)

a) Using nvm:

Install nvm from: https://github.com/creationix/nvm
then run this command to install 0.10.31 version of node - nvm install 0.10.31

b) Using brew:
```
brew install node
```
Setting up postgres
-------------------

#####Commands:
```sh
 create database "platform-dev";
 create user “pg-dev” with password '';
 grant all privileges on database "platform-dev" to "pg-dev";
```
#####Description:
This will create a user "pg-dev"(with no password) and "platform-dev" DB and grant the privileges on that db to that user.

Compiling the app for server side code
--------------------------------------

#####Command:
```
mvn clean install 
```

#####Description:
This will download all java dependencies and build the application and create target/platform-1.0-SNAPSHOT.jar
This is a fat jar file which contains all the dependant jars packaged into it.

Downloading front-end and other dependencies
--------------------------------------------

#####Commands:
```
 npm install(place sudo before the command if npm is installed using brew)
 bower install
```
#####Description:
This pulls all the js dependancies, it also runs bower install in the end to pull bower dependancies.

Starting the server
-------------------

#####Commands:
```
 mvn clean install
 
 java -jar target/platform-1.0-SNAPSHOT.jar server development.yml
```
#####Description:
This starts the server with configuration specified in development.yml and uses the jar file created after maven compilation is done.

Read more about dropwizard [here]:dropwizard.io/getting-started.html

Running migrations
------------------

#####Commands:
```
mvn clean install

java -jar target/platform-1.0-SNAPSHOT.jar server development.yml
```

#####Description:
This runs the migration against the connection string specified in developer.yml file.

Generating karma test-coverage report
-------------------------------------

#####Commands:
```
cd src/test/js

sh karmatest.sh
```
#####Description:
This will generate the test-coverage report for the files specified in the karma.conf.js configuration file.

Coverage report file(index.html) location:
	Ajab-Shahar-TW ▸ src ▸ main ▸ resources ▸ assets ▸ app ▸ coverage ▸ PhantomJS 1.9.8 (Mac OS X)

Deployment tasks :
==================

Here's the box :
smb://blrfs01/TeamShares/ajabshahar/ajabshahar.box

Prerequisites:

1) install virtualbox
2) install vagrant
Vagrant installer:
smb://blrfs01/TeamShares/ajabshahar/vagrant_1.7.2.dmg
3) Vagrant file path:
smb://blrfs01/TeamShares/ajabshahar/Vagrantfile

Steps to get your vm up and running:

1) copy the .box file to your system. Lets say to
~/VMs/ajabshahar

2) copy the attached vagrantfile to the folder '~/VMs/ajabshahar'

3) Clone the repo https://github.com/AjabShahar/scripts

4) Ensure that both the repos(ajabshahar and scripts) are siblings of each other i.e under the same folder.
For eg:
~/projects/ should contain both ajabshahar repo and scripts repo.

5) Update the path to the both the repos in line no. 40 of the vagrant file i.e
config.vm.synced_folder "path to ajab-shahar repos i.e ~/projects”, "/project”

6) cd to the vagrantfile folder i.e '~/VMs/ajabshahar' and type :
vagrant box add ajabshahar ajabshahar.box

7) then run :
vagrant up

8) To ssh into the new VM, use the command:
vagrant ssh

9) The directory ‘/projects’ contains the deployed project.

10) Go inside the scripts folder and run the command:
sh deploy-ajabshahar.sh

11) deploy-ajabshahar.sh will copy the files(zip file for assets and jar file for server code) from your machine(the path configured in the vagrant file) to the VM.

12) To see your changes:
a) If they are server side changes i.e java file changes under src, then:
i. Create the jar file by using ‘mvn clean install’/
ii. Run ‘sh deploy-ajabshahar.sh’ inside the VM.

b) If they are static content changes i.e js, css and images, then:
i. Run gulp zip
ii. Run ‘sh deploy-ajabshahar.sh’ inside the VM.

​13) To see the site use ip(of the VM):9000 i.e http://192.168.33.10:9000​

14) to get hot deployment of the client code, just add the following lines in ~/.bashrc of your vm
```
alias rs='rsync -a --delete /projects/Ajab-Shahar-TW/web/app/ /var/www/ajabshahar'
```
now to sync the code just type rs after any changes you make
