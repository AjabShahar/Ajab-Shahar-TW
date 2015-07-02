'use strict';

thumbnailModule.directive("wordOverview", function () {
    return {
        restrict: 'E',
        scope: {
            id: '@',
            wordTransliteration: '@',
            wordTranslation: '@',
            wordIntroEnglish: '@',
            writers: '=',
            displayAjabShaharTeam: '@'
        },
        templateUrl: '/user/js/words/directives/templates/wordOverview.html',
        controller: function ($scope) {
            $scope.people = $scope.writers.length > 0 ? $scope.writers[0].name : "";
            for (var i = 1; i < $scope.writers.length - 1; i++) {
                $scope.people += ", " + $scope.writers[i].name;
            }
            if($scope.writers.length > 1){
                $scope.people += " & " + $scope.writers[$scope.writers.length - 1].name;
            }

            $scope.getPersonUrl = function(person){
                return person?AjabShahar.user.ContentUrlCreator.getUrl(person,"person"):"";
            };

            $scope.getWordUrl = function(){
                return AjabShahar.user.ContentUrlCreator.getUrl({id:$scope.id, title:$scope.wordTransliteration},"word");
            };
        }
    }
});
