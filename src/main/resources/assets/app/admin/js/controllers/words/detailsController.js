var wordDetailsController = function($scope, $http,$window,$location){

  $scope.formInfo = {};
  $scope.categoryList = [];
  $scope.urlId = $location.search().id;
  $scope.formInfo.wordIntroductionArray = [];

  $http.get('/api/category/word').success(function(categoryList){
          $scope.categoryList = categoryList;
  });

  $scope.saveData = function(){
  $http.post('/api/words',$scope.formInfo).success(function(data){
      $window.location.href = '/admin/partials/words/edit.html?id='+data;
       });
  };

  $scope.getWordData = function(){
        $http.get('/api/words/edit', {
                          params: {
                             id:$scope.urlId
                          }
                       })
                       .success(function (data) {
                            $scope.formInfo = data;
        });
  };

  $scope.updateWord = function(){
       $http.put('/api/words/edit',
              {
                id:$scope.urlId,
                data:$scope.formInfo
              }
       ).success(function(data){
              alert('data updated');
       });
  };

   $scope.redirectToEnterPage= function(){
     alert('This data is not updated');
      $window.location.href = '/admin/partials/words/details.html';
   };

   $scope.addToWordIntroduction = function(){
      var wordIntroduction = {};
      for(var i=0,j=0;i<$scope.formInfo.wordIntroduction.length;){
         wordIntroduction.introduction_text = $scope.formInfo.wordIntroduction.split(500);
         $scope.formInfo.wordIntroductionArray[j] = wordIntroduction;
         i=i+500;
         j=j+1;
      }
   };
};

adminApp.controller('wordDetailsController',['$scope','$http','$window','$location',wordDetailsController]);
