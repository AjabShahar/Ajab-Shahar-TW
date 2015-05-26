angular.module("people").service("peopleService", ["$http", function ($http) {

    var getPeople = function () {
        return $http.get("/api/people");
    };

    return {
        getPeople: getPeople
    }
}]);