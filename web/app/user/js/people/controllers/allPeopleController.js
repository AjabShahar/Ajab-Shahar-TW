angular.module("people").controller('allPeopleController', ['$scope', 'peopleService','$location', '$anchorScroll', function ($scope, peopleService,$location, $anchorScroll) {
    $scope.classes = ['hansas', 'sadhus', 'yoginis'];

    $scope.people = [];
    $scope.expandFilter = false;
    $scope.numberOfPeople;
    $scope.init = function () {
        peopleService.getPeople().success(function (response) {
            var people = response.people;
            _.each(people, function (person, index) {
                $scope.people.push(new AjabShahar.peopleModel(person));
            });
            $scope.numberOfPeople = $scope.people.length;
        });
    };

    $scope.scrollTo = function(){
        $location.hash($location.hash());
        $anchorScroll();
        //$('body').scrollTo('#' + $location.hash());
    };

    $scope.toggleExpandFilter = function () {
            $scope.expandFilter = !$scope.expandFilter;
            
    };

    $scope.init();
}]);