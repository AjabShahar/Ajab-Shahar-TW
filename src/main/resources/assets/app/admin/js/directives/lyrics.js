'use strict';

songsAdminApp.directive("lyrics", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            lyricsComponent:'=',
            originalText:'=',
        },
        templateUrl:'/admin/js/templates/lyrics.html',
        controller:function($scope){
            $scope.lyricsTextList = [];

            $scope.addLyricsText = function(){
                if($scope.lyricsText != ""){
                    $scope.lyricsTextList.push($scope.lyricsText);
                }
                $scope.lyricsText = "";
            }

            Array.prototype.insert = function (index, item) {
                this.splice(index, 0, item);
            };

            $scope.moveItemUp = function(){
                var selectedSongIndex = $scope.lyricsTextList.indexOf($scope.selectedLyricsText);
                var songToBeMovedUp = $scope.lyricsTextList.splice(selectedSongIndex, 1);

                $scope.lyricsTextList.insert(selectedSongIndex - 1, songToBeMovedUp[0].englishTransliteration);
            }

        }
    }
});