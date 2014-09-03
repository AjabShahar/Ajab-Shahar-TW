//var splashScreenController = function($scope){
//    $scope.videoUrl = "https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1";
////	$scope.getVideoUrl = function(){
////			var listOfVideoUrls = ["https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1",
////			 "https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1"];
////			var min = 0, max = listOfVideoUrls.length;
////
////			var index = getRandomIntWithRange(min, max);
////			return listOfVideoUrls[index];
////	};
////
////	$scope.getRandomIntWithRange = function(min, max) {
////        return Math.floor(Math.random() * (max - min)) + min;
////    };
//};

introductionApp.controller('splashScreenController',function($scope,$sce){
    $scope.videoUrl = $sce.trustAsResourceUrl('https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1');
    $scope.getVideoUrl = function(){
            var listOfVideoUrls = ["https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1",
             "https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1"];
            var min = 0, max = listOfVideoUrls.length;

            var index = getRandomIntWithRange(min, max);
            return listOfVideoUrls[index];
    };

    $scope.getRandomIntWithRange = function(min, max) {
        return Math.floor(Math.random() * (max - min)) + min;
    };
});