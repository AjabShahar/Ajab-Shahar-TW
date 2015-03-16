'use strict';

describe("Song content details page", function() {
	var scope,
		element,
		compile,
		template;

	
	beforeEach(module("thumbnailModule"));

	beforeEach(inject(function($rootScope, $compile, $templateCache){
		scope = $rootScope.$new();
		compile = $compile;
		template = $templateCache;
	}));

	describe("the displayed media", function() {
		it("should not display a download button, if the download url is empty", function() {
			scope.downloadurl = "";
			
			element = angular.element('<song-content-details downloadurl="{{downloadurl}}"></song-content-details>');
			template.put('/user-js/common/templates/songs/songContentDetails.html', '<div ng-if="downloadurl">{{downloadurl}}</div>');
			compile(element)(scope);
			scope.$apply()

			expect(element.html()).toBe('<!-- ngIf: downloadurl -->');
		});

		it("should display a download button, if the download url is not empty", function() {
			scope.downloadurl = "someurl";
			
			element = angular.element('<song-content-details downloadurl="{{downloadurl}}"></song-content-details>');
			template.put('/user-js/common/templates/songs/songContentDetails.html', '<div ng-if="downloadurl">{{downloadurl}}</div>');
			compile(element)(scope);
			scope.$apply()

			expect(element.html()).toBe('<!-- ngIf: downloadurl --><div ng-if="downloadurl" class="ng-binding ng-scope">someurl</div><!-- end ngIf: downloadurl -->');
		});
	});
});