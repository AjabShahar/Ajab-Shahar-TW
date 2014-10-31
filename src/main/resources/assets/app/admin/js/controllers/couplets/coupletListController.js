var coupletListController = function($scope, contentService,nameService){
    $scope.couplets = [];
    $scope.nameService = nameService;
    $scope.init = function(){
        contentService.getAllCouplets().then(function(result){
            var allCouplets = result.data;
            $scope.couplets = _.reduce(allCouplets,function(couplets, value,index) {
                var toBeAdded={};
                toBeAdded.title = value.englishTranslation;
                toBeAdded.categoryName = value.songCategory.name;
                toBeAdded.id = value.id;
                toBeAdded.poet = nameService.getName(value.poet);
                couplets.push(toBeAdded);
                return couplets;
            },[])
        });
    }

    $scope.init();
}

adminApp.controller('coupletListController',['$scope','contentService','nameService',coupletListController]);