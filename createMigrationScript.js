'use strict';

// Usage: type the following in terminal
// node createMigrationScript.js 'migration script that needs to be created' 'author name'
// E.g:
// node createMigrationScript.js CREATE_GENRE_TABLE Jaideep

var fs = require("fs"),
    migrationsDir = './src/main/resources/migrations/',
    args = process.argv.slice(2);

var fileName = args[0];
var authorName = args[1];

var pad = function(number, width) {
  var prefix = '0';
  number = number + '';
  return number.length >= width ? number : new Array(width - number.length + 1).join(prefix) + number;
}

var getFiles = function(dir,files_){
    files_ = files_ || [];
    if (typeof files_ === 'undefined') files_=[];
    var files = fs.readdirSync(dir);
    for(var i in files){
        if (!files.hasOwnProperty(i)) continue;
        var name = dir+'/'+files[i];
        if (fs.statSync(name).isDirectory()){
            getFiles(name,files_);
        } else {
            var fileName = name.replace(dir + '/', "");
            files_.push(fileName);
        }
    }
    return files_;
}

var createNewMigrationFile = function(authorName, newChangeset, fileName){
    var liquibaseText = "--liquibase formatted sql \n\n--changeset "+ authorName + ":" + newChangeset + "\n\n";

    var newFile =  migrationsDir + pad(newChangeset,4) + "_" + fileName + ".sql";

    fs.writeFile(newFile, liquibaseText, function (err) {
        if (err) throw err;
        console.log(liquibaseText);
    });
}

var getNewChangetSetNumber = function(fileName) {
    var oldChangeSetNumber = parseInt(fileName.substr(0, fileName.indexOf('_')))
    return oldChangeSetNumber + 1;
}

var files = getFiles(migrationsDir);

var latestMigrationFileName = files[files.length - 1];

var newChangeset = getNewChangetSetNumber(latestMigrationFileName);

(Boolean(authorName)) ? authorName = authorName : authorName = "AuthorName";

createNewMigrationFile(authorName, newChangeset, fileName)