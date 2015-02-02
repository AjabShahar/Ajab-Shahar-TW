'use strict';

thumbnailModule.directive("wordOverview", function() {
    return {
        restrict: 'E',
        scope: {
            id:'@',
            wordTransliteration:'@',
            wordTranslation:'@',
            wordIntroEnglish:'@',
            writers:'@',
        },
        templateUrl:'/user-js/common/templates/words/wordOverview.html',
        controller: function($scope) {
           $scope.people = "";
           angular.forEach($scope.writers,function(writer){
              $scope.people += " " + writer.englishName ;
           })
        }
    }
});