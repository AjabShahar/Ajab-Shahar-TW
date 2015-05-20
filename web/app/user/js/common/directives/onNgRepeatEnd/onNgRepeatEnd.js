"use strict";

angular.module('utilities').directive('onNgRepeatEnd',function(){
    return {
        link:function(scope,elem,attr){
            if(scope.$last){
                scope[attr.onNgRepeatEnd]();
            }
        }
    }
});