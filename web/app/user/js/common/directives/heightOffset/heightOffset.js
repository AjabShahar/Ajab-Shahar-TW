"use strict";

angular.module('utilities').directive('heightOffset',function(){
    return {
        scope:{},
        link:function(scope,element,attrs){
            var offset = parseInt(attrs.heightOffset);
            $(element).height($(window).height() + offset);
        }
    }
});

angular.module('utilities').directive('ellipsis',function(){
    return {
        scope:{},
        link:function(scope,element,attrs){
            $(element).readmore({
                moreLink: '<a href="#" class="read-link">see more</a>',
                lessLink: '<a href="#" class="read-link">see less</a>'

            });
        }
    }
});