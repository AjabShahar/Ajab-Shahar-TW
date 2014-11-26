var coupletDetailsController = function($scope, $http,$window,$location){
    $scope.formInfo = {};
    $scope.formInfo.poet = [];
    $scope.categoryList= [];
    $scope.urlId = $location.search().id;


    $http.get('/api/people?role=Poet').success(function(poetsList){
        $scope.poetsList = poetsList;
    });

    $http.get('/api/category/couplet').success(function(categoryList){
        $scope.categoryList = categoryList;
    });


    $scope.saveData = function(){
    $http.post('/api/couplets',$scope.formInfo).success(function(data){
         $window.location.href = '/admin/partials/couplets/edit.html?id='+data;
           });
    };

    $scope.getCoupletData = function(){
            $http.get('/api/couplets/edit', {
                              params: {
                                 id:$scope.urlId
                              }
                           })
                           .success(function (data) {
                                $scope.formInfo = data;
            });
      };

      $scope.updateCouplet = function(){
           $http.put('/api/couplets/edit',
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
          $window.location.href = '/admin/partials/couplets/details.html';
       };
 };
adminApp.controller('coupletDetailsController',['$scope','$http','$window','$location',coupletDetailsController]);
