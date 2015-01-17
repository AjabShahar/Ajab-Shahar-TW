describe('Unit testing word bubble', function() {
  var $compile,
      $rootScope;

  beforeEach(module('thumbnailModule'));

  beforeEach(inject(function(_$compile_, _$rootScope_, $templateCache){
    $compile = _$compile_;
    scope = _$rootScope_.$new();
    template = $templateCache;
  }));

  it('Replaces the element with the appropriate content', function() {
    scope.word = {};
    scope.word.wordIntroductions = [];
    scope.word.wordIntroductions[0] = {};

    scope.word.wordOriginal = "Original word";
    scope.word.wordTranslation = "Word meaning";
    scope.word.wordTransliteration = "transliterated word";
    scope.word.wordIntroductions[0].introTextTransliteration = "Text with HTML";

    var wordBubbleDirective = '<word-bubble word-transliteration="{{word.wordTransliteration}}" word-translation="{{word.wordTranslation}}" transliteration-intro="{{word.wordIntroductions[0].introTextTransliteration}}" word-original="{{word.wordOriginal}}"></word-bubble>';
    element =  angular.element(wordBubbleDirective);

    template.put('/user-js/common/templates/words/wordBubble.html', '<div> {{wordOriginal}} {{wordTransliteration}} {{wordTranslation}} {{transliterationIntro}} </div>');
    $compile(element)(scope);
    scope.$apply();
    
    expect(element.html()).toContain('<div class="ng-binding"> Original word transliterated word Word meaning Text with HTML </div>');
  });
});