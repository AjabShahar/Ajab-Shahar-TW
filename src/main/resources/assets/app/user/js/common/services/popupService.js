var popupService = function (){
    shouldBeOpen = {};
    popups = [];
//    isThumbnailOpen = false;

    open = function(id){
        shouldBeOpen[id] = true;
//        isThumbnailOpen = true;
//        isThumbnailOpen;
    }

    onClose = function(id){
        shouldBeOpen[id] = false;
//        isThumbnailOpen = false;
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
        init:init
//        isThumbnailOpen: isThumbnailOpen
    };
}
popupSupport.factory('popupService',[popupService]);