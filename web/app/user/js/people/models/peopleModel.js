'use strict';

var AjabShahar = AjabShahar || {};


AjabShahar.peopleModel = function (content) {
    var self = this;

    var buildFromPerson = function (person) {
        if (!_.isEmpty(person)) {
            self.id = person.id;
            self.name = getName(person);
            self.occupations = getOccupations(person);
            self.thumbnailImg = person.thumbnailURL;
            self.profile = person.profile;
        }
    };
    var getName = function (person) {
        var name = person.firstName;
        if (!_.isEmpty(person.middleName))
            name = name + ' ' + person.middleName;
        if (!_.isEmpty(person.lastName))
            name = name + ' ' + person.lastName;
        return name;
    };
    var getOccupations = function(person){
        var occupations = [];
        if(!_.isEmpty(person.primaryOccupation) && !_.isEmpty(person.primaryOccupation.name)){
            occupations.push(person.primaryOccupation.name);
        }
        angular.forEach(person.roles,function(role){
            if(role.indexOf("_") != 0)
               occupations.push(role);
        });
        return occupations;
    };

    var isMetaOccupation = function(occupation){
        return occupation ? occupation.indexOf("_") === 0 : false;
    };

    self.getOccupationsAsString = function(){
        var occupations =_.reject( self.occupations,function(occupation){
            return isMetaOccupation(occupation);
        });
        return occupations.join(", ");
    };

    buildFromPerson(content);
};