'use strict';

thumbnailModule.directive("songContentDetailsMapper", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            details:'=',
            detailsService:'='
        },
        templateUrl:'/user/js/common/templates/mappers/songContentDetailsMapper.html',
        controller:function($scope){
            var getSingers = function(singers){
                if(singers.length != 0)
                {
                    var value = "" + singers[0].firstName;
                    
                    for(var index=0;index< singers.length;index++){
                        value += ' & '+singers[index].firstName;
                    }
                }
                
                return value;
            };

            $scope.song = {
            "id":$scope.details.id,
            "contentId":"song_"+$scope.details.id,
            "videoId":$scope.details.youtubeVideoId,
            "audioUrl":$scope.details.soundCloudTrackID,
            "singer":getSingers($scope.details.singers),
            "poet":$scope.details.poets[0].firstName,
            "downloadURL":$scope.details.download_url
            };
        }
    }
});