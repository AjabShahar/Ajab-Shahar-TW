describe('The filterByScroll filter', function () {
  'use strict';

  var $filter;

  beforeEach(function () {
    module('filterModule');

    inject(function (_$filter_) {
      $filter = _$filter_;
    });
  });

  it('should return elements whose index is less than specified index', function () {
    // Arrange.
    var string = 'hello world', result;

    var element1 = {};
    var element2 = {};
    var element3 = {};
    // Act.
    result = $filter('filterByScroll')(
    [element1,element2,element3], 2);

    // Assert.
    expect(result).toEqual([element1]);
  });

  it('should return all elements if number of elements less than specified index', function () {
    // Arrange.
    var string = 'hello world', result;

    var element1 = {};
    var element2 = {};
    var element3 = {};
    // Act.
    var list = [element1,element2,element3];
    result = $filter('filterByScroll')(list, 5);

    // Assert.
    expect(result).toEqual(list);
  });

  it('should return all elements if number of elements equal to specified index', function () {
    // Arrange.
    var string = 'hello world', result;

    var element1 = {};
    var element2 = {};
    var element3 = {};
    // Act.
    var list = [element1,element2,element3];
    result = $filter('filterByScroll')(list, 3);

    // Assert.
    expect(result).toEqual(list);
  });
});
