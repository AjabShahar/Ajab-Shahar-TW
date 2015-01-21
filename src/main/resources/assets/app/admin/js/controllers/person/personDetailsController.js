var personDetailsController = function($scope, $http,$window,$location, contentService){
  $scope.pageHeading = "";
  $scope.pageTitle = "";
  $scope.formInfo = {};
  $scope.categoryList = null;
  var isAddNewPersonPage = true;

  $scope.init = function(){
    contentService.getAllCategories('person').then(function(result){
      $scope.categoryList = result.data;
    });
  }

  var savePerson = function(){
    $http.post('/api/people',$scope.formInfo).success(function(data){
          $window.location.href = '/admin/person/details.html?id=' + data;
       });
  }


  $scope.saveData = function(){
    if (isAddNewPersonPage){
        savePerson();
    }
    else {
      updatePerson();
    }
  };

  $scope.getPersonData = function(){
  	var id = $location.search().id;
    isAddNewPersonPage = (id == undefined);

    if(isAddNewPersonPage){
      $scope.pageTitle = "Person details - admin";
      $scope.pageHeading = "Add Person details";
      return;
    }

    $scope.pageTitle = "Edit person details";
    $scope.pageHeading = "Edit Person details";

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

  var updatePerson = function(){
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
