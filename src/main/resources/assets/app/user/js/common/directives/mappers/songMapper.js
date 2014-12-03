'use strict';

thumbnailModule.directive("songMapper", function(nameService) {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            details:'=',
            customStyle:'@',
            showDetailsService:'='
        },
        templateUrl:'/user/js/common/templates/mappers/songMapper.html',
        controller:function($scope){
            $scope.song = {
                "id":"song_"+$scope.details.id,
                "customStyle":$scope.customStyle,
                "imgSrc":$scope.details.thumbnail_url,
                "videoId":$scope.details.youtubeVideoId,
                "englishTransliteration":$scope.details.songTitle.englishTransliteration,
                "categoryName":$scope.details.songCategory.name,
                "duration":$scope.details.duration,
                "singer":nameService.getName($scope.details.singers[0]) ,
                "poet":nameService.getName($scope.details.poets[0])
            };
            $scope.open = function(){
                return $scope.showDetailsService.open($scope.song.id);
            }
        }
    }
});