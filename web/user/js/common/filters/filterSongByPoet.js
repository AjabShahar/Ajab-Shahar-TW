var filterSongByPoet = filterModule.filter('filterSongByPoet', function() {
   return function( songs, poet) {
    if(poet==null || poet==='' || poet.name=='')
        return songs;

    var filtered = _.reduce(songs, function(filteredList,song){
        if(_.findWhere(song.searchableCriteria.poets, {id:poet.id})!=null)
            filteredList.push(song);
        return filteredList;
    },[]);

    return filtered;
  };
});
