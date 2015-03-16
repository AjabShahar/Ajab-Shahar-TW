describe('The filterSongBySinger filter', function () {
  'use strict';

  var $filter;

  beforeEach(function () {
    module('filterModule');

    inject(function (_$filter_) {
      $filter = _$filter_;
    });
  });

  it('should find songs with given singer', function () {
    // Arrange.
    var string = 'hello world', result;

    var song1 = {name:'song1',searchableCriteria:{singers:[{id:1},{id:2},{id:3}]}};
    var song2 = {name:'song2',searchableCriteria:{singers:[{id:4},{id:5},{id:6}]}};
    var song3 = {name:'song3',searchableCriteria:{singers:[{id:3}]}};
    // Act.
    result = $filter('filterSongBySinger')(
    [song1,song2,song3], {id:3});

    // Assert.
    expect(result).toEqual([song1,song3]);
  });

  it('should return empty if no song with given singer', function () {
    // Arrange.
    var string = 'hello world', result;

    var song1 = {name:'song1',searchableCriteria:{singers:[{id:1},{id:2},{id:3}]}};
    var song2 = {name:'song2',searchableCriteria:{singers:[{id:4},{id:5},{id:6}]}};
    var song3 = {name:'song3',searchableCriteria:{singers:[{id:3}]}};
    // Act.
    result = $filter('filterSongBySinger')(
    [song1,song2,song3], {id:10});

    // Assert.
    expect(result).toEqual([]);
  });

  it('should return all songs if criteria empty', function () {
    // Arrange.
    var string = 'hello world', result;

    var song1 = {name:'song1',searchableCriteria:{singers:[{id:1},{id:2},{id:3}]}};
    var song2 = {name:'song2',searchableCriteria:{singers:[{id:4},{id:5},{id:6}]}};
    var song3 = {name:'song3',searchableCriteria:{singers:[{id:3}]}};
    var songs = [song1,song2,song3];
    result = $filter('filterSongBySinger')(songs, '');
    // Assert.
    expect(result).toEqual(songs);
  });

  it('should return all songs if criteria null', function () {
    // Arrange.
    var string = 'hello world', result;

    var song1 = {name:'song1',searchableCriteria:{singers:[{id:1},{id:2},{id:3}]}};
    var song2 = {name:'song2',searchableCriteria:{singers:[{id:4},{id:5},{id:6}]}};
    var song3 = {name:'song3',searchableCriteria:{singers:[{id:3}]}};
    var songs = [song1,song2,song3];
    result = $filter('filterSongBySinger')(songs, null);
    // Assert.
    expect(result).toEqual(songs);
  });
});
