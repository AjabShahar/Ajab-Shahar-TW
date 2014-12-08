var songFilterBySinger = filterModule.filter('songFilterBySinger', function() {
   return function( songs, singer) {
    if(singer==null || singer==='')
       return songs;

    var filtered = _.reduce(songs, function(filteredList,song){
        if(_.contains(song.singers, singer))
            filteredList.push(song);
        return filteredList;
    },[]);

    return filtered;
  };
});