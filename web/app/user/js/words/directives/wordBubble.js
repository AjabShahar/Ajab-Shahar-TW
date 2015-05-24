'use strict';

angular.module("word").directive("wordBubble", function () {
    return {
        restrict: 'E',
        scope: {
            id: '@',
            wordTransliteration: '@',
            wordTranslation: '@',
            transliterationIntro: '@'
        },
        templateUrl: '/user/js/words/directives/templates/wordBubble.html',
        controller: function ($scope, $rootScope) {
            $scope.textRepresentation = 'Transliteration';
            $scope.primaryTitle = $scope.wordTransliteration;
            $scope.secondaryTitle = $scope.wordTranslation;

            $scope.wordDetailUrl = "details/" + $scope.id;
            var setTitles = function(){
                $scope.primaryTitle =   ($scope.textRepresentation === 'Transliteration') ? $scope.wordTransliteration : $scope.wordTranslation;
                $scope.secondaryTitle =($scope.textRepresentation === 'Transliteration') ? $scope.wordTranslation : $scope.wordTransliteration;
            };

            $rootScope.$watch('contentTextRepresentation', function ( value) {
                $scope.textRepresentation = value;
                setTitles();
            });
        }
    }
});
