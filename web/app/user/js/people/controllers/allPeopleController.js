angular.module("people").controller('allPeopleController', ['$scope', 'peopleService', '$location', '$anchorScroll', function ($scope, peopleService, $location, $anchorScroll) {
    $scope.classes = ['hansas', 'sadhus', 'yoginis'];

    $scope.people = [];
    $scope.allPeople = [];
    $scope.expandFilter = false;
    var currentSelection ="";
    $scope.currentAlphabetSelection="ALL";
    $scope.alphabetFilters = ['ALL', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

    $scope.init = function () {
        peopleService.getPeople().success(function (response) {
            var people = response.people;
            _.each(people, function (person, index) {
                $scope.people.push(new AjabShahar.peopleModel(person));
            });
            $scope.allPeople = angular.copy($scope.people);
            updateFilterCount();
        });
    };

    $scope.criteriaList = AjabShahar.user.PeopleFilterConfig.filterCategories;
    var sieve = new AjabShahar.user.Sieve($scope.criteriaList);

    $scope.applyAlphabetFilter = function (letter) {
        clearExistingFilters();
        if (letter === "ALL")
            sieve.removeFilterCriteria("name");
        else
            sieve.setFilterCriteria("name", letter);
        $scope.currentAlphabetSelection = letter;
        $scope.people = sieve.filter($scope.allPeople);
        updateFilterCount();
    };

    $scope.filterBy = function(occupation){
        clearExistingFilters();
        currentSelection = occupation;
        sieve.setFilterCriteria("occupations[]", occupation);
        $scope.people = sieve.filter($scope.allPeople);
        updateFilterCount();
    };

    $scope.resetFilters = function(){
        clearExistingFilters();
        $scope.people = $scope.allPeople;
        updateFilterCount();
    };

    $scope.scrollTo = function () {
        $location.hash($location.hash());
        $anchorScroll();
        //$('body').scrollTo('#' + $location.hash());
    };

    $scope.toggleExpandFilter = function () {
        $scope.expandFilter = !$scope.expandFilter;

    };

    $scope.isActive = function(criterion){
        return currentSelection === criterion;
    };

    var clearExistingFilters = function(){
        sieve.clearFilters();
        currentSelection = "";
        $scope.currentAlphabetSelection="ALL";
    };

    var updateFilterCount = function(){
        $scope.numberOfPeople = $scope.people.length;
    };
    $scope.init();
}]);