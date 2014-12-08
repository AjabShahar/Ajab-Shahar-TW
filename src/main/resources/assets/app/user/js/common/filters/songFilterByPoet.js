var songFilterByPoet = filterModule.filter('songFilterByPoet', function() {
   return function( songs, poet) {
    if(poet==null || poet==='')
        return songs;

    var filtered = _.reduce(songs, function(filteredList,song){
        if(_.contains(song.poet, poet))
            filteredList.push(song);
        return filteredList;
    },[]);

    return filtered;
  };
});
