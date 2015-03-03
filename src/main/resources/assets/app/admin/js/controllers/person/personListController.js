var personListController = function($scope, contentService){
    $scope.songs = [];
    $scope.init = function(){
        contentService.getAllPeople().then(function(result){
            var allPeople = result.data.people;
            $scope.people = _.reduce(allPeople,function(people, value,index) {
                var toBeAdded={};
                toBeAdded.firstName = value.firstName;
                toBeAdded.middleName = value.middleName;
                toBeAdded.lastName = value.lastName;
                toBeAdded.roles = _.reduce(value.roles, function(memo, value, index){ return memo + ((index!=0)?', ':'')+ value; },'');
                toBeAdded.id = value.id;
                people.push(toBeAdded);
                return people;
            },[]);
        });
    };

    $scope.init();
};

adminApp.controller('personListController',['$scope','contentService',personListController]);