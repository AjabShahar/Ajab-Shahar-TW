var wordDetailsController = function($scope, $http,$window,$location){

  $scope.formInfo = {};
  $scope.categoryList = [];
  $scope.urlId = $location.search().id;
  $scope.formInfo.wordIntroductions = [];

  $http.get('/api/category/word').success(function(categoryList){
          $scope.categoryList = categoryList;
  });

  $scope.saveData = function(){
      $http.post('/api/words',$scope.formInfo).success(function(data){
          $window.location.href = '/admin/words/edit.html?id='+data;
      });
  };

  $scope.init = function(){
    if($scope.urlId!=null && $scope.urlId!=''){
        $http.get('/api/words/edit', {
                          params: {
                             id:$scope.urlId
                          }
                       })
                       .success(function (data) {
                            $scope.formInfo = data;
        });
    }
  };

   $scope.redirectToEnterPage= function(){
      $window.location.href = '/admin/home.html';
   };

  $scope.updateWord = function(){
    $http.post('/api/words/edit',$scope.formInfo).success(function(data){
     $window.location.href = '/admin/home.html';
    });
  }
   $scope.init();
};

wordsAdminApp.controller('wordDetailsController',['$scope','$http','$window','$location',wordDetailsController]);
