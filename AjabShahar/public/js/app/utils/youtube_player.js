// create youtube player
var player;
function onYouTubePlayerAPIReady() {
    player = new YT.Player('player', {
    videoId: '0Bmhjf0rKe8',
      events: {
        'onReady': onPlayerReady,
        'onStateChange': onPlayerStateChange
      }
    });
}

// autoplay video
function onPlayerReady(event) {
    event.target.playVideo();
}

// when video ends
function onPlayerStateChange(event) {
    if(event.data === 0) {
      event.target.playVideo();
    }
}