var wordDetailsController = function($scope, $location, wordMapper, wordService){

    $scope.wordVersions = {"wordThumbnails":[]};
    $scope.words = [];
    $scope.wordId ='';

    $scope.init = function(){
       $scope.wordId = $location.search().id;

        wordService.getVersions($scope.wordId).then(function(result){
         $scope.words = result.data.words;
         $scope.wordVersions.wordThumbnails = wordMapper.getThumbnails($scope.words,'');
        });
    }();

}

wordDetailsApp.controller('wordDetailsController',['$scope','$location','wordMapper','wordService',wordDetailsController]);