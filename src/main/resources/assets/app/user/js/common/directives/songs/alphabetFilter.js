'use strict';

filterModule.directive("alphabetFilter", function() {
    return {
        restrict: 'E',
        scope: {
            activeLetter:'=',
        },
        templateUrl:'/user/js/common/templates/songs/alphabetFilter.html',
        controller: function($scope) {
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

            $scope.clearAlphabetFilter = function(){
                _.each($scope.alphabetFilters,function(alphabetFilter){
                    alphabetFilter.isSelected = false;
                });
            }

            $scope.filterSongsOnLetter = function(currentAlphabetFilter){
                $scope.clearAlphabetFilter();
                $scope.activeLetter = currentAlphabetFilter.alphabet;
                currentAlphabetFilter.isSelected = true;
            }

            $scope.isSelected = function(alphabet){
                return alphabet.isSelected ? 'active' : '';
            }

            $scope.$watch('activeLetter', function(newValue, oldValue) {
                if(newValue==oldValue)
                    return;
                if(newValue==='')
                    $scope.clearAlphabetFilter();
            });
        }
    }
});