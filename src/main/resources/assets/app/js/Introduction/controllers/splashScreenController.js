var splashScreenController = function($scope,$location,contentService){
    $scope.option;
    $scope.splashScreenOptions;
    $scope.getVideoUrl = function(){
        contentService.getScreenOptions().then(function(result){
                splashScreenOptions = result
                $scope.option = {"url":"","imageUrl":""};
                if(splashScreenOptions==null || splashScreenOptions.data.length==0)
                return;

                var min = 0, max = splashScreenOptions.data.length;

                var index = $scope.getRandomIntWithRange(min, max);
                var splashScreenOption = splashScreenOptions.data[index];

                $scope.option.url = splashScreenOption.url;

                if(splashScreenOption.format=='video'){
                  $location.path('/splashScreenVideo');
                  $scope.option.imageUrl = "";
                }
                if(splashScreenOption.format=='audio'){
                  $location.path('/splashScreenAudio');
                  $scope.option.imageUrl = splashScreenOption.imageUrl;
                }

            });
     };

    $scope.showEnterButton = function(){
        $scope.expand = true;
    }
     $scope.getRandomIntWithRange = function(min, max) {
         return Math.floor(Math.random() * (max - min)) + min;
     };
    $scope.getVideoUrl();
 };

introductionApp.controller('splashScreenController',['$scope','$location','contentService',splashScreenController]);