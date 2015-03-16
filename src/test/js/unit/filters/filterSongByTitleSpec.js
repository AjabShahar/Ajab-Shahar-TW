describe('The filterSongByTitle filter', function () {
  'use strict';

  var $filter;

  beforeEach(function () {
    module('filterModule');

    inject(function (_$filter_) {
      $filter = _$filter_;
    });
  });

  it('should find songs which start with given alphabet', function () {
    // Arrange.
    var string = 'hello world', result;

    var song1 = {englishTranslation:'abc'};
    var song2 = {englishTranslation:'def'};
    var song3 = {englishTranslation:'aaa'};
    // Act.
    result = $filter('filterSongByTitle')(
    [song1,song2,song3], {alphabet:'a'});

    // Assert.
    expect(result).toEqual([song1,song3]);
  });
});