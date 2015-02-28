'use strict';

reflectionsAdminApp.directive("reflectionTranscript", function() {
    return {
        restrict: 'E',
        scope: {
           reflectionTranscripts : '=',
        },
        templateUrl:'/admin-js/templates/reflectionTranscript.html',
        controller:function($scope){
          $scope.addToReflectionTranscript = function(){
             var hindiTranscript = $scope.hindiTranscript !=null ? $scope.hindiTranscript.split(/\n{2,}/g) : [];
             var englishTranscript = $scope.englishTranscript !=null ? $scope.englishTranscript.split(/\n{2,}/g) : [];
             var total =  hindiTranscript.length > 0 ? hindiTranscript.length : englishTranscript.length;
             for(var i=0; i < total; i++){
               var reflectionTranscript = {};
               reflectionTranscript.hindiTranscript = hindiTranscript.length >0 ? "<p>" + hindiTranscript[i].split("\n").join("<br />") +"</p>" : "";
               reflectionTranscript.englishTranscript = englishTranscript.length >0 ?"<p>" + englishTranscript[i].split("\n").join("<br />") +"</p>" : "";
                $scope.reflectionTranscripts.push(reflectionTranscript);
             }
             $scope.reflectionTranscript = "";
          };
        }
    };
});