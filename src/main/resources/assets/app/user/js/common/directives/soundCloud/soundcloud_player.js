mediaPlayer.directive('soundCloud', function($window, YT_event) {
  return {
    restrict: "E",

    scope: {
      height: "@",
      width: "@",
      audioUrl: '@',
      shouldStopVideo:'&',
      id:'@',
    },

    template: '<div id="{{id}}"></div>',

    link: function(scope, element, attrs, $rootScope) {
        SC.initialize({
            client_id: "694f15bbffd7ae8e6e399f49dd228725"
        });

        scope.loadTrack = function(trackID){
              SC.get("/tracks/" + trackID, {limit: 1}, function(track){
                SC.oEmbed(track.uri, document.getElementById(scope.id));
              });
        }

        scope.$watch(function() { return scope.shouldStopVideo(); }, function(newValue, oldValue) {
            if (newValue == oldValue) {
              return;
            }

            if(newValue){
                scope.pauseTrack();
            }
        });

        scope.pauseTrack = function(){
            var iframeElement = element.find('iframe');
            var widget1         = SC.Widget(iframeElement[0]);
            widget1.pause();
        }

        scope.playTracks = function(){
            var iframeElement = element.find('iframe');
            var widget1         = SC.Widget(iframeElement[0]);
            widget1.play();
        }

        scope.loadTrack(scope.audioUrl);
    }
  };
});