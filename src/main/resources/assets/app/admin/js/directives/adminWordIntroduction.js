'use strict';

wordsAdminApp.directive("adminWordIntroduction", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            wordIntroduction:'@',
            wordIntroductions:'=',
        },
        templateUrl:'/admin/js/templates/adminWordIntroduction.html',
        controller:function($scope){
            $scope.addToWordIntroduction = function(){
                var wordIntroduction = {};
                  var wordIntroductions = $scope.formInfo.wordIntroduction.split(/\n{2,}/g);
                  for(var i=0;i<wordIntroductions.length;){
                     var wordIntroduction = {};
                     wordIntroduction.introduction_text = wordIntroductions[i];
                     $scope.formInfo.wordIntroductions[i] = wordIntroduction;
                     i=i+1;
                  }
            };
        }
    }
});