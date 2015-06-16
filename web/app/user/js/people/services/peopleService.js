angular.module("people").service("peopleService", ["$http", function ($http) {

    var getPeople = function () {
        return $http.get("/api/people");
    };

    var getSongs = function(personId){
        return $http.get("/api/songs?personId="+personId);
    };

    var getReflections = function(personId){
        return $http.get("/api/reflections/summaries?personId="+personId);
    };

    var getWords = function(personId){
        return $http.get("/api/words/summary?personId="+personId);
    };

    var getPerson = function(personId){
        return $http.get("/api/people/"+personId);
    };

    return {
        getPeople: getPeople,
        getSongs: getSongs,
        getReflections: getReflections,
        getWords: getWords,
        getPerson: getPerson
    }
}]);