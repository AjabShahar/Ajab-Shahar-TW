var popupService = function (){
    shouldBeOpen = {};
    orderOfPopups = [];

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

    next = function(index){
        var oldIndex = index;
        var newIndex = (index+1==orderOfPopups.length)?0:index+1;

        var oldId = orderOfPopups[oldIndex];
        var newId = orderOfPopups[newIndex];

        shouldBeOpen[oldId] = false;
        shouldBeOpen[newId] = true;
    }

    prev = function(index){
        var oldIndex = index;
        var newIndex = (index-1==-1)?orderOfPopups.length-1:index-1;

        var oldId = orderOfPopups[oldIndex];
        var newId = orderOfPopups[newIndex];

        shouldBeOpen[oldId] = false;
        shouldBeOpen[newId] = true;
    }

    init = function(index,id){
        orderOfPopups[index] = id;
    }

    return {
        open: open,
        onClose: onClose,
        shouldShow: shouldShow,
        isClosed: isClosed,
        next:next,
        prev:prev,
        init:init,
    };
}
popupSupport.factory('popupService',[popupService]);