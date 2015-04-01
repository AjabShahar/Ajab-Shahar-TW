'use strict';

filterModule.directive("songFilterCategory", function() {

    return {
        restrict:'EA',
        templateUrl:'/user/js/common/templates/songs/songFilterCategory.html',
        scope:{
            criteriaList:"=",
            selectHandler:"=",
            deselectHandler:"=",
            clearFilters:"="
        },

        link:function(scope,element,attrs){
            scope.criteriaClicked = function(criteria){
                //console.log("criteria clicked : ",criteria)
                criteria.isActive = true;
                scope.selectHandler(criteria);
            };

            scope.criteriaRemoved = function(criteria){
                //console.log("criteria removed : ",criteria)
                scope.deselectHandler(criteria);
            };

            scope.resetFilters = function(){
                //console.log("reset clicked")

                scope.clearFilters();
            }
        }
    }
});