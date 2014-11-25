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

    select = function(oldIndex, newIndex){
        shouldBeOpen[popups[oldIndex]] = false;
        if(newIndex>popups.length)
            newIndex = 0;
        shouldBeOpen[popups[newIndex]] = true;
    }

    init = function(id){
        popups[popups.length] = id;
    }

    return {
        open: open,
        onClose: onClose,
        select: select,
        shouldShow: shouldShow,
        isClosed: isClosed,
        init:init,
    };
}
popupSupport.factory('popupService',[popupService]);