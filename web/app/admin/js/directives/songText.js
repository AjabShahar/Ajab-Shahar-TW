'use strict';

songsAdminApp.directive("songText", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            songText:'=',
            poets:'='
        },
        templateUrl:'/admin/js/templates/songText.html',
        controller:function($scope){
            $scope.lyricsText = "";
            $scope.selectedLyricsContent = {};
            $scope.songText.openingCouplets = [];

            $scope.initializeContent = function(){
                $scope.newContent = {
                    "contentType": "stanza",
                    "englishTranslationText": "",
                    "englishTransliterationText": "",
                    "originalText": "",
                };
                $scope.openingCouplet = {
                    "contentType": "couplet",
                    "englishTranslationText": "",
                    "englishTransliterationText": "",
                    "originalText": "",
                };
            };

            $scope.isEmpty = function(value){
                return !Boolean(value);
            }

            $scope.clearSongText = function(){
                $scope.songText.openingCouplets = [];
                $scope.songText.songTextContents = [];
            };

            $scope.shouldShowStanzaDetails = function(){
                return $scope.newContent.contentType == 'stanza' ;
            };

            $scope.shouldShowCoupletDetails = function(){
                return $scope.newContent.contentType == 'couplet' ;
            };

            $scope.addLyricsText = function(){
                if($scope.newContent.englishTranslationText == "" &&
                $scope.newContent.englishTransliterationText == "" &&
                $scope.newContent.originalText == "")
                    return;

                var englishTranslationTexts = $scope.newContent.englishTranslationText.split(/\n{2,}/g);
                var originalTexts = $scope.newContent.originalText.split(/\n{2,}/g);
                var englishTransliterationTexts = $scope.newContent.englishTransliterationText.split(/\n{2,}/g);

                var totalIterations = Math.max(englishTranslationTexts.length, originalTexts.length, englishTransliterationTexts.length);

                for(var i = 0 ; i < totalIterations; i++){

                    var newElement = {
                        contentType: "",
                        sequenceNumber: "",
                        englishTranslationText: "",
                        englishTransliterationText: "",
                        originalText: "",
                        poet: ""
                    };

                    newElement.contentType = $scope.newContent.contentType;
                    newElement.sequenceNumber = $scope.getSongContents().length;

                    newElement.englishTranslationText = englishTranslationTexts[i];
                    newElement.englishTransliterationText = englishTransliterationTexts[i];
                    newElement.originalText = originalTexts[i];

                    newElement.poet = $scope.newContent.poet;

                    $scope.getSongContents().push(newElement);
                    $scope.selectedLyricsContent = newElement;
                    $scope.initializeContent();
                }
            };

            $scope.addOpeningCouplet = function(){
               if(($scope.openingCouplet.englishTranslationText == "" &&
                   $scope.openingCouplet.englishTransliterationText == "" &&
                   $scope.openingCouplet.originalText == ""))
                           return;
               $scope.openingCouplet.sequenceNumber = $scope.songText.openingCouplets.length;
               $scope.songText.openingCouplets.push($scope.openingCouplet);
               $scope.initializeContent();
            };

            $scope.getSongContents = function(){
                if($scope.songText.songTextContents==null)
                    $scope.songText.songTextContents = [];
                return $scope.songText.songTextContents;
            };

            $scope.showLyrics = function(){
                return $scope.getSongContents().length != 0;
            };

            $scope.initializeContent();
        }
    };
});
