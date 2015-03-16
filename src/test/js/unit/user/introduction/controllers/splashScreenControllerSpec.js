'use strict';

describe("SplashScreenController Specs", function(){
	var scope,q,controller;

    var getPromise = function(response) {
      response = response || '';
      var deferred = q.defer();
      deferred.resolve(response);
      return deferred.promise;
    };

	var sampleResponse = 
		["https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1",
		 "https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1"];

	var contentService = {
	    getScreenOptions:function(){}
	};

    var location = {
        path:function(){}
    };

    beforeEach(inject(function($q) {
      q = $q;
    }));

	beforeEach(inject(function (_$rootScope_, _$controller_) {
    	scope = _$rootScope_.$new();
        spyOn(location,'path');
        controller = _$controller_;
    }));

	it("should contain video-url randomly choosen from the list of video urls", function(){
        spyOn(contentService,'getScreenOptions').andReturn(getPromise({
          "data" :[
                      {"format":"video","url":"aaa","imageUrl":""},
          ]
        }));

        controller(splashScreenController, {
            $scope: scope,
            $location:location,
            contentService:contentService,
        });

        var splashScreenDetails = scope.getVideoUrl();
        expect(contentService.getScreenOptions).toHaveBeenCalled();
        scope.$apply();

        expect(scope.option.url).toBe('aaa');
        expect(location.path).toHaveBeenCalledWith('/splashScreenVideo');
	});

	it("should contain audio-url randomly choosen from the list of audio urls", function(){
        spyOn(contentService,'getScreenOptions').andReturn(getPromise({
          "data" :[
             {"format":"audio","url":"aaa","image":"kkk"},
          ]
        }));

        controller(splashScreenController, {
            $scope: scope,
            $location:location,
            contentService:contentService,
        });

        var splashScreenDetails = scope.getVideoUrl();
        expect(contentService.getScreenOptions).toHaveBeenCalled();
        scope.$apply();

        expect(scope.option.url).toBe('aaa');
        expect(scope.option.imageUrl).toBe('kkk');

        expect(location.path).toHaveBeenCalledWith('/splashScreenAudio');
	});
});