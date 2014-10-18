'use strict';

describe("LandingPages controller Specs", function(){
	var scope, q, controller;
	var contentService = {
	    getLandingPageThumbnails:function(){}
	};

    var getPromise = function(response) {
        response = response || '';
        var deferred = q.defer();
        deferred.resolve(response);
        return deferred.promise;
    };

    beforeEach(inject(function($q) {
        q = $q;
    }));

	beforeEach(inject(function (_$rootScope_, _$controller_) {
    	scope = _$rootScope_.$new();
    	controller = _$controller_;
    }));

});