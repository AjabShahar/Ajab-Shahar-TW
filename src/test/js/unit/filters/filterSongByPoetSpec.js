describe('The filterSongByPoet filter', function () {
  'use strict';

  var $filter;

  beforeEach(function () {
    module('filterModule');

    inject(function (_$filter_) {
      $filter = _$filter_;
    });
  });

  it('should capitalize a string', function () {
    // Arrange.
    var string = 'hello world', result;

    var song1 = {name:'song1',searchableCriteria:{poets:[{id:1},{id:2},{id:3}]}};
    var song2 = {name:'song2',searchableCriteria:{poets:[{id:4},{id:5},{id:6}]}};
    var song3 = {name:'song3',searchableCriteria:{poets:[{id:3}]}};
    // Act.
    result = $filter('filterSongByPoet')(
    [song1,song2,song3], {id:3,name:'bbb'});

    // Assert.
    expect(result).toEqual([song1,song3]);
  });
});