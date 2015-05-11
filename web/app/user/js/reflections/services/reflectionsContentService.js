angular.module("reflection").service("reflectionsContentService",["$http",function($http) {
    var getAllReflections = function () {
        return $http.get('/api/reflections/completeInfo?content=featured');
    };

    var getReflection = function (id) {
        return $http.get('/api/reflections/edit?id=' + id);
    };

    var getPublishedReflections = function(){
       return $http.get('/api/reflections/completeInfo?content=authoringComplete');
    };

    return {
        getAllReflections: getAllReflections,
        getReflection: getReflection,
        getPublishedReflections: getPublishedReflections
    };
}]);
