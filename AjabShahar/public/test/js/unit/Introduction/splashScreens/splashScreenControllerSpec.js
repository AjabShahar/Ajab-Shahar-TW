'use strict';

describe("SplashScreenController Specs", function(){
	var scope;//scope will have requiredURL with it

	// var sampleResponse = {
	// 	"videoUrl": ["http://test.com", "http://test2.com"];
	// }

	beforeEach(inject(function (_$rootScope_, _$controller_) {
    	scope = _$rootScope_.$new();
    	_$controller_(createPollController, {
	        $scope: scope,
        }
	)}));

//	beforeEach(function () {
//    module('introductionApp');
//    inject(function ($route) {
//      route = $route;
//   	});

	it("should contain video-url randomly choosen from the list of video urls", function(){
		var result = JSON.parse(sampleResponse);
//		expect(result.videoUrl).contain(scope.videoUrlkarma.conf.js);
	});
});