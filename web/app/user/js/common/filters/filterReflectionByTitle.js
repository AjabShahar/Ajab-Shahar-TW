var filterReflectionByTitle = filterModule.filter('filterReflectionByTitle', function($window) {

    return function( reflections, filterCriteria) {
        var strStartsWith = function(str, prefix) {
            return (str+"").indexOf(prefix) === 0;
        };

        if(filterCriteria==null || filterCriteria==='')
            return reflections;

        var filtered = _.reduce(reflections, function(filteredList,reflection){
            var title = (filterCriteria.contentTextRepresentation === 'Transliteration')?
                reflection.title :
                reflection.title;
            if(strStartsWith(title.toUpperCase(),filterCriteria.alphabet.toUpperCase()))
                filteredList.push(reflection);

            return filteredList;
        },[]);

        return filtered;
    }
});
