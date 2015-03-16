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
    var song3 = {englishTranslation:'Aaa'};
    // Act.
    result = $filter('filterSongByTitle')(
    [song1,song2,song3], {alphabet:'a'});

    // Assert.
    expect(result).toEqual([song1,song3]);
  });

  it('should find songs which start with given alphabet of englishTransliteration', function () {
    // Arrange.
    var string = 'hello world', result;

    var song1 = {englishTransliteration:'abc'};
    var song2 = {englishTransliteration:'def'};
    var song3 = {englishTransliteration:'Aaa'};
    // Act.
    result = $filter('filterSongByTitle')(
    [song1,song2,song3], {alphabet:'a',contentTextRepresentation:'Transliteration'});

    // Assert.
    expect(result).toEqual([song1,song3]);
  });

    it('should return all songs if criteria empty', function () {
      // Arrange.
      var string = 'hello world', result;

      var song1 = {name:'song1'};
      var song2 = {name:'song2'};
      var song3 = {name:'song3'};
      var songs = [song1,song2,song3];
      result = $filter('filterSongByTitle')(songs, '');
      // Assert.
      expect(result).toEqual(songs);
    });

    it('should return all songs if criteria null', function () {
      // Arrange.
      var string = 'hello world', result;

      var song1 = {name:'song1'};
      var song2 = {name:'song2'};
      var song3 = {name:'song3'};
      var songs = [song1,song2,song3];
      result = $filter('filterSongByTitle')(songs, null);
      // Assert.
      expect(result).toEqual(songs);
    });

});
