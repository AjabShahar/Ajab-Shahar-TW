var wordDetailsController = function($scope, $location, wordMapper, wordService){

    $scope.wordVersions = {"wordThumbnails":[],"wordCompleteInfo":[]};
    $scope.words = [];
    $scope.showVersion = true;
    $scope.wordId ='';

    $scope.init = function(){
       $scope.wordId = $location.search().id;

        wordService.getVersions($scope.wordId).then(function(result){
         $scope.words = result.data.words;
         $scope.wordVersions.wordThumbnails = wordMapper.getThumbnails($scope.words,'');
         $scope.wordVersions.wordCompleteInfo = wordMapper.getWordCompleteInfo($scope.words);
        });
    }();

    $scope.toggleVersion = function(){
       $scope.showVersion = !$scope.showVersion;
    }

}

wordDetailsApp.controller('wordDetailsController',['$scope','$location','wordMapper','wordService',wordDetailsController]);