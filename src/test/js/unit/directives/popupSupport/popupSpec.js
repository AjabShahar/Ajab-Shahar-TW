'use strict';

describe('Popup Support', function() {
    describe('should show the popup content', function() {
        var scope;
        var element;
        var compile;
        var template;
        beforeEach(function(){
            module('popupSupport');
        });

        beforeEach(inject(function($rootScope,$compile,$templateCache) {
            scope = $rootScope.$new();
            compile = $compile;
            template = $templateCache;
        }));

        it('Should initialize couplet with details', function() {
            element = angular.element('<pop-up>content is here</pop-up>');
            template.put('/user/js/common/templates/popupSupport/popup.html', '<div ng-transclude></div>');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('<span class="ng-scope">content is here</span>');
        });
    });
});
