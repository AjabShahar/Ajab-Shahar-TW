"use strict";

angular.module('utilities').directive('onNgRepeatEnd',function($timeout){
    return {
        link:function(scope,elem,attr){
            if(scope.$last){
                $timeout(function(){
                    scope[attr.onNgRepeatEnd]();
                });
            }
        }
    }
});