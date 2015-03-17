var gatheringContentService = function ($http) {

  var saveGathering = function(gathering){
    return $http.post('/api/gatherings', gathering);
  };

  var getGathering = function (gatheringId) {
    return $http.get('/api/gatherings/' + gatheringId);
  };

  var getAllGatherings = function () {
    return $http.get('/api/gatherings');
  };

  return {
    saveGathering:saveGathering,
    getGathering: getGathering,
    getAllGatherings: getAllGatherings
  };
};