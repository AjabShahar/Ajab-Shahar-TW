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
     $http.post($scope.formInfo).success(function(data){
         $window.location.href = '/admin/partials/home.html';
     });
  };

   $scope.redirectToEnterPage= function(){
      $window.location.href = '/admin/partials/home.html';
   };

   $scope.addToWordIntroduction = function(){
      var wordIntroduction = {};
      var wordIntroductions = $scope.formInfo.wordIntroduction.replace(/(<([^>]+)>)/ig,"\n").split(/\n{2,}/g);
      for(var i=0;i<wordIntroductions.length;){
         wordIntroduction.introduction_text = "<p>"+wordIntroductions[i].replace("\n","")+"</p>";
         $scope.formInfo.wordIntroductions[i] = wordIntroduction;
         i=i+1;
      }
   };
};

adminApp.controller('wordDetailsController',['$scope','$http','$window','$location',wordDetailsController]);
