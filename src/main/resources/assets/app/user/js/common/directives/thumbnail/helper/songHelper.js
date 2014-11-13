'use strict';

thumbnailModule.directive("songHelper", function(nameService) {
    return {
        restrict: 'E',
        scope: {
            details:'=',
            customStyle:'@',
            showDetailsService:'='
        },
        template:'<song id="{{song.id}}"'+
                           ' open="open()"'+
                           ' custom-style="{{song.customStyle}}"' +
                           ' img-src="{{song.imgSrc}}"'+
                           ' url="{{song.videoId}}"'+
                           ' name="{{song.englishTranslation}}"'+
                           ' category-name="{{song.categoryName}}"'+
                           ' duration="{{song.duration}}"'+
                           ' singer="{{song.singer}}"' +
                           ' class = "songs"' +
                           ' poet="{{song.poet}}">' +
                           '</song>',
        controller:function($scope){
            $scope.song = {"id":"song_"+$scope.details.id, "customStyle":$scope.customStyle,"imgSrc":$scope.details.thumbnail_url,"videoId":$scope.details.youtubeVideoId,"englishTranslation":$scope.details.songTitle.englishTranslation,
                            "categoryName":$scope.details.songCategory.name, "duration":$scope.details.duration, "singer":nameService.getName($scope.details.singers[0]) ,
                            "poet":nameService.getName($scope.details.poets[0])
                        };
            $scope.open = function(){
                return $scope.showDetailsService.open($scope.song.id);
            }
        }
    }
});