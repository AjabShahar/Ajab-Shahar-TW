'use strict';

thumbnailModule.directive("songThumbnailMapper", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            details:'=',
            customStyle:'@',
            showDetailsService:'='
        },
        templateUrl:'/user/js/common/templates/mappers/songThumbnailMapper.html',
        controller:function($scope){
            $scope.song = {
                "id":$scope.details.id,
                "contentId":'song_'+$scope.details.id,
                "customStyle":$scope.customStyle,
                "englishTranslationTitle":$scope.details.englishTranslationTitle,
                "englishTransliteration":$scope.details.englishTransliterationTitle,
                "category":$scope.details.category,
                "duration":$scope.details.duration,
                "singer":$scope.details.singers[0].name +($scope.details.singers[1]!=null ? ' ......':''),
                "poet":($scope.details.poet==null || $scope.details.poet.length==0)?'Unknown': $scope.details.poet[0].name,
                "thumbnailUrl":$scope.details.thumbnailUrl
            };
            $scope.open = function(){
                return $scope.showDetailsService.open($scope.song.id);
            }
        }
    }
});