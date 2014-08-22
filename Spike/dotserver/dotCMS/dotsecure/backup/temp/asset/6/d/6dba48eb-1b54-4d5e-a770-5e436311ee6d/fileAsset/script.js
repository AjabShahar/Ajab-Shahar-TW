var userInfoDataLoaded = false;

// User Info Flyout
$(function(){
  $("#userInfo .trigger").toggle(function(){
  if(!userInfoDataLoaded){
      //alert("set loading image here");
      $('#userInfoData').load('/application/ajax/location-clickstream.html', function(){
        //alert("stop loading image here");
        userInfoDataLoaded=true;
        $(this).parent().animate({right:0}, "medium")
      });
    }
    else{
    $(this).parent().animate({right:0}, "medium")
    }
  }, function(){
    $(this).parent().animate({right:-322}, "medium")
  });
});


// Hover Image
$(function(){
  $(".hoverImage").append("<span></span>");
  $(".hoverImage").hover(function(){
    $(this).find("img").stop().animate({opacity:0.1}, "normal")
  }, function(){
    $(this).find("img").stop().animate({opacity:1}, "normal")
  });
});


// Hover Video
$(function(){
  $(".hoverVideo").append("<span></span>");
  $(".hoverVideo").hover(function(){
    $(this).find("img").stop().animate({opacity:0.1}, "normal")
  }, function(){
    $(this).find("img").stop().animate({opacity:1}, "normal")
  });
});


// Tabs  
$(function(){
  tabs.init();
});
    
tabs = {
  init : function(){
    $('.tabs').each(function(){
      $(this).find('.tab-content:gt(0)').hide();
      $(this).find('ul.nav a').click(function(){
      $(this).parents('div.tabs').find('.tab-content').hide();
      $($(this).attr('href')).fadeIn(300);
      $(this).parent().addClass('selected').siblings().removeClass('selected');
        return false;
      });
    });
  }
}


// Flip iPhone
function flipPhone(){
	var p = $('div.pp_pic_holder');
	var position = p.position();
	var y = position.top;
	var x = position.left;
	var x = (x - 110);
	$('#iphoneFrame').attr('width', '542px');
	$('#iphoneFrame').attr('height', '362px');
	$('.pp_pic_holder').attr('style', 'display: block; width: 620px; top:' + y + 'px; left:' + x + 'px;');
	$('.pp_content').attr('style', 'height: 362px; width: 578px; ');
	$('.pp_details').addClass("pp_details-hoz");
	$('.pp_details').attr('style', 'height: 20px; ');
	$('.flipLink').attr('style', 'display:none;');
	$('.pp_close').attr('style', 'left:-180px;top:-30px;');
}


$(document).ready(function() {

	// Flex Slider
	if ($(".flexslider").length) {
		$(window).load(function() {
			$('.flexslider').flexslider({
				animation: "slide"
			});
		});
	}

	// Table sorter
	if ($(".sort-table").length) {
		$(".sort-table").tablesorter( {sortList: [[0,0]]} );
	}
  
	// initiate tool tip
    // basic usage  
    $('.normaltip').aToolTip();
    
    // fixed tooltip  
    $('.fixedtip').aToolTip({  
      fixed: true  
    });
    
    // clicked tooltip
    $('.clicktip').aToolTip({  
      clickIt: true
    });  
      
  // jumper
  $('.top').click(
    function(e){
      $('html, body').animate({scrollTop: '0px'}, 800);
      return false;
    }
  );
      
  // accordion  
  $("#accordion dt").click(function(){
    $(this).next().toggle("slow").siblings("#accordion dd:visible").slideUp("slow");
    return false;
  });
  
  // slideDown
  $(".slideDown dt").click(function(){
    $(this).toggleClass("active").parent(".slideDown").find("dd").slideToggle();
  });

          
  //prettyPhoto
  $("a.prettyPhoto").prettyPhoto({
    animation_speed: 'fast', /* fast/slow/normal */
    slideshow: 5000, 
    autoplay_slideshow: false,
    opacity: 0.80,
    show_title: true,
    allow_resize: true,
    default_width: 700,
    default_height: 344,
    counter_separator_label: ' of ', /* The separator for the gallery counter 1 "of" 2 */
    theme: 'pp_default', /* light_rounded / dark_rounded / light_square / dark_square / facebook */
    horizontal_padding: 20, /* The padding on each side of the picture */
    hideflash: false, /* Hides all the flash object on a page, set to TRUE if flash appears over prettyPhoto */
    wmode: 'opaque', /* Set the flash wmode attribute */
    autoplay: true, /* Automatically start videos: True/False */
    modal: false, /* If set to true, only the close button will close the window */
    deeplinking: true, /* Allow prettyPhoto to update the url to enable deeplinking. */
    overlay_gallery: true, /* If set to true, a gallery will overlay the fullscreen image on mouse over */
    keyboard_shortcuts: true, /* Set to false if you open forms inside prettyPhoto */
  });
      
  $("a.clickTrails").prettyPhoto({
    theme: 'pp_default', /* light_rounded / dark_rounded / light_square / dark_square / facebook */
    show_title: false,
    deeplinking: false,
    social_tools: false
  });
  
  $("a.mobileSite").prettyPhoto({
    theme: 'iphone', /* light_rounded / dark_rounded / light_square / dark_square / facebook */
    show_title: false,
    deeplinking: false,
    social_tools: false,
    allow_resize: false,
    default_width: 362,
    default_height: 542,
    markup: '<div class="pp_pic_holder"> \
						<div class="ppt">&nbsp;</div> \
						<div class="pp_top"> \
							<div class="pp_left"></div> \
							<div class="pp_middle"></div> \
							<div class="pp_right"></div> \
						</div> \
						<div class="pp_content_container"> \
							<div class="pp_left"> \
								<div class="pp_right"> \
									<div class="pp_content"> \
										<div class="pp_loaderIcon"></div> \
										<div class="pp_fade"> \
											<div id="pp_full_res"></div> \
											<div class="pp_details"> \
												<a class="flipLink" href="javascript:flipPhone()">Flip Phone</a> \
												<a class="pp_close" href="#">Close</a> \
											</div> \
										</div> \
									</div> \
								</div> \
							</div> \
						</div> \
						<div class="pp_bottom"> \
							<div class="pp_left"></div> \
							<div class="pp_middle"></div> \
							<div class="pp_right"></div> \
						</div> \
					</div> \
					<div class="pp_overlay"></div>',
	iframe_markup: '<iframe src ="{path}" width="{width}" height="{height}" id="iphoneFrame" frameborder="no"></iframe>'
  });
  
  // Chosen selectbox custom
  $("#event-categories").chosen();

  // Submit resources form
  $('#resSubmit, #doc-search input:checkbox').click( function() {
    $('#doc-search').submit();
  });


});
