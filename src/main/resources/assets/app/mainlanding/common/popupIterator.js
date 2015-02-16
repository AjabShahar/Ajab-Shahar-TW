var AjabShahar = AjabShahar|| {};
AjabShahar.PopUpIterator  = function(itemsCollection){
    var self = this;
    self.items = itemsCollection || [];
    self.currentIndex = 0;
    
    self.select = function(index){
        if(index < self.items.length){
            self.selection = self.items[index];
            self.currentIndex = index;
        }
    };
    self.getSelection = function(){
        return self.selection;  
    };
    self.next = function(){
        if(self.currentIndex +1 < self.items.length){
            self.select(self.currentIndex +1);
        }
        return self.getSelection();
    };
    self.previous = function(){
        if(self.currentIndex - 1 >= 0 ){
            self.select(self.currentIndex -1);
        }
        return self.getSelection();
    };
    
    return self;
};