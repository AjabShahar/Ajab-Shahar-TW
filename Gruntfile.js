module.exports = function(grunt) {

  require("matchdep").filterDev("grunt-*").forEach(grunt.loadNpmTasks);
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    jssemicoloned: {
        files: ['src/main/resources/assets/app/admin/js/**/**/*.js','src/main/resources/assets/app/admin/js/**/*.js']
     },
    concat: {
      options: {
        separator: ';'
      },
      dist: {
        src: ['src/main/resources/assets/app/admin/js/**/**/*.js','src/main/resources/assets/app/admin/js/**/*.js'],
        dest: 'dist/<%= pkg.name %>.js'
      }
    },
    uglify: {

      options: {
        banner: '/*! <%= pkg.name %> <%= grunt.template.today("dd-mm-yyyy") %> */\n'
      },
      dist: {
        files: {
          expand : true,
          'dist/<%= pkg.name %>.min.js': ['<%= concat.dist.dest %>']
        }
      }
    },
    qunit: {
      files: ['test/js/config/karma.dev.conf.js']
    },
    jshint: {
      files: ['Gruntfile.js', 'src/main/resources/assets/app/admin/js/**/**/*.js','src/main/resources/assets/app/admin/js/**/*.js'],
      options: {
        // options here to override JSHint defaults
        globals: {
          jQuery: true,
          console: true,
          module: true,
          document: true
        }
      },
      ignore_warning: {
            options: {
              '-W033': true,
            },
            src: ['src/main/resources/assets/app/admin/js/**/**/*.js','src/main/resources/assets/app/admin/js/**/*.js'],
      }
    },
    watch: {
      files: ['<%= jshint.files %>'],
      tasks: ['jshint', 'qunit']
    }
  });


  grunt.registerTask('test', ['jshint', 'qunit']);

  grunt.registerTask('default', ['jshint', 'qunit', 'concat', 'uglify','watch']);

};