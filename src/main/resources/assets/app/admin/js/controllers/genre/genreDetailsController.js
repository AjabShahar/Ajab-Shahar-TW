var genreDetailsController = function($scope, $http, $window, $location){
	$scope.formInfo = {};

    $scope.saveData = function(){
    	$http.post('/api/genre',$scope.formInfo).success(function(data){
          $window.location.href = '/admin/home.html';
      });
    }
 };
adminApp.controller('genreDetailsController',['$scope', '$http', '$window', '$location', genreDetailsController]);
