var popupService = function (){
    shouldBeOpen={};

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

    return {
        open: open,
        onClose: onClose,
        shouldShow: shouldShow,
        isClosed: isClosed,
    };
}
popupSupport.factory('popupService',[popupService]);