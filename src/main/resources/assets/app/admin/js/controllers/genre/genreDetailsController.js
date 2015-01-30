var genreDetailsController = function($scope, $http, $window, $location, genreContentService){
	$scope.formInfo = {};
	$scope.pageName = {};

	$scope.getGenreData = function(){
		var genreId = $location.search().id;

		if(Boolean(genreId)){
			genreContentService.getGenre(genreId).success(function (data, status) {
			  $scope.formInfo = data;
			  $scope.pageName = "Edit";
			});
		}
		else{
			$scope.pageName = "Enter";
		}
	}

    $scope.saveData = function(){
    	genreContentService.saveGenre($scope.formInfo).success(function(data){
          $window.location.href = '/admin/home.html';
      });
    }
 };
genresAdminApp.controller('genreDetailsController',['$scope', '$http', '$window', '$location', 'genreContentService', genreDetailsController]);