'use strict';

wordsAdminApp.directive("adminWordIntroduction", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            wordIntroductions:'=',
        },
        templateUrl:'/admin-js/templates/adminWordIntroduction.html',
        controller:function($scope){
            $scope.addToWordIntroduction = function(){
                var introTextOriginals = $scope.introTextOriginal.split(/\n{2,}/g);
                var introTextTranslations = $scope.introTextTranslation.split(/\n{2,}/g);
                var introTextTransliterations = $scope.introTextTransliteration.split(/\n{2,}/g);
                var total =  introTextOriginals.length>0 ? introTextOriginals.length :
                    (introTextTranslations.length>0? introTextTranslations.length : introTextTransliterations.length);
                for(var i=0; i < total; i++){
                    var wordIntroduction = {};
                    wordIntroduction.introTextOriginal ="<p>" + introTextOriginals[i].split("\n").join("<br />") +"</p>";
                    wordIntroduction.introTextTranslation ="<p>" + introTextTranslations[i].split("\n").join("<br />") +"</p>";
                    wordIntroduction.introTextTransliteration ="<p>" + introTextTransliterations[i].split("\n").join("<br />") +"</p>";
                    $scope.wordIntroductions.push(wordIntroduction)
                }
                $scope.wordIntroduction = "";
            };
        }
    }
});