var popupService = function (){
    shouldBeOpen = {};
    popups = [];

    open = function(id){
        shouldBeOpen[id] = true;
    }

    onClose = function(id){
        shouldBeOpen[id] = false;
    }

    shouldShow = function(id){
        return shouldBeOpen[id];
    }

    isClosed = function(id){
        return !shouldBeOpen[id];
    }

    select = function(index){
        _.each(popups,function(id){
            shouldBeOpen[id] = false;
        });
        shouldBeOpen[popups[index]] = true;
    }

    init = function(id){
        popups[popups.length] = id;
    }

    return {
        open: open,
        onClose: onClose,
        shouldShow: shouldShow,
        isClosed: isClosed,
        init:init,
    };
}
popupSupport.factory('popupService',[popupService]);