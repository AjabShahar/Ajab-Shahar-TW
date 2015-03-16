adminApp.controller('categoryDetailsController',['$scope', '$http',"loginVerifyService",
function($scope, $http,loginVerifyService){
  loginVerifyService.redirectIfNotAuthenticated();
  $scope.categoryTypeList = [];
  $scope.category = {};
  $scope.AddNewDiv = false;

  $http.get('/api/category').success(function(result){
    $scope.categoryTypeList = result;
  });

  $scope.enableNew = function(){
    $scope.category.categoryType = '';
    $scope.AddNewDiv = true;
  };

  $scope.saveData = function(){
    $http.post('/api/category', $scope.category).success(function(data){
      alert("data added");
    });
  }
}]);

