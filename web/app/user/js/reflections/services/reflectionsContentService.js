angular.module("reflection").service("reflectionsContentService",["$http",function($http) {
    var getAllReflections = function () {
        return $http.get('/api/reflections/getCompleteInfo?content=featured');
    };

    var getReflection = function (id) {
        return $http.get('/api/reflections/edit?id=' + id);
    };

    return {
        getAllReflections: getAllReflections,
        getReflection: getReflection
    };
}]);
