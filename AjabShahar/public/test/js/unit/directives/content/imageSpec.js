'use strict';

describe('Content', function() {

    describe('image content', function() {
        var scope;
        var element;
        var compile;
        var template;
        beforeEach(function(){
            module('contentModule');
        });

        beforeEach(inject(function($rootScope,$compile,$templateCache) {
            scope = $rootScope.$new();
            compile = $compile;
            template = $templateCache;
        }));

        it('Should initialize image with details', function() {
            scope = {
                        name1:'some Name',
                        singer1:'Some Singer',
                        imgSrc1:'someimg',
                        shiftDirection1:'r',
                        shiftIndex1:'1',
                        showcontrols1:true,
            };
            element = angular.element('<image-with-details showcontrols="{{showcontrols1}}" shift-direction="{{shiftDirection1}}" shift-index="{{shiftIndex1}}" img-src="{{imgSrc1}}" name="{{name1}}" singer="{{singer1}}"></image-with-details>');
            template.put('js/templates/content/image.html', '{{name}} {{singer}} {{img-src}} shift({{shift-direction}},{{shift-index}}) {{show-controls}}');
            compile(element)(scope);

            expect(element.html()).toBe('some Name Some Singer someimg shiftRight1 true');
        });
    });
});
