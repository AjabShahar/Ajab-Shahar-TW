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
                $scope.wordIntroductions = [];
                var introTextOriginals = $scope.wordIntroHindi !=null ? $scope.wordIntroHindi.split(/\n{2,}/g) : [];
                var introTextTranslations = $scope.wordIntroEnglish !=null ? $scope.wordIntroEnglish.split(/\n{2,}/g) : [];
                var total =  introTextOriginals.length > 0 ? introTextOriginals.length : introTextTranslations.length;
                for(var i=0; i < total; i++){
                    var wordIntroduction = {};
                    wordIntroduction.wordIntroHindi = introTextOriginals.length >0 ? "<p>" + introTextOriginals[i].split("\n").join("<br />") +"</p>" : "";
                    wordIntroduction.wordIntroEnglish = introTextTranslations.length >0 ?"<p>" + introTextTranslations[i].split("\n").join("<br />") +"</p>" : "";
                    $scope.wordIntroductions.push(wordIntroduction);
                }
                $scope.wordIntroduction = "";
            };
        }
    };
});