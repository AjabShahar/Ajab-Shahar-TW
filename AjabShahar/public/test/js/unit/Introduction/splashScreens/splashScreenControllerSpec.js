'use strict';

describe("SplashScreenController Specs", function(){
	var scope;//scope will have requiredURL with it

	var sampleResponse = 
		["https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1",
		 "https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1"];
	

	beforeEach(inject(function (_$rootScope_, _$controller_) {
    	scope = _$rootScope_.$new();
    	_$controller_(splashScreenController, {
	        $scope: scope,
        }
	)}));

	it("should contain video-url randomly choosen from the list of video urls", function(){
		expect(_.contains(sampleResponse, scope.getVideoUrl())).toBe(true);
	});
});