'use strict';

describe("angular-parallaxSpec", function() {
	describe("testing scroll directive", function() {
		var $compile,
			$scope,
			element;

		beforeEach(module('angular-parallax'));

		beforeEach(inject(function(_$compile_, _$rootScope_) {
			$compile = _$compile_;
			$scope = _$rootScope_.$new();

			element = '<div id="clouds" class="clouds" scroll></div>';

			element = $compile(element)($scope);
    		$scope.$digest();
		}));

		it("should increase the margin left value when user scrolls down", function() {
			
		});
		
	});
});