'use strict';

songsAdminApp.directive("lyrics", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            lyricsData:'=',
            coupletList:'=',
        },
        templateUrl:'/admin/js/templates/lyrics.html',
        controller:function($scope){
            $scope.lyricsComponent = 'stanza';
            $scope.lyricsText = "";

            $scope.addLyricsText = function(){
                if($scope.lyricsText != ""){
                    $scope.lyricsData.push($scope.lyricsText);
                    $scope.selectedLyricsText = $scope.lyricsText;
                }
                $scope.lyricsText = "";
            }

            Array.prototype.move = function (old_index, new_index) {
                if (new_index >= this.length) {
                    var k = new_index - this.length;
                    while ((k--) + 1) {
                        this.push(undefined);
                    }
                }
                this.splice(new_index, 0, this.splice(old_index, 1)[0]);
                return this; // for testing purposes
            };

            $scope.moveItemUp = function(){
                var selectedSongIndex = $scope.lyricsData.indexOf($scope.selectedLyricsText);
                if(selectedSongIndex<=0)
                    return;

                $scope.lyricsData.move(selectedSongIndex, selectedSongIndex-1);
            }

            $scope.moveItemDown = function(){
                var selectedSongIndex = $scope.lyricsData.indexOf($scope.selectedLyricsText);
                if(selectedSongIndex>=$scope.lyricsData.length-1)
                    return;

                $scope.lyricsData.move(selectedSongIndex, selectedSongIndex+1);
            }

            $scope.showLyrics = function(){
                return $scope.lyricsData.length != 0;
            }
        }
    }
});