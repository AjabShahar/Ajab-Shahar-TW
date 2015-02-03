var mappers = function (songMapper,wordMapper,reflectionMapper) {
    getSongMapper = function(){
        return songMapper;
    }

    getWordMapper = function(){
        return wordMapper;
    }

    getReflectionMapper = function(){
        return reflectionMapper;
    }

    return {
        getSongMapper: getSongMapper,
        getWordMapper: getWordMapper,
        getReflectionMapper : getReflectionMapper,
    };
};

thumbnailModule.factory('mappers', ['songMapper','wordMapper','reflectionMapper',mappers]);