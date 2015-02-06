var reflectionDetailsController = function($scope, $http,$window, $location){
   $scope.formInfo = {"reflectionTranscripts":[],"speaker":{}};
   $scope.people = [];
   $scope.urlId = $location.search().id;

   $scope.init = function(){
      $http.get('/api/people').success(function(data){
         $scope.people = data.people;
      });

      if($scope.urlId!=null && $scope.urlId!=''){
          $http.get('/api/reflections/edit', {
                                params: {
                                   id:$scope.urlId
                                }
                             })
                             .success(function (data) {
                               $scope.formInfo = data;
                               $scope.formInfo.speaker = data.speaker;
          });
      }

   }

      $scope.saveData = function(){
        $http.post('/api/reflections',$scope.formInfo).success(function(data){
           $window.location.href = '/admin/home.html';
        });
      }

      $scope.updateData = function(){
        $http.post('/api/reflections/edit',$scope.formInfo).success(function(data){
                   $window.location.href = '/admin/home.html';
        });

      }

   $scope.init();
}

reflectionsAdminApp.controller('reflectionDetailsController',['$scope','$http','$window','$location',reflectionDetailsController]);