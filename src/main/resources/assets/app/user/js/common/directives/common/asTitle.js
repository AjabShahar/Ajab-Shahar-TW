'use strict';

filterModule.directive("asTitle", function() {
    return {
        restrict: 'E',
        replace:true,
        transclude: true,
        scope: {
            currentAlphabetFilter:'=',
        },
        templateUrl:'/user-js/common/templates/common/asTitle.html',
        controller: function($scope,$rootScope) {
            $scope.contentTextRepresentation = 'Transliteration';
            $scope.alphabetFilters = [
                {alphabet:'A',isSelected:false},
                {alphabet:'B',isSelected:false},
                {alphabet:'C',isSelected:false},
                {alphabet:'D',isSelected:false},
                {alphabet:'E',isSelected:false},
                {alphabet:'F',isSelected:false},
                {alphabet:'G',isSelected:false},
                {alphabet:'H',isSelected:false},
                {alphabet:'I',isSelected:false},
                {alphabet:'J',isSelected:false},
                {alphabet:'K',isSelected:false},
                {alphabet:'L',isSelected:false},
                {alphabet:'M',isSelected:false},
                {alphabet:'N',isSelected:false},
                {alphabet:'O',isSelected:false},
                {alphabet:'P',isSelected:false},
                {alphabet:'Q',isSelected:false},
                {alphabet:'R',isSelected:false},
                {alphabet:'S',isSelected:false},
                {alphabet:'T',isSelected:false},
                {alphabet:'U',isSelected:false},
                {alphabet:'V',isSelected:false},
                {alphabet:'W',isSelected:false},
                {alphabet:'X',isSelected:false},
                {alphabet:'Y',isSelected:false},
                {alphabet:'Z',isSelected:false}];

            $scope.selectOriginalTitle = function(){
                if($scope.contentTextRepresentation==='Transliteration')
                    return;
                $scope.contentTextRepresentation = 'Transliteration';
                $scope.clearAlphabetFilter();
                $rootScope.$broadcast('contentTextRepresentation',$scope.contentTextRepresentation);
            }

            $scope.selectEnglishTitle = function(){
                if($scope.contentTextRepresentation==='Translation')
                    return;
                $scope.contentTextRepresentation = 'Translation';
                $scope.clearAlphabetFilter();
                $rootScope.$broadcast('contentTextRepresentation',$scope.contentTextRepresentation);
            }

            $scope.clearAlphabetFilter = function(){
                _.each($scope.alphabetFilters,function(alphabetFilter){
                    alphabetFilter.isSelected = false;
                });
                $scope.currentAlphabetFilter = '';
            }

            $scope.filterSongsOnLetter = function(currentAlphabetFilter){
                $scope.clearAlphabetFilter();
                currentAlphabetFilter.isSelected = true;
                currentAlphabetFilter.contentTextRepresentation = $scope.contentTextRepresentation;
                $scope.currentAlphabetFilter = currentAlphabetFilter;
            }

            $scope.isSelected = function(alphabet){
                return alphabet.isSelected ? 'active' : '';
            }

            $scope.$watch('currentAlphabetFilter', function(newValue, oldValue) {
                if(newValue==oldValue)
                    return;
                if(newValue==='')
                    $scope.clearAlphabetFilter();
            });
        }
    }
});