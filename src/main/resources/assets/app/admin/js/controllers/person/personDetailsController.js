var personDetailsController = function($scope, $http,$window,$location){
  $scope.formInfo = {};
  $scope.categoryList = [{"occupation":"Poet"},{"occupation":"Singer"}];
  $scope.saveData = function(){
  $http.post('/api/people',$scope.formInfo).success(function(data){
          $window.location.href = '/admin/partials/person/edit.html?id=' + data;
       });
  };

  $scope.getPersonData = function(){
  	var id = $location.search().id;

  	$http.get('/api/people/'+id).success(function(data){
        $scope.formInfo = data;
        $scope.formInfo.category = data.roles[0];
        console.log(data.roles[0]);
    });
  };

  $scope.updatePerson = function(){
  	$http.post('/api/people/edit', $scope.formInfo).success(function(data){
            $window.location.href = '/admin/partials/home.html';
     });
  }

	$scope.redirectToEnterPage= function(isDirty){
		if(isDirty)
		{
		    alert('This data is not updated');
		}
		$window.location.href = '/admin/partials/person/details.html';
	};

};

adminApp.controller('personDetailsController',['$scope','$http','$window','$location',personDetailsController]);
