var mappers = function (songMapper,wordMapper) {
    getSongMapper = function(){
        return songMapper;
    }

    getWordMapper = function(){
        return wordMapper;
    }

    return {
        getSongMapper: getSongMapper,
        getWordMapper: getWordMapper,
    };
};

thumbnailModule.factory('mappers', ['songMapper','wordMapper',mappers]);