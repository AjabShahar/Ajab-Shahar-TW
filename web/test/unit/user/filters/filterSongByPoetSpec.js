describe('The filterSongByPoet filter', function () {
  'use strict';

  var $filter;

  beforeEach(function () {
    module('filterModule');

    inject(function (_$filter_) {
      $filter = _$filter_;
    });
  });

  it('should find songs with given poet', function () {
    // Arrange.
    var string = 'hello world', result;

    var song1 = {name:'song1',searchableCriteria:{poets:[{id:1},{id:2},{id:3}]}};
    var song2 = {name:'song2',searchableCriteria:{poets:[{id:4},{id:5},{id:6}]}};
    var song3 = {name:'song3',searchableCriteria:{poets:[{id:3}]}};
    // Act.
    result = $filter('filterSongByPoet')(
    [song1,song2,song3], {id:3});

    // Assert.
    expect(result).toEqual([song1,song3]);
  });

  it('should return empty if no song with given poet', function () {
    // Arrange.
    var string = 'hello world', result;

    var song1 = {name:'song1',searchableCriteria:{poets:[{id:1},{id:2},{id:3}]}};
    var song2 = {name:'song2',searchableCriteria:{poets:[{id:4},{id:5},{id:6}]}};
    var song3 = {name:'song3',searchableCriteria:{poets:[{id:3}]}};
    // Act.
    result = $filter('filterSongByPoet')(
    [song1,song2,song3], {id:10});

    // Assert.
    expect(result).toEqual([]);
  });

  it('should return all songs if criteria empty', function () {
    // Arrange.
    var string = 'hello world', result;

    var song1 = {name:'song1',searchableCriteria:{poets:[{id:1},{id:2},{id:3}]}};
    var song2 = {name:'song2',searchableCriteria:{poets:[{id:4},{id:5},{id:6}]}};
    var song3 = {name:'song3',searchableCriteria:{poets:[{id:3}]}};
    var songs = [song1,song2,song3];
    result = $filter('filterSongByPoet')(songs, '');
    // Assert.
    expect(result).toEqual(songs);
  });

  it('should return all songs if criteria null', function () {
    // Arrange.
    var string = 'hello world', result;

    var song1 = {name:'song1',searchableCriteria:{poets:[{id:1},{id:2},{id:3}]}};
    var song2 = {name:'song2',searchableCriteria:{poets:[{id:4},{id:5},{id:6}]}};
    var song3 = {name:'song3',searchableCriteria:{poets:[{id:3}]}};
    var songs = [song1,song2,song3];
    result = $filter('filterSongByPoet')(songs, null);
    // Assert.
    expect(result).toEqual(songs);
  });
});
