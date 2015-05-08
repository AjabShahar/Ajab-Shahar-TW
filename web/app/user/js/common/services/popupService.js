'use strict';

popupSupport.factory('popupService', function () {
    var self = {};
    var items = {};
    var selectedIndex = -1;

    self.select = function(index){
        items[index].selected = true;
        selectedIndex = index;
        return items[index];
    };

    self.isSelected = function(index){
        return items[index].selected;
    };

    self.addItem = function(item,index){
        items[index] = {
            content: item,
            selected: false,
            index: index
        }
    };
    self.deselect = function(){
        if(selectedIndex!= -1){
            items[selectedIndex].selected = false;
            selectedIndex = -1;
        }
    };

    self.getItems = function(){
        return _.values(items);
    };

    self.count = function(){
        return _.values(items).length;
    };

    self.getSelected = function(){
        return selectedIndex != -1 ? items[selectedIndex]: null;
    };

    self.reset = function(){
        items = [];
    };

    return self;
});
