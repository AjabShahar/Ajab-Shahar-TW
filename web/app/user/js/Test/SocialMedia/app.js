var myApp = angular.module ('myApp', ['djds4rce.angular-socialshare']);
 
myApp.run(function($FB){
  $FB.init('714039208665351');
});
 
myApp.config(function($locationProvider){
    $locationProvider.html5Mode(true).hashPrefix('!');
});

var test = myApp.controller('test', ['$scope', '$location', function($scope, $location){
	$scope.url;
	$scope.testingCall = function(){
		$scope.url = $location.absUrl();
	};

	$scope.testingCall();
}])
