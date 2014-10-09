$.noConflict();
jQuery( document ).ready(function( $ ) {
$(function(){
$(window).scroll(function(){
  var sticky = $('.headerWrapper'),stickyArt = $('.headerArt'),
      scroll = $(window).scrollTop(),toFix=$('.headerWrapper').height();

  if (scroll >= toFix){sticky.addClass('fixed');stickyArt.addClass('fixed');$('body').css('padding-top',toFix+'px');}
  else {sticky.removeClass('fixed');stickyArt.removeClass('fixed');$('body').css('padding-top','0px');}
});


$(document).on('click','div.headerArt',function(){

var addMarginToArt=$('.headerWrapper').height();
if($('.headerWrapper').css('display')=='none'){
    $('.headerWrapper').addClass('fixedOnScroll');
    $('.headerArt').css('top',addMarginToArt+'px');
}
else{
    $('.headerWrapper').removeClass('fixedOnScroll');
    $('.headerArt').css('top','0px');
}
});

});
});
