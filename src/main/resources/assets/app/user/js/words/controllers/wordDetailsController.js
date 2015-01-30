var wordDetailsController = function($scope, $location, wordMapper,reflectionMapper, wordService){

    $scope.wordReflections = {"wordThumbnails":[],"reflections":[]};
    $scope.wordReflectionsCompleteInfo = {"wordCompleteInfo":[],"reflectionsCompleteInfo":[]};
    $scope.words = [];
    $scope.showVersion = true;
    $scope.wordId ='';

    $scope.init = function(){
       $scope.wordId = $location.search().id;

        wordService.getVersions($scope.wordId).then(function(result){
         $scope.words.push(result.data.word);
         $scope.wordReflections.reflections = reflectionMapper.getThumbnails(result.data.reflections);
         $scope.wordReflections.wordThumbnails = wordMapper.getThumbnails($scope.words,'');
         $scope.wordReflectionsCompleteInfo.wordCompleteInfo = wordMapper.getWordCompleteInfo($scope.words);
        });
    }();

    $scope.toggleVersion = function(){
       $scope.showVersion = !$scope.showVersion;
    }

}

wordDetailsApp.controller('wordDetailsController',['$scope','$location','wordMapper','reflectionMapper','wordService',wordDetailsController]);