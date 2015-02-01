var genreDetailsController = function($scope, $window, $location, genreContentService){
	$scope.formInfo = {};
	$scope.pageName = {};

	$scope.getGenreData = function(){
		var genreId = $location.search().id;

		if(Boolean(genreId)){
			genreContentService.getGenre(genreId).success(function (data) {
			  $scope.formInfo = data;
			  $scope.pageName = "Edit";
			});
		}
		else{
			$scope.pageName = "Enter";
		}
	}

	var redirectToAdminHome = function(){
		$window.location.href = '/admin/home.html';
	}

    $scope.saveData = function(){
    	genreContentService.saveGenre($scope.formInfo).success(redirectToAdminHome);
    }
 };
genresAdminApp.controller('genreDetailsController',['$scope', '$window', '$location', 'genreContentService', genreDetailsController]);