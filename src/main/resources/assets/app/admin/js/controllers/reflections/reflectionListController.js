var reflectionListController = function($scope, contentService){
    $scope.reflections = [];
    $scope.init = function(){
        contentService.getAllReflections().then(function(result){
            var allReflections = result.data;
            $scope.reflections = _.reduce(allReflections,function(reflections, value,index) {
                var toBeAdded={};
                toBeAdded.title = value.title;
                toBeAdded.id = value.id;
                toBeAdded.speaker = value.speaker.firstName +' '+ value.speaker.lastName;

                if(value.isAuthoringComplete)
                    toBeAdded.publish = "Yes";
                else
                    toBeAdded.publish = "No";

                reflections.push(toBeAdded);
                return reflections;
            },[]);
        });
    };

    $scope.init();
};

adminApp.controller('reflectionListController',['$scope','contentService',reflectionListController]);