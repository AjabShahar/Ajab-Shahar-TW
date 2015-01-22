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
                var introTextOriginals = $scope.wordIntroHindi.split(/\n{2,}/g);
                var introTextTranslations = $scope.wordIntroEnglish.split(/\n{2,}/g);
                var total =  introTextOriginals.length>0 ? introTextOriginals.length : introTextTranslations.length;
                for(var i=0; i < total; i++){
                    var wordIntroduction = {};
                    wordIntroduction.wordIntroHindi ="<p>" + introTextOriginals[i].split("\n").join("<br />") +"</p>";
                    wordIntroduction.wordIntroEnglish ="<p>" + introTextTranslations[i].split("\n").join("<br />") +"</p>";
                    $scope.wordIntroductions.push(wordIntroduction)
                }
                $scope.wordIntroduction = "";
            };
        }
    }
});