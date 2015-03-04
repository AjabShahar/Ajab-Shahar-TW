var gulp = require("gulp"),
		sass = require("gulp-sass"),
		path = require("path"),
		sourcemaps = require('gulp-sourcemaps');


var userModule = "./src/main/resources/assets/app/user";


gulp.task("sass:compile", function() {

  var outputStyle = "expanded"; // "nested" | "compact" | "compressed" | "expanded"

  return gulp.src(
  					path.join(userModule, "/sass/ajabShahar.scss"
  				))
					.pipe(sourcemaps.init())
					.pipe(sass({outputStyle: outputStyle}))
					.pipe(sourcemaps.write())
					.pipe(gulp.dest(
						path.join(userModule, "/css")
					));
});


gulp.task("default", ["sass:compile"]);