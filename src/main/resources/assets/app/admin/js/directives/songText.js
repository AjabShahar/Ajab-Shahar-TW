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
            $scope.songText.openingCouplets = [];

            $scope.initializeContent = function(){
                $scope.newContent = {
                    "contentType":"stanza",
                    "englishTranslationText":"","englishTransliterationText":"","originalText":"",
                };
                $scope.openingCouplet = {
                    "contentType":"couplet",
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

            // var getParagraphFromText = function(){
            //     var englishTransliterationText = $scope.newContent.englishTransliterationText;
            //     var totalParagraphInEnglishTranlisteraion = (englishTransliterationText.match(/\n\n/g) || []).length;

            //     var englishTranslationText = $scope.newContent.englishTranslationText;
            //     var totalParagraphInEnglishTranslation = (englishTranslationText.match(/\n\n/g) || []).length;

            //     var originalText = $scope.newContent.originalText;
            //     var totalParagraphInOriginal = (originalText.match(/\n\n/g) || []).length;
            // }

            $scope.addLyricsText = function(){
                if(($scope.newContent.englishTranslationText == "" &&
                $scope.newContent.englishTransliterationText == "" &&
                $scope.newContent.originalText == "") && $scope.newContent.content == null)
                    return;
                var newElement = {};
                newElement.contentType = $scope.newContent.contentType;


                var englishTranslationTexts = $scope.newContent.englishTranslationText.split(/\n{2,}/g);
                var originalTexts = $scope.newContent.originalText.split(/\n{2,}/g);
                var englishTransliterationTexts = $scope.newContent.englishTransliterationText.split(/\n{2,}/g);

                var totalIterations = Math.max(englishTranslationTexts.length, originalTexts.length, englishTransliterationTexts.length);

                for(var i = 0 ; i < totalIterations; i++){
                    var newElement = {};
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
            }

            $scope.addOpeningCouplet = function(){
               if(($scope.openingCouplet.englishTranslationText == "" &&
                   $scope.openingCouplet.englishTransliterationText == "" &&
                   $scope.openingCouplet.originalText == ""))
                           return;
               $scope.openingCouplet.sequenceNumber = $scope.songText.openingCouplets.length;
               $scope.songText.openingCouplets.push($scope.openingCouplet);
               $scope.initializeContent();
            }

            $scope.getSongContents = function(){
                if($scope.songText.songTextContents==null)
                    $scope.songText.songTextContents = [];
                return $scope.songText.songTextContents;
            }

            $scope.showLyrics = function(){
                return $scope.getSongContents().length != 0;
            }

        }
    }
});