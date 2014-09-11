var splashScreenController = function($scope,$location,introductionAppService){
    $scope.url;
    $scope.imageUrl;
    $scope.getVideoUrl = function(){
         var splashScreenOptions = introductionAppService.getScreenOptions();
         if(splashScreenOptions==null || splashScreenOptions.options.length==0)
            return {"url":"","imageUrl":""};
         var min = 0, max = splashScreenOptions.options.length;

         var index = $scope.getRandomIntWithRange(min, max);
         var splashScreenOption = splashScreenOptions.options[index];

        $scope.url = splashScreenOption.url;
        $scope.imageUrl = splashScreenOption.imageUrl;
         if(splashScreenOption.format=='video'){
              $location.path('/splashScreenVideo');
              return {"url": splashScreenOption.url,imageUrl:""};
         }
         if(splashScreenOption.format=='audio'){
              $location.path('/splashScreenAudio');
              return {"url": splashScreenOption.url,imageUrl:splashScreenOption.imageUrl};
         }
     };

    $scope.showEnterButton = function(){
        $scope.expand = true;
    }
     $scope.getRandomIntWithRange = function(min, max) {
         return Math.floor(Math.random() * (max - min)) + min;
     };
    $scope.getVideoUrl();
 };

introductionApp.controller('splashScreenController',['$scope','$location','introductionAppService',splashScreenController]);