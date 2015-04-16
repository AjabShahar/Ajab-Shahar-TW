'use strict';

filterModule.directive("asTitle", function () {
    return {
        restrict: 'E',
        replace: true,
        transclude: true,
        scope: {
            filterClicked: "=",
            clearFilters: "="
        },
        templateUrl: '/user/js/common/templates/common/asTitle.html',
        controller: function ($scope, $rootScope) {
            $rootScope.contentTextRepresentation = 'Transliteration';
            $scope.isOriginalTitle = true;
            $scope.isEnglishTitle = false;
            $scope.alphabetFilters = [
                {alphabet: 'ALL', isSelected: true},
                {alphabet: 'A', isSelected: false},
                {alphabet: 'B', isSelected: false},
                {alphabet: 'C', isSelected: false},
                {alphabet: 'D', isSelected: false},
                {alphabet: 'E', isSelected: false},
                {alphabet: 'F', isSelected: false},
                {alphabet: 'G', isSelected: false},
                {alphabet: 'H', isSelected: false},
                {alphabet: 'I', isSelected: false},
                {alphabet: 'J', isSelected: false},
                {alphabet: 'K', isSelected: false},
                {alphabet: 'L', isSelected: false},
                {alphabet: 'M', isSelected: false},
                {alphabet: 'N', isSelected: false},
                {alphabet: 'O', isSelected: false},
                {alphabet: 'P', isSelected: false},
                {alphabet: 'Q', isSelected: false},
                {alphabet: 'R', isSelected: false},
                {alphabet: 'S', isSelected: false},
                {alphabet: 'T', isSelected: false},
                {alphabet: 'U', isSelected: false},
                {alphabet: 'V', isSelected: false},
                {alphabet: 'W', isSelected: false},
                {alphabet: 'X', isSelected: false},
                {alphabet: 'Y', isSelected: false},
                {alphabet: 'Z', isSelected: false}];

            $scope.selectOriginalTitle = function () {
                if ($rootScope.contentTextRepresentation === 'Transliteration')
                    return;
                $rootScope.contentTextRepresentation = 'Transliteration';
                $scope.isOriginalTitle = true;
                $scope.isEnglishTitle = false;
                $scope.clearAlphabetFilter();
                //$rootScope.$broadcast('contentTextRepresentation', $rootScope.contentTextRepresentation);
            };

            $scope.selectEnglishTitle = function () {
                if ($rootScope.contentTextRepresentation === 'Translation')
                    return;
                $rootScope.contentTextRepresentation = 'Translation';
                $scope.isEnglishTitle = true;
                $scope.isOriginalTitle = false;
                $scope.clearAlphabetFilter();
                //$rootScope.$broadcast('contentTextRepresentation', $rootScope.contentTextRepresentation);
            };

            $scope.clearAlphabetFilter = function () {
                _.each($scope.alphabetFilters, function (alphabetFilter) {
                    alphabetFilter.isSelected = false;
                });
                $scope.currentAlphabetFilter = '';
                $scope.clearFilters();
            };

            $scope.filterSongsOnLetter = function (currentAlphabetFilter) {
                $scope.clearAlphabetFilter();
                currentAlphabetFilter.isSelected = true;
                $scope.currentAlphabetFilter = currentAlphabetFilter;

                var filter = angular.extend({}, currentAlphabetFilter);
                filter.contentTextRepresentation = $rootScope.contentTextRepresentation;
                if (currentAlphabetFilter.alphabet.toLowerCase() === 'all') {
                    filter.alphabet = "";
                }
                $scope.filterClicked(filter);
            };

            $scope.isSelected = function (alphabet) {
                return alphabet.isSelected ? 'active' : '';
            };
        }
    }
});
