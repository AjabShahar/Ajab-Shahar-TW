var wordService = function($http){

   var getVersions = function(wordId){
      return $http.get('/api/words/versions?id='+wordId);
   }

   return{
     getVersions : getVersions
   };
}