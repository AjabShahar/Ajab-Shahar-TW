var genreDetailsController = function($scope, $window, $location, genreContentService){
	$scope.formInfo = {};
	$scope.pageName = {};
	$scope.alert = "";

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
	};

	var redirectToAdminHome = function(){
		$window.location.href = '/admin/home.html';
	};

	var isValidData = function(){
		return ((Boolean($scope.formInfo.original)) && (Boolean($scope.formInfo.english)));
	};

    $scope.saveData = function(){
    	if(isValidData())
    		genreContentService.saveGenre($scope.formInfo).success(redirectToAdminHome);
    	else
    		$scope.alert = "Please fill in all the fields";
    };
 };
genresAdminApp.controller('genreDetailsController',['$scope', '$window', '$location', 'genreContentService', genreDetailsController]);