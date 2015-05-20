'use strict';

describe('Word feature controller specs:', function() {
	var scope,
		$httpBackend;
	beforeEach(module("word"));

	beforeEach(inject(function(_$controller_, _$rootScope_, wordService, sortService, _$httpBackend_) {
		scope = _$rootScope_.$new();
        $httpBackend = _$httpBackend_;

        _$controller_('wordFeaturedContentController', {
            $scope: scope,
            wordService: wordService,
			sortService: sortService
        });

	}));

	describe('When initializing the controller,', function() {
		it('should have list of words which are rootwords', function() {
			$httpBackend.when("GET", "/api/words?publish=true").respond({"words" : [{'id': 1, 'isRootWord': true, 'wordTranslation': "translation"}, {'id': 2, 'isRootWord': true, 'wordTranslation':"trnaslation"}]});

			scope.init();
			$httpBackend.flush();

			expect(scope.words.length).toBe(2);
		});
		it('should not have list of words which are not rootwords', function() {
			$httpBackend.when("GET", "/api/words?publish=true").respond({"words" : [{'id': 1}, {'id': 2}]});

			scope.init();
			$httpBackend.flush();

			expect(scope.words.length).toBe(0);
		});
	});
});