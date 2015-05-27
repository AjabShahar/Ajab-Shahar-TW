describe("The sieve", function () {

    var filter;
    beforeEach(inject(function () {
        filter = new AjabShahar.user.Sieve();

    }));

    function createSong(title, gathering, singers, words) {
        return {
            title: {
                english: title
            },
            gathering: gathering,
            singers: singers,
            words: words
        }
    }

    it("should filter items based on the filter criteria when field to be filtered is 1 level deep", function () {
        var filterCriteria = [{
            name: "gathering",
            value: "rajasthan"
        }];

        filter.filterCriteria = filterCriteria;

        var songs = [
            createSong("kichchu din mone", "rajasthan", [{name: "parvati"}], [{english: "moon", translit: "chand"}]),
            createSong("bhajan ro gudak ra jao", "delhi", [{name: "laxmi"}], [{english: "sun", translit: "suraj"}]),
            createSong("nit khair", "rajasthan", [{name: "moon"}], [{english: "moon", translit: "chand"}])
        ];
        var result = filter.filter(songs);
        expect(result.length).toBe(2);
        expect(result[0].title.english).toBe("kichchu din mone");
        expect(result[1].title.english).toBe("nit khair");
    });

    it("should filter items based on the filter criteria when field to be filtered is 2 level deep", function () {
        var filterCriteria = [{
            name: "title.english",
            value: "nit khair"
        }];

        filter.filterCriteria = filterCriteria;

        var songs = [
            createSong("kichchu din mone", "rajasthan", [{name: "parvati"}], [{english: "moon", translit: "chand"}]),
            createSong("bhajan ro gudak ra jao", "delhi", [{name: "laxmi"}], [{english: "sun", translit: "suraj"}]),
            createSong("nit khair", "rajasthan", [{name: "moon"}], [{english: "moon", translit: "chand"}])
        ];
        var result = filter.filter(songs);
        expect(result.length).toBe(1);
        expect(result[0].title.english).toBe("nit khair");
    });

    it("should filter items based on the filter criteria when field to be matched is an item in a collection", function () {
        var filterCriteria = [{
            name: "words[].english",
            value: "moon"
        }];

        filter.filterCriteria = filterCriteria;

        var songs = [
            createSong("kichchu din mone", "rajasthan", [{name: "parvati"}], [{english: "moon", translit: "chand"}]),
            createSong("bhajan ro gudak ra jao", "delhi", [{name: "laxmi"}], [{english: "sun", translit: "suraj"}]),
            createSong("nit khair", "rajasthan", [{name: "guy"}], [{
                english: "moon",
                translit: "chand"
            }, {english: "sun", translit: "suraj"}])
        ];
        var result = filter.filter(songs);
        expect(result.length).toBe(2);
        expect(result[0].title.english).toBe("kichchu din mone");
        expect(result[1].title.english).toBe("nit khair");
    });

    it("should filter items based on the filter criteria when field to be matched is an item is nested a collection", function () {
        var filterCriteria = [{
            name: "singers[].occupation[]",
            value: "dancer"
        }];

        filter.filterCriteria = filterCriteria;

        var songs = [
            createSong("kichchu din mone", "rajasthan", [{name: "parvati"}], [{english: "moon", translit: "chand"}]),
            createSong("bhajan ro gudak ra jao", "delhi", [{
                name: "laxmi",
                "occupation": ["poet", "writer"]
            }], [{english: "sun", translit: "suraj"}]),
            createSong("nit khair", "rajasthan", [{name: "guy", "occupation": ["poet", "dancer"]}], [{
                english: "moon",
                translit: "chand"
            }, {english: "sun", translit: "suraj"}])
        ];
        var result = filter.filter(songs);
        expect(result.length).toBe(1);
        expect(result[0].title.english).toBe("nit khair");
    });

    it("should filter items based on the filter criteria when field to be matched is an string in a collection", function () {
        var filterCriteria = [{
            name: "gathering[]",
            value: "delhi"
        }];

        filter.filterCriteria = filterCriteria;

        var songs = [
            createSong("kichchu din mone", ["rajasthan", "delhi"], [{name: "parvati"}], [{
                english: "moon",
                translit: "chand"
            }]),
            createSong("bhajan ro gudak ra jao", ["delhi"], [{
                name: "laxmi",
                "occupation": ["poet", "writer"]
            }], [{english: "sun", translit: "suraj"}]),
            createSong("nit khair", ["rajasthan"], [{name: "guy", "occupation": ["poet", "dancer"]}], [{
                english: "moon",
                translit: "chand"
            }, {english: "sun", translit: "suraj"}])
        ];
        var result = filter.filter(songs);
        expect(result.length).toBe(2);
        expect(result[0].title.english).toBe("kichchu din mone");
        expect(result[1].title.english).toBe("bhajan ro gudak ra jao");
    });

    it("should chain multiple filters", function () {
        var filterCriteria = [{
            name: "gathering",
            value: "delhi"
        }, {
            name: "singers[].name",
            value: "parvati"
        }];

        filter.filterCriteria = filterCriteria;
        var songs = [
            createSong("kichchu din mone", "delhi", [{name: "parvati"}], [{english: "moon", translit: "chand"}]),
            createSong("bhajan ro gudak ra jao", "delhi", [{
                name: "laxmi",
                "occupation": ["poet", "writer"]
            }], [{english: "sun", translit: "suraj"}]),
            createSong("nit khair", "rajasthan", [{name: "guy", "occupation": ["poet", "dancer"]}], [{
                english: "moon",
                translit: "chand"
            }, {english: "sun", translit: "suraj"}])
        ];

        var result = filter.filter(songs);

        expect(result.length).toBe(1);
        expect(result[0].title.english).toBe("kichchu din mone");
    });

    it("should chain multiple filters, even though result of combination is 0", function () {
        var filterCriteria = [{
            name: "gathering",
            value: "delhi"
        }, {
            name: "singers[].name",
            value: "parvati"
        }];

        filter.filterCriteria = filterCriteria;
        var songs = [
            createSong("kichchu din mone", "rajasthan", [{name: "parvati"}], [{english: "moon", translit: "chand"}]),
            createSong("bhajan ro gudak ra jao", "delhi", [{
                name: "laxmi",
                "occupation": ["poet", "writer"]
            }], [{english: "sun", translit: "suraj"}]),
            createSong("nit khair", "rajasthan", [{name: "guy", "occupation": ["poet", "dancer"]}], [{
                english: "moon",
                translit: "chand"
            }, {english: "sun", translit: "suraj"}])
        ];

        var result = filter.filter(songs);

        expect(result.length).toBe(0);

    });

    it("should be able to clear all filters", function () {
        var filterCriteria = [{
            name: "gathering",
            value: "delhi"
        }, {
            name: "singers[].name",
            value: "parvati"
        }];

        filter.filterCriteria = filterCriteria;
        var songs = [
            createSong("kichchu din mone", "rajasthan", [{name: "parvati"}], [{english: "moon", translit: "chand"}]),
            createSong("bhajan ro gudak ra jao", "delhi", [{
                name: "laxmi",
                "occupation": ["poet", "writer"]
            }], [{english: "sun", translit: "suraj"}]),
            createSong("nit khair", "rajasthan", [{name: "guy", "occupation": ["poet", "dancer"]}], [{
                english: "moon",
                translit: "chand"
            }, {english: "sun", translit: "suraj"}])
        ];

        var result = filter.filter(songs);
        expect(result.length).toBe(0);

        filter.clearFilters();

        result = filter.filter(songs);

        expect(result.length).toBe(3);
    });

    it("should be able to remove a particular filter", function () {
        var filterCriteria = [{
            name: "gathering",
            value: "rajasthan"
        }, {
            name: "singers[].name",
            value: "parvati"
        }];

        filter.filterCriteria = filterCriteria;
        var songs = [
            createSong("kichchu din mone", "rajasthan", [{name: "parvati"}], [{english: "moon", translit: "chand"}]),
            createSong("bhajan ro gudak ra jao", "delhi", [{
                name: "laxmi",
                "occupation": ["poet", "writer"]
            }], [{english: "sun", translit: "suraj"}]),
            createSong("nit khair", "rajasthan", [{name: "guy", "occupation": ["poet", "dancer"]}], [{
                english: "moon",
                translit: "chand"
            }, {english: "sun", translit: "suraj"}])
        ];

        var result = filter.filter(songs);
        expect(result.length).toBe(1);

        filter.removeFilterCriteria("singers[].name");
        result = filter.filter(songs);
        expect(result.length).toBe(2);

        filter.removeFilterCriteria("gathering");
        result = filter.filter(songs);
        expect(result.length).toBe(3);
    });


    it("should be able to apply the given filter method - startsWith", function () {
        var filterCriteria = [{
            name: "gathering",
            value: "a",
            method: "startsWith"
        }];

        filter.filterCriteria = filterCriteria;
        var songs = [
            createSong("kichchu din mone", "Ajasthan", [{name: "parvati"}], [{english: "moon", translit: "chand"}]),
            createSong("bhajan ro gudak ra jao", "latur", [{
                name: "laxmi",
                "occupation": ["poet", "writer"]
            }], [{english: "sun", translit: "suraj"}]),
            createSong("nit khair", "ajasthan", [{name: "guy", "occupation": ["poet", "dancer"]}], [{
                english: "moon",
                translit: "chand"
            }, {english: "sun", translit: "suraj"}])
        ];

        var result = filter.filter(songs);
        expect(result.length).toBe(2);

    });
});