var categoryDetailsController = function($scope, $http){
  $scope.categoryTypeList = null;
  $scope.categoryName = "";
  $scope.category = {};
  $scope.AddNewDiv = false;

  $http.get('/api/category').success(function(result){
    $scope.categoryTypeList = result;
  });

  $scope.enableNew = function(){
    $scope.AddNewDiv = true;
  }

  $scope.saveData = function(){
    console.log("enableNew called");
  }  
}

adminApp.controller('categoryDetailsController',['$scope', '$http', categoryDetailsController]);
