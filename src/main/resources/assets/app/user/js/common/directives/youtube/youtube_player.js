mediaPlayer.constant('YT_event', {
  STOP:            0,
  PLAY:            1,
  PAUSE:           2
});

mediaPlayer.directive('youtube', function($window, YT_event,$sce) {
  return {
    restrict: "E",

    scope: {
      height: "@",
      width: "@",
      videoid: "=",
      controls : "@",
      showinfo: "@"
    },

    template: '<iframe id="ytplayer" type="text/html" width="{{width}}" height="{{height}}" ng-src="{{videoUrl}}" frameborder="0"/>',

    link: function(scope, element, attrs, $rootScope) {
        console.log(scope.videoid);
        scope.videoUrl = $sce.trustAsResourceUrl("http://www.youtube.com/embed/"+scope.videoid+"?controls="+scope.controls+"&showinfo="+scope.showinfo);
    }
  };
});