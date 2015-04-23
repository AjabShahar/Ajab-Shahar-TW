'use strict';

describe("Carousel", function () {
    var $compile,
        scope,
        template,
        rootScope,
        $httpBackend;

    beforeEach(module('thumbnailModule'));
    beforeEach(module('user/js/common/directives/carouselSupport/carousel.html'));

    beforeEach(inject(function (_$compile_, _$rootScope_, $templateCache,_$httpBackend_) {
        $compile = _$compile_;
        scope = _$rootScope_.$new();
        template = $templateCache;
        rootScope = _$rootScope_;
        $httpBackend = _$httpBackend_;
    }));


    xit("should invoke the item clicked handler with proper data on the selecting an item in the  carousel",function(){
        $httpBackend.expectGET("/user/js/common/directives/carouselSupport/carousel.html").respond('200');
        scope.items = test_carouselItems;

        var selectedWord = null;
        scope.itemSelected = function(data){
            selectedWord =data;
        };

        var directiveElement = angular.element('<carousel items="" item-clicked="itemSelected" format="\'transliteration\'" ></carousel>');
        $compile(directiveElement)(scope);
        scope.$digest();

        var directiveScope = directiveElement.isolateScope();
        console.log("directive scope :",directiveScope)

        directiveScope.selectThumbnail(0);
        expect(scope.itemSelected).toHaveBeenCalled();

    });
});

var test_carouselItems = [
    {
        id:"2",
        type:"word",
        thumbnailImg:"img2.jpg",
        description:"lorem ipsum platinum",
        verbPeople:{
            verb:"Intro by",
            people:"parvati & kabir"
        },
        translitTitle:"jeena marna",
        englishTitle:"living and dying",
        contentFormat:"text"
    },
    {
        id:"1",
        type:"word",
        thumbnailImg:"img1.jpg",
        description:"its about crying and washing",
        verbPeople:{
            verb:"Intro by",
            people:"meena & jagjit"
        },
        englishTitle:"crying and washing",
        translitTitle:"rona dhona",
        contentFormat:"text"
    },
    {
        id:"3",
        type:"word",
        thumbnailImg:"img3.jpg",
        description:"its all about winning",
        verbPeople:{
            verb:"Intro by",
            people:"mahi & punter"
        },
        englishTitle:"winning and losing",
        translitTitle:"harna jeetna",
        contentFormat:"text"
    }

];