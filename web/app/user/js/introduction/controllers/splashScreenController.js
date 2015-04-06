var splashScreenController = function($scope,$window,$location,$http,contentService){
    $scope.formInfo = {};
    $scope.option = {"url":"","imageUrl":""};
    $scope.splashScreenOptions;
    $scope.optionsFormatList = [{'id':'audio','name':'Audio'},{'id':'video','name':'Video'}];
    $scope.getVideoUrl = function(){
        contentService.getScreenOptions().then(function(result){
                splashScreenOptions = result;
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
                  $scope.option.imageUrl = splashScreenOption.image;
                }

            });
     };
    $scope.saveData = function(){
       $http.post('/api/SplashScreenOptions',$scope.formInfo).success(function(){
             alert("Data added");
             $window.location.href = '/admin/splashScreenOptions.html';
       });
    };
    $scope.showEnterButton = function(){
        $scope.expand = true;
    };

    $scope.getRandomIntWithRange = function(min, max) {
     return Math.floor(Math.random() * (max - min)) + min;
    };

    $scope.shouldShowImageUrl = function(){
        return ($scope.formInfo.FORMAT=='audio');
    };

    $scope.getVideoUrl();
 };

introductionApp.controller('splashScreenController',['$scope','$window','$location','$http','contentService',splashScreenController]);
