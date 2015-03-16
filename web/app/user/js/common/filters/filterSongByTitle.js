var filterSongByTitle = filterModule.filter('filterSongByTitle', function($window) {

    return function( songs, filterCriteria) {
        var strStartsWith = function(str, prefix) {
            return (str+"").indexOf(prefix) === 0;
        }

        if(filterCriteria==null || filterCriteria==='')
            return songs;

        var filtered = _.reduce(songs, function(filteredList,song){
            var title = (filterCriteria.contentTextRepresentation === 'Transliteration')?
                song.englishTransliteration :
                song.englishTranslation;
            if(strStartsWith(title.toUpperCase(),filterCriteria.alphabet.toUpperCase()))
                filteredList.push(song);

            return filteredList;
        },[]);

        return filtered;
    }
});
