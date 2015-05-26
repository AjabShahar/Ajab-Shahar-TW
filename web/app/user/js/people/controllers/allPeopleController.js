angular.module("people").controller('allPeopleController', ['$scope','peopleService', function ($scope,peopleService) {
    $scope.classes = ['hansas','sadhus','yoginis'];

    $scope.people = [];
    $scope.numberOfPeople = 0;
    $scope.seeMore = true;
    peopleService.getPeople().success(function(response){
        var people = response.people;
        _.each(people, function (person, index) {
            $scope.people.push(new AjabShahar.ThumbnailObject(person, "person"));
        });
        $scope.numberOfPeople = $scope.people.length;
    })
}]);