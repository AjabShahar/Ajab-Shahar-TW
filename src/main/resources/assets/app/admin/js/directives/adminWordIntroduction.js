'use strict';

wordsAdminApp.directive("adminWordIntroduction", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            wordIntroduction:'@',
            wordIntroductions:'=',
        },
        templateUrl:'/admin-js/templates/adminWordIntroduction.html',
        controller:function($scope){
            $scope.addToWordIntroduction = function(){
                var wordIntroductions = $scope.wordIntroduction.split(/\n{2,}/g);
                for(var i=0; i < wordIntroductions.length; i++){
                    var wordIntroduction = {};
                    wordIntroduction.introduction_text ="<p>" + wordIntroductions[i].split("\n").join("<br />") +"</p>";
                    $scope.wordIntroductions.push(wordIntroduction)
                }
                $scope.wordIntroduction = "";
            };
        }
    }
});