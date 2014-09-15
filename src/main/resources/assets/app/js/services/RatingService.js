'use strict';

define('services/RatingService', [''], function() {
  var factory = function() {
    var ratingService = {
      phoneRatings: {},
      setRating: function(phoneId, rating) {
        if (!phoneId) return;
        this.phoneRatings[phoneId] = rating;
      },
      getRating: function(phoneId) {
        return this.phoneRatings[phoneId];
      }
    };
    return ratingService;
  };
  return factory;
});
