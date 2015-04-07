'use strict';

describe("Error message specs:", function () {
    var $compile,
        scope,
        template;

    beforeEach(module('commonApp'));

    beforeEach(inject(function (_$compile_, _$rootScope_, $templateCache) {
        $compile = _$compile_;
        scope = _$rootScope_.$new();
        template = $templateCache;
    }));

    describe("When the directive is loaded,", function () {
        it("then should show an error message if show-error attribute is set true", function () {
            var errorMessageDirective = '<error-message name="This" show-error="true"></error-message>';

            var element = $compile(errorMessageDirective)(scope);
            scope.$apply();

            expect(element.html()).not.toContain('ng-hide');
        });
        it("then should not show an error message if show-error attribute is set false", function () {
            var errorMessageDirective = '<error-message name="That" show-error="false"></error-message>';

            var element = $compile(errorMessageDirective)(scope);
            scope.$apply();

            expect(element.html()).toContain('ng-hide');
        });
    });
});
