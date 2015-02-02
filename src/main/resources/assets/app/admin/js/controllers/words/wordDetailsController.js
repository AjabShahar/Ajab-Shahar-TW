var wordDetailsController = function($scope, $http,$window,$location){

  $scope.formInfo = {reflections:[]};
  $scope.categoryList = [];
  $scope.reflectionsList = [];
  $scope.peopleList = [];
  $scope.urlId = $location.search().id;
  $scope.formInfo.wordIntroductions = [];

  $http.get('/api/category/word').success(function(categoryList){
          $scope.categoryList = categoryList;
  });

  $http.get('/api/reflections').success(function(data){
          $scope.reflectionsList = data.reflections;
  });

  $http.get('/api/people').success(function(peopleList){
            $scope.peopleList = peopleList.people;
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
                         angular.forEach($scope.peopleList,function(person){
                           angular.forEach(data.writers,function(writer){
                              if(writer.id == person.id)
                                person.ticked = true;
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
