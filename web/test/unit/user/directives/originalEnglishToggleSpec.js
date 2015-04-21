describe("original english toggle ",function(){
    var scope,compile,template;

    beforeEach(module("animationModule"));
    beforeEach(inject(function (_$rootScope_, $compile,$templateCache) {
        scope = _$rootScope_.$new();
        compile = $compile;
        template = $templateCache;

        var originalEnglishToggle = '<original-english-toggle></original-english-toggle>';
        template.put('/user/js/common/templates/common/originalEnglishToggle.html','<div></div>');

        var element = compile(originalEnglishToggle)(scope);
        scope.$apply();
    }));

    it("should select original title by default",function(){
        expect(scope.isOriginalTitle).toBeTruthy();
        expect(scope.isEnglishTitle).toBeFalsy();
    });

    it("should toggle english title on click",function(){
        scope.selectEnglishTitle();

        expect(scope.isEnglishTitle).toBeTruthy();
        expect(scope.isOriginalTitle).toBeFalsy();

    });

    it("should toggle original title on click",function(){
        scope.selectOriginalTitle();

        expect(scope.isEnglishTitle).toBeFalsy();
        expect(scope.isOriginalTitle).toBeTruthy();

    });

    it("should change contentText representation variable correspondingly on selection",function(){
        scope.selectOriginalTitle();

        expect(scope.contentTextRepresentation).toBe('Transliteration');

        scope.selectEnglishTitle();
        expect(scope.contentTextRepresentation).toBe('Translation');
    });
});
