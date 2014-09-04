var splashScreenController = function($scope,$location){
     $scope.videoUrl;
     $scope.getVideoUrl = function(){
         var splashScreenOptions = {
             "options" :[
                         {"format":"audio","url":"https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1","imageUrl":""},
                         {"format":"video","url":"https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1"}
             ]
         };
         var min = 0, max = splashScreenOptions.options.length;

         var index = $scope.getRandomIntWithRange(min, max);
         $scope.videoUrl = splashScreenOptions.options[index].url;
         $scope.ngViewUrl = "/splashScreenVideo";
         $location.path($scope.ngViewUrl);
     };

     $scope.getRandomIntWithRange = function(min, max) {
         return Math.floor(Math.random() * (max - min)) + min;
     };

     $scope.getVideoUrl();
 };
introductionApp.controller('splashScreenController',['$scope','$location',splashScreenController]);