var categoryListController = function($scope, $location, contentService){
    $scope.categories = [];
    $scope.init = function(){
        $location.url($location.absUrl());
        $scope.url = $location.absUrl();
        $scope.type = $location.search().type;

        contentService.getAllCategories($scope.type).then(function(result){
            var allCategories = result.data;
            $scope.categories = _.reduce(allCategories,function(categories, value,index) {
                var toBeAdded={};
                toBeAdded.name = value.name;
                toBeAdded.type = value.categoryType;
                toBeAdded.id = value.id;
                categories.push(toBeAdded);
                return categories;
            },[]);
        });
    };

    $scope.init();
};

adminApp.controller('categoryListController',['$scope','$location','contentService',categoryListController]);