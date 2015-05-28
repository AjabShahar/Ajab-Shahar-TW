angular.module("people").controller('allPeopleController', ['$scope', 'peopleService', function ($scope, peopleService) {
    $scope.classes = ['hansas', 'sadhus', 'yoginis'];

    $scope.people = [];
    $scope.numberOfPeople;
    $scope.init = function () {
        peopleService.getPeople().success(function (response) {
            var people = response.people;
            _.each(people, function (person, index) {
                $scope.people.push(new AjabShahar.DetailsObject(person, "person"));
            });
            $scope.numberOfPeople = $scope.people.length;
        });
    };
    $scope.init();

}]);