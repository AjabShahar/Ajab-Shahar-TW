var wordListController = function($scope, contentService){
    $scope.words = [];
    $scope.init = function(){
        contentService.getAllWords().then(function(result){
            var allWords = result.data;
            $scope.words = _.reduce(allWords,function(words, value,index) {
                var toBeAdded={};
                toBeAdded.wordOrPhase = value.wordOrPhase;
                toBeAdded.meaning = value.meaning;
                toBeAdded.id = value.id;
                words.push(toBeAdded);
                return words;
            },[])
        });
    }

    $scope.init();
}

adminApp.controller('wordListController',['$scope','contentService',wordListController]);