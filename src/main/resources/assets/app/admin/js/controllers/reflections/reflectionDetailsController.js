var reflectionDetailsController = function($scope, $http,$window){
   $scope.formInfo = {"reflectionTranscripts":[]};
   $scope.people = [];

   $scope.init = function(){
      $http.get('/api/people').success(function(data){
         $scope.people = data.people;
      });

   }

      $scope.saveData = function(){
        $scope.getTranscripts();
        $http.post('/api/reflections',$scope.formInfo).success(function(data){
           $window.location.href = '/admin/home.html';
        });
      }

      $scope.getTranscripts = function(){
         var transcript = {}
         transcript.text = $scope.formInfo.transcript;
         $scope.formInfo.reflectionTranscripts.push(transcript);
      }
   $scope.init();
}

adminApp.controller('reflectionDetailsController',['$scope','$http','$window',reflectionDetailsController]);