var gatheringDetailsController = gatheringsAdminApp.controller('gatheringDetailsController',['$scope', '$window', '$location', 'gatheringContentService','loginVerifyService',
    function($scope, $window, $location, gatheringContentService, loginVerifyService){
    loginVerifyService.redirectIfNotAuthenticated();
	$scope.formInfo = {};
	$scope.pageName = {};
	$scope.alert = "";

	$scope.getGatheringData = function(){
		var gatheringId = $location.search().id;

		if(Boolean(gatheringId)){
			gatheringContentService.getGathering(gatheringId).success(function (data) {
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
    		gatheringContentService.saveGathering($scope.formInfo).success(redirectToAdminHome);
    	else
    		$scope.alert = "Please fill in all the fields";
    };
 }]);
