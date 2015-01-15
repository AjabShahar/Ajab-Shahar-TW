var wordsEditorsChoiceController = function($scope,contentService, $location, $window){
	$scope.init = function(){
        contentService.getWordsLandingPageContent().then(function(result){
            $scope.words = result.data;
        });
	}

	$scope.init();
}

editorsChoiceApp.controller('songsEditorsChoiceController',['$scope','contentService','$location', '$window',wordsEditorsChoiceController]);