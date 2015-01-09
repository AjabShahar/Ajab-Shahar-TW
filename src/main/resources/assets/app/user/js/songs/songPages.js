(function(window, document, $) {
 	"use strict";

 	$(document).ready(function() {
 		$(".filter").on("click", function() {
 			$(".nav-list").toggleClass("show");
 			$(".ajab-wrapper").toggleClass("left-menu-open");
 			return false;
 		});
    }); // Close DOM ready.

})(window, document, jQuery);
