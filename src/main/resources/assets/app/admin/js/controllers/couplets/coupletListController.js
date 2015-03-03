var coupletListController = function($scope, contentService){
    $scope.couplets = [];
    $scope.init = function(){
        contentService.getAllCouplets().then(function(result){
            var allCouplets = result.data;
            $scope.couplets = _.reduce(allCouplets,function(couplets, value,index) {
                var toBeAdded={};
                toBeAdded.title = value.englishTranslation;
                toBeAdded.categoryName = value.songCategory.name;
                toBeAdded.id = value.id;
                toBeAdded.poet = '';
                couplets.push(toBeAdded);
                return couplets;
            },[]);
        });
    };

    $scope.init();
};

adminApp.controller('coupletListController',['$scope','contentService',coupletListController]);