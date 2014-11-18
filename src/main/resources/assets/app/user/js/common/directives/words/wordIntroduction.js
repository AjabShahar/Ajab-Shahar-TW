'use strict';

thumbnailModule.directive("wordIntroduction", function() {
    return {
        restrict: 'E',
        scope: {
            id:'@',
            originalText:'@',
            englishTranslationText:'@',
            englishTransliterationText:'@',
            poet:'@',
            closeVideo:'&'
        },
        templateUrl:'/user/js/common/templates/words/introduction.html',
        controller: function($scope) {
            $scope.$watch(function() { return $scope.closeVideo(); }, function(newValue, oldValue) {
                $scope.shouldStopVideo = !newValue;
            });
            //$scope.$on($scope.stopOn,$stopParam)
        }
    }
});