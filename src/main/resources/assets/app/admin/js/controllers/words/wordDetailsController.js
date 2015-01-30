var wordDetailsController = function($scope, $http,$window,$location){

  $scope.formInfo = {reflections:[]};
  $scope.categoryList = [];
  $scope.reflectionsList = [];
  $scope.urlId = $location.search().id;
  $scope.formInfo.wordIntroductions = [];

  $http.get('/api/category/word').success(function(categoryList){
          $scope.categoryList = categoryList;
  });

  $http.get('/api/reflections').success(function(data){
          $scope.reflectionsList = data.reflections;
  });

  $scope.saveData = function(){
      $http.post('/api/words',$scope.formInfo).success(function(data){
          $window.location.href = '/admin/home.html';
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
                         angular.forEach($scope.reflectionsList,function(reflection){
                           angular.forEach(data.reflections,function(selectedReflection){
                               if(selectedReflection.id === reflection.id)
                                      reflection.ticked=true;
                            });
                         });
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
