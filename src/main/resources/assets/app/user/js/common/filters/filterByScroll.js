var filterByScroll = filterModule.filter('filterByScroll', function($window) {

    return function( features, scrollIndex) {
        if(features.length<=scrollIndex)
            return features;

        var index = 0;

        var filtered = _.reduce(features, function(filteredList,feature){
            index++;
            if(index>=scrollIndex)
                return filteredList;
            filteredList.push(feature);
            return filteredList;
        },[]);

        return filtered;
    }
});