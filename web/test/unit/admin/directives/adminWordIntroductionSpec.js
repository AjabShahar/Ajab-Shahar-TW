describe("Admin word introductions specs", function(){
	var scope, $compile, template, controller, adminWordIntroductionDirective, element;

	beforeEach(module("wordsAdminApp"));

	beforeEach(inject(function(_$rootScope_,_$compile_,$templateCache){
    		scope = _$rootScope_.$new();
    		$compile = _$compile_;
    		template = $templateCache;

    		adminWordIntroductionDirective = angular.element('<admin-word-introduction word-introductions=[]></admin-word-introduction>');
            template.put('/admin/js/templates/adminWordIntroduction.html','<div> </div>');

            element = $compile(adminWordIntroductionDirective)(scope);
            scope.$apply();
            controller = element.controller;
    }));

	describe("It should Add wordIntroductions and split", function(){
		it("Then It should have array of word introductions", function(){
			scope.wordIntroHindi = "text text \n\n text text";
			scope.wordIntroEnglish = "data data \n\n data data";

//		    scope.addToWordIntroduction();

//   		    expect(scope.wordIntroductions).not.toBe(undefined);
		});

	});
});
