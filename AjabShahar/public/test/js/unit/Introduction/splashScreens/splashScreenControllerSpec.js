'use strict';

describe("SplashScreenController Specs", function(){
	var scope;//scope will have requiredURL with it

	var sampleResponse = 
		["https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1",
		 "https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1"];

	var introductionAppService = {
	    getScreenOptions:function(){}
	};

    var location = {
        path:function(){}
    };

	beforeEach(inject(function (_$rootScope_, _$controller_) {
    	scope = _$rootScope_.$new();
        spyOn(location,'path');
        spyOn(introductionAppService,'getScreenOptions');

    	_$controller_(splashScreenController, {
	        $scope: scope,
	        $location:location,
	        introductionAppService:introductionAppService,
        })
    }));

	it("should contain video-url randomly choosen from the list of video urls", function(){
	    introductionAppService.getScreenOptions.andReturn(
	                 {
                         "options" :[
                                     {"format":"video","url":"aaa","imageUrl":""},
                         ]
                     }
	    );

        var splashScreenDetails = scope.getVideoUrl();
        expect(splashScreenDetails.url).toBe('aaa');
        expect(location.path).toHaveBeenCalledWith('/splashScreenVideo');
	});

	it("should contain audio-url randomly choosen from the list of audio urls", function(){
	    introductionAppService.getScreenOptions.andReturn(
	                 {
                         "options" :[
                                     {"format":"audio","url":"aaa","imageUrl":"kkk"},
                         ]
                     }
	    );

        var splashScreenDetails = scope.getVideoUrl();
        expect(splashScreenDetails.url).toBe('aaa');
        expect(splashScreenDetails.imageUrl).toBe('kkk');
        expect(location.path).toHaveBeenCalledWith('/splashScreenAudio');
	});
});