describe("Filter model", function () {

    var filter;
    beforeEach(inject(function () {
        filter = new Ajabshahar.user.FilterModel();

    }));


    xit("should add filter item", function () {
        var items = [];
        items.push(new Ajabshahar.user.FilterItem(["parvati"], "singer", 1));
        items.push(new Ajabshahar.user.FilterItem(["jaagna"], "word", 2));

        filter.addFilter(items[0]);
        filter.addFilter(items[1]);

        expect(filter.getSelectedFilters().length).toBe(2);
        expect(filter.getSelectedFilters()[0].names[0]).toBe("parvati");
        expect(filter.getSelectedFilters()[1].names[0]).toBe("jaagna");
    });

    xit("should remove filter item", function () {
        var items = [];
        items.push(new Ajabshahar.user.FilterItem(["parvati"], "singer", 1));
        items.push(new Ajabshahar.user.FilterItem(["jaagna"], "word", 2));

        filter.addFilter(items[0]);
        filter.addFilter(items[1]);

        filter.removeFilter(items[1]);

        expect(filter.getSelectedFilters().length).toBe(1);

    });

    function createSong(title,gathering,singers,words){
        return {
            title:{
                english:title
            },
            gathering:gathering,
            singers:singers,
            words:words
        }
    }

    it("should filter items based on the filter criteria when field to be filtered is 1 level deep",function(){
        var filterCriteria = [{
            name: "gathering",
            value:"rajasthan"
        }];

        filter.filterCriteria = filterCriteria;

        var songs =[
            createSong("kichchu din mone","rajasthan",[{name:"parvati"}],[{english:"moon",translit:"chand"}]),
            createSong("bhajan ro gudak ra jao","delhi",[{name:"laxmi"}],[{english:"sun",translit:"suraj"}]),
            createSong("nit khair","rajasthan",[{name:"moon"}],[{english:"moon",translit:"chand"}])
        ];
        var result = filter.filter(songs);
        expect (result.length).toBe(2);
        expect(result[0].title.english).toBe("kichchu din mone");
        expect(result[1].title.english).toBe("nit khair");
    });

    it("should filter items based on the filter criteria when field to be filtered is 2 level deep",function(){
        var filterCriteria = [{
            name: "title.english",
            value:"nit khair"
        }];

        filter.filterCriteria = filterCriteria;

        var songs =[
            createSong("kichchu din mone","rajasthan",[{name:"parvati"}],[{english:"moon",translit:"chand"}]),
            createSong("bhajan ro gudak ra jao","delhi",[{name:"laxmi"}],[{english:"sun",translit:"suraj"}]),
            createSong("nit khair","rajasthan",[{name:"moon"}],[{english:"moon",translit:"chand"}])
        ];
        var result = filter.filter(songs);
        expect (result.length).toBe(1);
        expect(result[0].title.english).toBe("nit khair");
    });

    it("should filter items based on the filter criteria when field to be matched is an item in a collection",function(){
        var filterCriteria = [{
            name: "words[].english",
            value:"moon"
        }];

        filter.filterCriteria = filterCriteria;

        var songs =[
            createSong("kichchu din mone","rajasthan",[{name:"parvati"}],[{english:"moon",translit:"chand"}]),
            createSong("bhajan ro gudak ra jao","delhi",[{name:"laxmi"}],[{english:"sun",translit:"suraj"}]),
            createSong("nit khair","rajasthan",[{name:"guy"}],[{english:"moon",translit:"chand"},{english:"sun",translit:"suraj"}])
        ];
        var result = filter.filter(songs);
        expect (result.length).toBe(2);
        expect(result[0].title.english).toBe("kichchu din mone");
        expect(result[1].title.english).toBe("nit khair");
    });

    it("should filter items based on the filter criteria when field to be matched is an item is nested a collection",function(){
        var filterCriteria = [{
            name: "singers[].occupation[]",
            value:"dancer"
        }];

        filter.filterCriteria = filterCriteria;

        var songs =[
            createSong("kichchu din mone","rajasthan",[{name:"parvati"}],[{english:"moon",translit:"chand"}]),
            createSong("bhajan ro gudak ra jao","delhi",[{name:"laxmi","occupation":["poet","writer"]}],[{english:"sun",translit:"suraj"}]),
            createSong("nit khair","rajasthan",[{name:"guy","occupation":["poet","dancer"]}],[{english:"moon",translit:"chand"},{english:"sun",translit:"suraj"}])
        ];
        var result = filter.filter(songs);
        expect (result.length).toBe(1);
        expect(result[0].title.english).toBe("nit khair");
    });

    it("should filter items based on the filter criteria when field to be matched is an string in a collection",function(){
        var filterCriteria = [{
            name: "gathering[]",
            value:"delhi"
        }];

        filter.filterCriteria = filterCriteria;

        var songs =[
            createSong("kichchu din mone",["rajasthan","delhi"],[{name:"parvati"}],[{english:"moon",translit:"chand"}]),
            createSong("bhajan ro gudak ra jao",["delhi"],[{name:"laxmi","occupation":["poet","writer"]}],[{english:"sun",translit:"suraj"}]),
            createSong("nit khair",["rajasthan"],[{name:"guy","occupation":["poet","dancer"]}],[{english:"moon",translit:"chand"},{english:"sun",translit:"suraj"}])
        ];
        var result = filter.filter(songs);
        expect (result.length).toBe(2);
        expect(result[0].title.english).toBe("kichchu din mone");
        expect(result[1].title.english).toBe("bhajan ro gudak ra jao");
    });

    it("should chain multiple filters",function(){
        var filterCriteria = [{
            name:"gathering",
            value:"delhi"
        },{
             name:"singers[].name",
             value:"parvati"
        }];

        filter.filterCriteria = filterCriteria;
        var songs =[
            createSong("kichchu din mone","delhi",[{name:"parvati"}],[{english:"moon",translit:"chand"}]),
            createSong("bhajan ro gudak ra jao","delhi",[{name:"laxmi","occupation":["poet","writer"]}],[{english:"sun",translit:"suraj"}]),
            createSong("nit khair","rajasthan",[{name:"guy","occupation":["poet","dancer"]}],[{english:"moon",translit:"chand"},{english:"sun",translit:"suraj"}])
        ];

        var result = filter.filter(songs);

        expect(result.length).toBe(1);
        expect(result[0].title.english).toBe("kichchu din mone");
    });

    it("should chain multiple filters, even though result of combination is 0",function(){
        var filterCriteria = [{
            name:"gathering",
            value:"delhi"
        },{
            name:"singers[].name",
            value:"parvati"
        }];

        filter.filterCriteria = filterCriteria;
        var songs =[
            createSong("kichchu din mone","rajasthan",[{name:"parvati"}],[{english:"moon",translit:"chand"}]),
            createSong("bhajan ro gudak ra jao","delhi",[{name:"laxmi","occupation":["poet","writer"]}],[{english:"sun",translit:"suraj"}]),
            createSong("nit khair","rajasthan",[{name:"guy","occupation":["poet","dancer"]}],[{english:"moon",translit:"chand"},{english:"sun",translit:"suraj"}])
        ];

        var result = filter.filter(songs);

        expect(result.length).toBe(0);

    });

    it("should be able to clear all filters",function(){
        var filterCriteria = [{
            name:"gathering",
            value:"delhi"
        },{
            name:"singers[].name",
            value:"parvati"
        }];

        filter.filterCriteria = filterCriteria;
        var songs =[
            createSong("kichchu din mone","rajasthan",[{name:"parvati"}],[{english:"moon",translit:"chand"}]),
            createSong("bhajan ro gudak ra jao","delhi",[{name:"laxmi","occupation":["poet","writer"]}],[{english:"sun",translit:"suraj"}]),
            createSong("nit khair","rajasthan",[{name:"guy","occupation":["poet","dancer"]}],[{english:"moon",translit:"chand"},{english:"sun",translit:"suraj"}])
        ];

        var result = filter.filter(songs);
        expect(result.length).toBe(0);

        filter.clearFilters();

        result = filter.filter(songs);

        expect(result.length).toBe(3);
    });


    xit("test object recursion",function(){
       var titlePath = "song.title.name";

        var item = {
            song:{
                title:{
                    name:"nice"
                },
                singer:"mukhtiyar"
            },
            words:[
                {
                    name:"jaagna",
                    people:[{
                        name:"roy"
                    }]
                }
            ]
        };

        var value = traverse(item,titlePath);

        function traverse (item,path){
            var pathIndexes = path.split(".");
            var pathIndex = pathIndexes[0];
            if(pathIndex.indexOf('[]')>0){
                pathIndex = pathIndex.substring(0,pathIndex.indexOf('['));
                return item[pathIndex].map(function(child){
                    var result =traverse(child,path.substring(path.indexOf('.')+1))
                    console.log(result);
                    return result;
                })
            }
            else{
                if(pathIndexes.length>1 && item[pathIndexes[0]]){
                    return traverse(item[pathIndexes[0]], path.substring(path.indexOf('.')+1))
                }
            }
            return item[pathIndexes[0]];
        }
        expect(value).toBe("nice");

        var wordPath = "words[].name";
        value = traverse(item,wordPath);
        expect(value[0]).toBe("jaagna");

    })


});