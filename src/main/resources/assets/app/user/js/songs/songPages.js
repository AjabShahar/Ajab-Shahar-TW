(function(window, document, $) {
 	"use strict";

 	$(document).ready(function() {
 		$(".filter-button").on("click", function() {
 			$(".nav-list").toggleClass("show");

 			return false;
 		});
    }); // Close DOM ready.

})(window, document, jQuery);
