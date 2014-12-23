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
            $scope.lyricsText = "";

            $scope.initializeContent = function(){
                $scope.current = {
                    "contentType":"stanza",
                    "englishTranslationText":"","englishTransliterationText":"","originalText":"",
                };
            }

            $scope.initializeContent();

            $scope.shouldShowStanzaDetails = function(){
                return $scope.current.contentType == 'stanza' ;
            }

            $scope.shouldShowCoupletDetails = function(){
                return $scope.current.contentType == 'couplet' ;
            }

            $scope.addLyricsText = function(){
                if(($scope.current.englishTranslation == "" &&
                $scope.current.englishTransliteration == "" &&
                $scope.current.original == "") && $scope.current.content == null)
                    return;
                var newElement = {};
                angular.copy($scope.current,newElement);
                $scope.lyricsData.content.push(newElement);
                $scope.initializeContent();
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
                var selectedSongIndex = $scope.lyricsData.content.indexOf($scope.selectedLyricsContent.content);
                if(selectedSongIndex<=0)
                    return;

                $scope.lyricsData.content.move(selectedSongIndex, selectedSongIndex-1);
            }

            $scope.moveItemDown = function(){
                var selectedSongIndex = $scope.lyricsData.content.indexOf($scope.selectedLyricsContent.content);
                if(selectedSongIndex>=$scope.lyricsData.content.length-1)
                    return;

                $scope.lyricsData.content.move(selectedSongIndex, selectedSongIndex+1);
            }

            $scope.showLyrics = function(){
                return $scope.lyricsData.content.length != 0;
            }
        }
    }
});