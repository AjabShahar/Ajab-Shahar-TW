'use strict';

describe('Word feature controller specs:', function() {
	var scope,
		$httpBackend;
	beforeEach(module("word"));

	beforeEach(inject(function(_$controller_, _$rootScope_, wordService, _$httpBackend_) {
		scope = _$rootScope_.$new();
        $httpBackend = _$httpBackend_;

        _$controller_('wordFeaturedContentController', {
            $scope: scope,
            wordService: wordService,
        });

	}));

	describe('When initializing the controller,', function() {
		it('should have list of words which are rootwords', function() {
			$httpBackend.when("GET", "/api/words?showOnWordsLandingPage=true").respond({"words" : [{'id': 1, 'isRootWord': true}, {'id': 2, 'isRootWord': true}]});

			scope.init();
			$httpBackend.flush();

			expect(scope.words.length).toBe(2);
		});
		it('should not have list of words which are not rootwords', function() {
			$httpBackend.when("GET", "/api/words?showOnWordsLandingPage=true").respond({"words" : [{'id': 1}, {'id': 2}]});

			scope.init();
			$httpBackend.flush();

			expect(scope.words.length).toBe(0);
		});
	});
});