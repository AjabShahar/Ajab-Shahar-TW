var allReflectionsController = function($scope,$window,reflectionsContentService,reflectionMapper){
    $scope.reflections=[];
    $scope.allReflections = null;
    $scope.totalFilteredReflections = 0;
    $scope.detailsService = {open:function(id){
                                          $window.location.href = '/reflections/details.html?id='+id;
                                      }};
    $scope.activeLetter = '';
    $scope.scrollIndex = 12;
    $scope.reflectionCount = 0;
    $scope.reflectionsList = [];

    var i = 0;

    $scope.getAllReflections = function(){
        reflectionsContentService.getAllReflections().then(function(reflectionsList){
            $scope.reflections = reflectionMapper.getThumbnails(reflectionsList.data.reflections);
            $scope.reflectionCount = reflectionsList.data.reflections.length;
        });
    }

    $scope.reflectionStartsWithComparator = function (actual, expected) {
       if (!$scope.activeLetter && $scope.activeLetter=='') {
          return true;
       }
       return $scope.strStartsWith(actual.englishTransliterationTitle.toUpperCase(),$scope.activeLetter.toUpperCase());
    };

    $scope.loadReflectionFromRange = function(){
        if($scope.scrollIndex>$scope.reflections.length)
            return;
        $scope.scrollIndex += 12;
    }
    $scope.getAllReflections();
};

allReflectionsApp.controller('allReflectionsController',['$scope','$window','reflectionsContentService','reflectionMapper',allReflectionsController]);
