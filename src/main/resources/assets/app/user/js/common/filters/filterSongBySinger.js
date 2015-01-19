var filterSongBySinger = filterModule.filter('filterSongBySinger', function() {
   return function( songs, singer) {
    if(singer==null || singer==='' || singer.name=='')
       return songs;

    var filtered = _.reduce(songs, function(filteredList,song){
        if(_.findWhere(song.searchableCriteria.singers, singer)!=null)
            filteredList.push(song);
        return filteredList;
    },[]);

    return filtered;
  };
});