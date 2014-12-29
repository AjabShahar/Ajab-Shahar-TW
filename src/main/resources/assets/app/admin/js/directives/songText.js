'use strict';

songsAdminApp.directive("songText", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            songText:'=',
            coupletList:'=',
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
                newElement.sequenceNumber = $scope.songText.length;
                newElement.stanza = {};
                newElement.stanza.contentType = $scope.newContent.contentType;
                newElement.chorus = $scope.songText.chorus;
                newElement.stanza.englishTranslationText = $scope.newContent.englishTranslationText;
                newElement.stanza.englishTransliterationText = $scope.newContent.englishTransliterationText;
                newElement.stanza.originalText = $scope.newContent.originalText;

                $scope.songText.push(newElement);
                $scope.selectedLyricsContent = newElement;
                $scope.initializeContent();
            }

            $scope.showLyrics = function(){
                return $scope.songText.length != 0;
            }

            $scope.getEnglishTransliterationText = function(content){
                if(content.stanza!=null)
                     return content.stanza.englishTransliterationText;
                return content.couplet.englishTransliterationText;
            }

            $scope.getEnglishTranslationText = function(content){
                if(content.stanza!=null)
                     return content.stanza.englishTranslationText;
                return content.couplet.englishTranslationText;
            }

            $scope.getOriginalText = function(content){
                if(content.stanza!=null)
                     return content.stanza.originalText;
                return content.couplet.originalText;
            }
        }
    }
});