var wordsFeaturedContentController = function($scope,contentService, $location, $window){
	$scope.init = function(){
        contentService.getWordsLandingPageContent().then(function(result){
            $scope.words = result.data.words;
        });
	};

	$scope.setPageHeight = function(){
    var homepageContent = jQuery('.' + 'homepage-content')[0];
    var pxLayers = jQuery('.' + 'pxLayers');

    _.each(pxLayers, function(pxLayer){
        pxLayer.style.height = (homepageContent.offsetHeight+400)+"px";
    });
    homepageContent.style.height = (homepageContent.offsetHeight+300)+"px";
    };

	$scope.init();
};

wordsFeaturedContentApp.controller('wordsFeaturedContentController',['$scope','contentService','$location', '$window',wordsFeaturedContentController]);
