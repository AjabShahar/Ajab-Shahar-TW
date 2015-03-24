describe ("Filter model",function(){

   var filter;
   beforeEach(inject(function(){
       var categoriesList = ["singer","poet","word"];
       filter = new Ajabshahar.user.FilterModel(categoriesList);

   }));

  it("should create filter categories",function(){

      expect(filter.filterCategories.length).toBe(3);
      expect(filter.filterCategories[0].name).toBe("singer");
      expect(filter.filterCategories[1].name).toBe("poet");
      expect(filter.filterCategories[2].name).toBe("word");
  });

  it("should add filter item",function(){
     var items = [];
     items.push(new Ajabshahar.user.FilterItem(["parvati"],"singer",1));
     items.push(new Ajabshahar.user.FilterItem(["jaagna"],"word",2));

     filter.addFilter(items[0]);
     filter.addFilter(items[1]);

     expect(filter.getSelectedFilters().length).toBe(2);
     expect(filter.getSelectedFilters()[0].names[0]).toBe("parvati");
     expect(filter.getSelectedFilters()[1].names[0]).toBe("jaagna");
  });

  it("should remove filter item",function(){
      var items = [];
      items.push(new Ajabshahar.user.FilterItem(["parvati"],"singer",1));
      items.push(new Ajabshahar.user.FilterItem(["jaagna"],"word",2));

      filter.addFilter(items[0]);
      filter.addFilter(items[1]);

      filter.removeFilter(items[1]);

     expect(filter.getSelectedFilters().length).toBe(1);

  });

})