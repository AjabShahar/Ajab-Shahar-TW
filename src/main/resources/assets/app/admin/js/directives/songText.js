'use strict';

songsAdminApp.directive("songText", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            songText:'=',
            poets:'=',
        },
        templateUrl:'/admin/js/templates/songText.html',
        controller:function($scope){
            $scope.lyricsText = "";
            $scope.selectedLyricsContent = {};

            $scope.initializeContent = function(){
                $scope.newContent = {
                    "contentType":"stanza",
                    "englishTranslationText":"","englishTransliterationText":"","originalText":"",
                };

                if($scope.songText.songTextContents==null)
                    $scope.songText.songTextContents = [];
            }

            $scope.initializeContent();

            $scope.shouldShowStanzaDetails = function(){
                return $scope.newContent.contentType == 'stanza' ;
            }

            $scope.shouldShowCoupletDetails = function(){
                return $scope.newContent.contentType == 'couplet' ;
            }

            $scope.edit = function(songText){
            }

            $scope.addLyricsText = function(){
                if(($scope.newContent.englishTranslationText == "" &&
                $scope.newContent.englishTransliterationText == "" &&
                $scope.newContent.originalText == "") && $scope.newContent.content == null)
                    return;
                var newElement = {};
                newElement.contentType = $scope.newContent.contentType;
                newElement.sequenceNumber = $scope.songText.songTextContents.length;

                newElement.englishTranslationText = $scope.newContent.englishTranslationText;
                newElement.englishTransliterationText = $scope.newContent.englishTransliterationText;
                newElement.originalText = $scope.newContent.originalText;
                newElement.poet = $scope.newContent.poet;

                $scope.songText.songTextContents.push(newElement);
                $scope.selectedLyricsContent = newElement;
                $scope.initializeContent();
            }

            $scope.showLyrics = function(){
                return $scope.songText.songTextContents.length != 0;
            }

        }
    }
});