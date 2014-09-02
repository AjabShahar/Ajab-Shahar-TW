function getRandomIntWithRange(min, max) {
  return Math.floor(Math.random() * (max - min)) + min;
}

var splashScreenController = function($scope){
	$scope.getVideoUrl = function(){
			var listOfVideoUrls = ["http://test.com", "http://test2.com"];
			var min = 0, max = listOfVideoUrls.length;

			var index = getRandomIntWithRange(min, max);
			return listOfVideoUrls[index];
	}
};

introductionApp.controller('splashScreenController',['scope',splashScreenController]);