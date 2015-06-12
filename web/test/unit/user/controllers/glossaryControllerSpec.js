'use strict'

describe("Glossary controller specs", function(){
    var scope, $httpBackend, wordService;
    beforeEach(module("word"));

    beforeEach(inject(function(_$controller_, _$rootScope_, _wordService_, _$httpBackend_, _$filter_){
        scope = _$rootScope_.$new();
        $httpBackend = _$httpBackend_;
        wordService = _wordService_;

        _$controller_('glossaryController', {
            $scope: scope,
            wordService: wordService
        });
    }));

    it("Should only have words with meanings", function(){
        var words = { "words" : [{}, {meaning: ""}, {}, {meaning: "someMeaning"}, {meaning: null}] };
        $httpBackend.when("GET", "/api/words?publish=true").respond(words);
        $httpBackend.flush();

        expect(scope.numberOfTerms).toBe(1);
    });
});