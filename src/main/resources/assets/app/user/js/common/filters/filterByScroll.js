var filterByScroll = filterModule.filter('filterByScroll', function($window) {

    return function( songs, scrollIndex) {
        var index = 0;

        var filtered = _.reduce(songs, function(filteredList,song){
            index++;
            if(index>=scrollIndex)
                return filteredList;
            filteredList.push(song);
            return filteredList;
        },[]);

        return filtered;
    }
});