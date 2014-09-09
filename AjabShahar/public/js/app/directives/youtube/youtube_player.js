ajabShaharApp.constant('YT_event', {
  STOP:            0,
  PLAY:            1,
  PAUSE:           2
});

ajabShaharApp.controller('YouTubeCtrl', function($scope, YT_event) {
  $scope.yt = {
    width: '100%',
    height: '100%',
    videoid: "M7lc1UVf-VE"
  };
});

ajabShaharApp.directive('youtube', function($window, YT_event) {
  return {
    restrict: "E",

    scope: {
      height: "@",
      width: "@",
      videoid: "@"
    },

    template: '<div></div>',

    link: function(scope, element, attrs, $rootScope) {
      var tag = document.createElement('script');
      tag.src = "https://www.youtube.com/iframe_api";
      var firstScriptTag = document.getElementsByTagName('script')[0];
      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

      var player;

      $window.onYouTubeIframeAPIReady = function() {

        player = new YT.Player(element.children()[0], {
          playerVars: {
            autoplay: 0,
            html5: 1,
            theme: "light",
            modesbranding: 0,
            color: "white",
            iv_load_policy: 3,
            showinfo: 0,
            controls: 0
          },

          height: scope.height,
          width: scope.width,
          videoId: scope.videoid,
          events: {
            'onReady': onPlayerReady,
            'onStateChange': onPlayerStateChange
          },
        });
      }

        function onPlayerReady(event) {
            event.target.playVideo();
        }

        // when video ends
        function onPlayerStateChange(event) {
            if(event.data === 0) {
              event.target.playVideo();
            }
        }

        scope.$watch('height + width', function(newValue, oldValue) {
        if (newValue == oldValue) {
          return;
        }

        player.setSize(scope.width, scope.height);

        });

        scope.$watch('videoid', function(newValue, oldValue) {
        if (newValue == oldValue) {
          return;
        }

        player.cueVideoById(scope.videoid);

        });

        scope.$on(YT_event.STOP, function () {
        player.seekTo(0);
        player.stopVideo();
        });

        scope.$on(YT_event.PLAY, function () {
        player.playVideo();
        });

        scope.$on(YT_event.PAUSE, function () {
        player.pauseVideo();
        });

    }
  };
});