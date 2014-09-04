'use strict';

describe("SplashScreenController Specs", function(){
	var scope;//scope will have requiredURL with it

	var sampleResponse = 
		["https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1",
		 "https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1"];
	

    var location = {
        path:function(){}
    };

	beforeEach(inject(function (_$rootScope_, _$controller_) {
    	scope = _$rootScope_.$new();
    	_$controller_(splashScreenController, {
	        $scope: scope,
	        $location:location,
        }
	)}));

	it("should contain video-url randomly choosen from the list of video urls", function(){
	    spyOn(location,'path');
		expect(_.contains(sampleResponse, scope.videoUrl)).toBe(true);
		expect(scope.ngViewUrl).toBe('/splashScreenVideo');
//        expect(location.path).toHaveBeenCalledWith('/splashScreenVideo');
	});
});