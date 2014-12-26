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
            $scope.selectedLyricsContent = {};

            $scope.initializeContent = function(){
                $scope.newContent = {
                    "contentType":"stanza",
                    "englishTranslationText":"","englishTransliterationText":"","originalText":"",
                };
            }

            $scope.initializeContent();

            $scope.shouldShowStanzaDetails = function(){
                return $scope.newContent.contentType == 'stanza' ;
            }

            $scope.shouldShowCoupletDetails = function(){
                return $scope.newContent.contentType == 'couplet' ;
            }

            $scope.addLyricsText = function(){
                if(($scope.newContent.englishTranslationText == "" &&
                $scope.newContent.englishTransliterationText == "" &&
                $scope.newContent.originalText == "") && $scope.newContent.content == null)
                    return;
                var newElement = {};
                newElement.contentType = $scope.newContent.contentType;
                newElement.sequenceNumber = $scope.lyricsData.length;
                if($scope.newContent.contentType == 'stanza'){
                    newElement.stanza = {};
                    newElement.chorus = $scope.lyricsData.chorus;
                    newElement.stanza.englishTranslationText = $scope.newContent.englishTranslationText;
                    newElement.stanza.englishTransliterationText = $scope.newContent.englishTransliterationText;
                    newElement.stanza.originalText = $scope.newContent.originalText;
                }
                if($scope.newContent.contentType == 'couplet'){
                    newElement.couplet = $scope.newContent.content;
                    newElement.chorus = $scope.lyricsData.chorus;
                }
                $scope.lyricsData.push(newElement);
                $scope.selectedLyricsContent = newElement;
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
                var selectedSongIndex = $scope.lyricsData.indexOf($scope.selectedLyricsContent);
                if(selectedSongIndex<=0)
                    return;

                $scope.lyricsData.move(selectedSongIndex, selectedSongIndex-1);
//                _.each($scope.lyricsData, function(){});
            }

            $scope.moveItemDown = function(){
                var selectedSongIndex = $scope.lyricsData.indexOf($scope.selectedLyricsContent);
                if(selectedSongIndex>=$scope.lyricsData.length-1)
                    return;

                $scope.lyricsData.move(selectedSongIndex, selectedSongIndex+1);
            }

            $scope.showLyrics = function(){
                return $scope.lyricsData.length != 0;
            }

            $scope.getLyricsForDisplay = function(content){
                if(content.stanza!=null)
                     return content.stanza.englishTransliterationText;
                return content.couplet.englishTranslationText;
//                if(content.contentType=='stanza')
//                    return content.stanza.englishTransliterationText;
//                return content.couplet.englishTransliterationText;
            }
        }
    }
});