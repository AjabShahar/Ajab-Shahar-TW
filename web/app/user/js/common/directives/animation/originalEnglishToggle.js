animationModule.directive("originalEnglishToggle", function(){
   return {
       restrict : 'E',
       templateUrl: '/user/js/common/templates/common/originalEnglishToggle.html',
       controller: function ($scope, $rootScope) {
           $rootScope.contentTextRepresentation = 'Transliteration';
           $scope.isOriginalTitle = true;
           $scope.isEnglishTitle = false;

           $scope.selectOriginalTitle = function () {
               if ($rootScope.contentTextRepresentation === 'Transliteration')
                   return;
               $rootScope.contentTextRepresentation = 'Transliteration';
               $scope.isOriginalTitle = true;
               $scope.isEnglishTitle = false;
               //$rootScope.$broadcast('contentTextRepresentation', $rootScope.contentTextRepresentation);
           };

           $scope.selectEnglishTitle = function () {
               if ($rootScope.contentTextRepresentation === 'Translation')
                   return;
               $rootScope.contentTextRepresentation = 'Translation';
               $scope.isEnglishTitle = true;
               $scope.isOriginalTitle = false;
               //$rootScope.$broadcast('contentTextRepresentation', $rootScope.contentTextRepresentation);
           };
       }
   };
});