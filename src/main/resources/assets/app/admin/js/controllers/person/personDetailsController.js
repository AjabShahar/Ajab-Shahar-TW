var personDetailsController = function($scope, $http,$window,$location, contentService){
  $scope.formInfo = {};
  $scope.categoryList = null;

  $scope.init = function(){
    contentService.getAllCategories('person').then(function(result){
      $scope.categoryList = result.data;
    });
  }


  $scope.saveData = function(){
  $http.post('/api/people',$scope.formInfo).success(function(data){
          $window.location.href = '/admin/person/edit.html?id=' + data;
       });
  };

  $scope.getPersonData = function(){
  	var id = $location.search().id;

  	$http.get('/api/people/'+id).success(function(data){
        $scope.formInfo = data;

        angular.forEach($scope.categoryList,function(category){
          angular.forEach(data.roles,function(selectedCategories){
             if(selectedCategories == category.name)
               category.ticked=true;
          });
        });

    });
  };

  $scope.updatePerson = function(){
  	$http.post('/api/people/edit', $scope.formInfo).success(function(data){
            $window.location.href = '/admin/home.html';
     });
  }

	$scope.redirectToEnterPage= function(isDirty){
		if(isDirty)
		{
		    alert('This data is not updated');
		}
		$window.location.href = '/admin/person/details.html';
	};

  $scope.init();

};

personAdminApp.controller('personDetailsController',['$scope','$http','$window','$location', 'contentService',personDetailsController]);
