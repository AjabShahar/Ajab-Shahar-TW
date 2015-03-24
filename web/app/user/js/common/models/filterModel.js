var Ajabshahar = Ajabshahar || {};
Ajabshahar.user = Ajabshahar.user || {};
Ajabshahar.user.FilterModel = function(filterCategoryNames){
    var self = {};
    self.selectedFilters = [];

    var createFilterCategory = function(filterCategoryName){
        return new Ajabshahar.user.FilterCategory(filterCategoryName);
    }
    self.filterCategories = filterCategoryNames.map(createFilterCategory);

    self.getSelectedFilters = function(){
      return self.selectedFilters;
    }

    self.removeFilter = function(filterItem){
        var indexToRemove = -1;
        for(var i=0;i<self.selectedFilters.length;i++){
            if(_.isEqual(filterItem,self.selectedFilters[i])){
                indexToRemove = i;
            }
        }
        if(indexToRemove !=-1){
            self.selectedFilters.splice(indexToRemove,1);
        }
    }

    self.addFilter = function(filterItem){
       self.selectedFilters.push(filterItem);
    }

    self.getFilterItems = function(category){

    }

    self.clearFilters = function(){

    }


    return self;

}

