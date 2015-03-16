var gulp = require("gulp"),
		sass = require("gulp-sass"),
		path = require("path"),
		del = require("del"),
		zip = require("gulp-zip"),
		sourcemaps = require('gulp-sourcemaps');


var userModule = "./web/app/user";



// SASS Related tasks

gulp.task("sass:compile", function() {

  var outputStyle = "expanded"; // "nested" | "compact" | "compressed" | "expanded"

  return gulp.src(
  					path.join(userModule, "/sass/ajabShahar.scss")
  				)
					.pipe(sourcemaps.init())
					.pipe(sass({outputStyle: outputStyle}))
					.pipe(sourcemaps.write())
					.pipe(gulp.dest(
						path.join(userModule, "/css")
					));
});

gulp.task("sass:watch", function() {

 	gulp.watch(
 		path.join(userModule, "/sass/ajabShahar.scss"),
 		["sass:compile"]
 	)

 	// gutil.log("[watch:sass]", gutil.colors.yellow("Watching SASS files..."));

});

// Common tasks

gulp.task("default", ["sass:compile"]);

gulp.task("watch", ["sass:watch"]);

gulp.task('js', function () {
    return gulp.src('web/**/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'))
        .pipe(gulp.dest('dist'));
});

gulp.task ("clean",function(){
    del(['dist/*']);
});

gulp.task("zip",["clean","sass:compile"],function(){
    return gulp.src('web/app/**')
        .pipe(zip('ajabshahar.zip'))
        .pipe(gulp.dest('dist'))
});