var personDetailsController = function( $scope, $http, $window, $location, contentService ){
  $scope.pageHeading = "";
  $scope.pageTitle = "";
  $scope.formInfo = {};
  $scope.categoryList = null;
  var isAddNewPersonPage = true;

  $scope.init = function(){
    contentService.getAllCategories('person').then(function(result){
      $scope.categoryList = result.data;
    });
  }();

  var savePerson = function(){
    $http.post('/api/people',$scope.formInfo).success(function(data){
        $window.location.href = '/admin/person/details.html?id=' + data;
    });
  };

  var updatePerson = function(){
    $http.post('/api/people/edit', $scope.formInfo).success(function(data){
      $window.location.href = '/admin/home.html';
    });
  };

  $scope.saveData = function(){
    (isAddNewPersonPage) ? savePerson() : updatePerson();
  };

  var getPersonDetails = function(personId){
    $http.get( '/api/people/' + personId ).success(function(data){
        $scope.formInfo = data;

        angular.forEach($scope.categoryList, function(category){
          angular.forEach(data.roles, function(selectedCategoryName){
            ( selectedCategoryName == category.name ) ? category.ticked = true : category.ticked = false;
          });
        });
    });
  };

  $scope.getPersonData = function(){
  	var personId = $location.search().id;
    isAddNewPersonPage = (personId == undefined);

    if(isAddNewPersonPage){
      $scope.pageTitle = "Person details - admin";
      $scope.pageHeading = "Add Person details";
    }
    else{
      $scope.pageTitle = "Edit person details";
      $scope.pageHeading = "Edit Person details";
      getPersonDetails(personId);  
    }
  };
};

personAdminApp.controller('personDetailsController', ['$scope', '$http', '$window', '$location', 'contentService', personDetailsController] );