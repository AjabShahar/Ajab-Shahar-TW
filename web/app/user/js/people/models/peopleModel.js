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
            occupations.push(role);
        });
        return occupations;
    };

    buildFromPerson(content);
};