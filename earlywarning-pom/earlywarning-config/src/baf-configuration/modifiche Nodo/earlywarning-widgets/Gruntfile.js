module.exports = function(grunt) {
    require('time-grunt')(grunt);

    grunt.initConfig({
        env: {
            prod: {
                bower_directory: "bower_components/prod"
            },
            test: {
                bower_directory: "bower_components/test"
            }
        },
        bower: { //js dependency copy
            prod: {
                options: {
                    targetDir: './target-grunt/assets',
                    layout: 'byComponent',
                    cleanTargetDir: true,
                    cleanBowerDir: false,
                    verbose: false,
                    install: false
                }
            },
            test: {
                options: {
                    targetDir: './target-grunt/assets/test',
                    layout: 'byComponent',
                    cleanTargetDir: true,
                    cleanBowerDir: false,
                    verbose: false,
                    install: false
                }
            }
        },
        uglify: { //js minification
            minify: {
                options: {
                    drop_console: false,
                    mangle: false,  // Use if you want the names of your functions and variables unchanged
                    compress: false,
                    sourceMap: true,
                    sourceMapIncludeSources: true
                },
                files: [
                    {expand: true, cwd:'target-grunt/assets', src: ['**/*.js'], dest: 'target/assets', filter: 'isFile'}
                    //{expand: true, cwd:'src/main/webapp/widgets', src: ['**/view.js'], dest: 'target/widgets', filter: 'isFile'}
                ]
            }
        },
        less: { //less compilation to css
            development: {
                options: {
                    compress: true,
                    yuicompress: true,
                    optimization: 2
                },
                files: [{
                    expand: true,
                    cwd: 'target-grunt/widgets',
                    src: ['**/main.less'],
                    dest: 'target-grunt/widgets/',
                    ext: '.css',
                    rename: function(dest, src) {
                        return dest + src.substring(0, src.indexOf('/')) +'/style.css';
                    }
                }]
            }
        },
        copy: { //copy sources for minification and produced artifacts
            copySrc: {
                files: [
                    {expand: true, cwd:'src/main/webapp/widgets', src: ['**', '!**/*.less'], dest: 'target-grunt/widgets', filter: 'isFile'}, //, '!**/view.js'
                    {expand: true, cwd:'src/main/webapp/assets', src: ['**', '!**/*.less'], dest: 'target/assets', filter: 'isFile'},
                    {expand: true, cwd:'target-grunt/assets/', src: ['**/*.src.js'], dest: 'target-grunt/assets/', filter: 'isFile', rename: function(dest, src) {return dest + src.replace('.src.js','.js');}},//rename files downloaded from Bower
                    {expand: true, cwd:'target-grunt/assets/', src: ['**/*-*.js'], dest: 'target-grunt/assets/', filter: 'isFile', rename: function(dest, src) {return dest + src.replace(/-\d+(\.\d+)+.js/g,'.js');}}//rename files downloaded from Bower
                ],
                encoding: "UTF-8"
            },
            copyDest: {
                files: [
                    {expand: true, cwd:'target-grunt/', src: ['assets/**', '!src/**', '!test/**', '!**/*.js'], dest: 'target/'}, //assets
                    {expand: true, cwd:'target-grunt/', src: ['widgets/**', '!**/*.less'], dest: 'target/'} //widgets
                ],
                encoding: "UTF-8"
            }
        },
        karma: {
            unit: {
                configFile: 'karma.conf.js',
                singleRun: true,
                reporters: ['progress', 'verbose']
            },
            coverage: {
                configFile: 'karma.conf.js',
                singleRun: true,
                reporters: ['coverage']
            },
            debug: {
                configFile: 'karma.conf.js',
                singleRun: false,
                reporters: ['progress', 'verbose']
            }
        },
        watch: {
            styles: {
                files: ['src/main/webapp/widgets/**/*.less'],
                tasks: ['addImportsToLess', 'less', 'copy:copyDest'],
                options: {
                    interrupt: true,
                    nospawn: true
                }
            },
            sources: {
                files: ['src/main/webapp/widgets/**', 'src/main/webapp/assets/**', '!**/*.less'],
                tasks: ['copy:copySrc', 'copy:copyDest'],
                options: {
                    interrupt: true,
                    nospawn: true
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-env');
    grunt.loadNpmTasks('grunt-bower-task');
    grunt.loadNpmTasks('grunt-karma');

    grunt.registerTask('test', 'Check if there are tests and execute them.', function() {
        var specs = grunt.file.expand(['src/test/javascript/**/*.js']);
        grunt.log.writeln('SPECS: '+specs.length);
        if(specs.length > 0) {
            require('jit-grunt')(grunt, {
                jasmine: 'grunt-contrib-jasmine'
            });
            return grunt.task.run(['env:test', 'bower:test', 'karma:unit', 'karma:coverage']);
        }
    });

    grunt.registerTask('addImportsToLess', function() {
        grunt.file.recurse('src/main/webapp/widgets', function(abspath, rootdir, subdir, filename) {
            if(filename.indexOf("styles.less")>-1){
                var mainFile=abspath.replace("src/main/webapp/widgets","target-grunt/widgets").replace("less/styles.less", "main.less");
                var includeTemplate = '@import (reference) "../../../target/less/main";\n';
                grunt.file.recurse(rootdir+"/"+subdir, function(innerabspath, innerrootdir, innersubdir, innerfilename) {
                    if(innerfilename!="main.less"){
                        includeTemplate+='@import "' + innerabspath + '";\n';
                    }
                });
                if(grunt.file.exists("src/main/webapp/assets")) {
                    grunt.file.recurse("src/main/webapp/assets", function (innerabspath, innerrootdir, innersubdir, innerfilename) {
                        if (innerfilename.endsWith(".less")) {
                            includeTemplate += '@import (reference) "' + innerabspath + '";\n';
                        }
                    });
                }
                grunt.file.write(mainFile, includeTemplate);
            }
        });
    });

    grunt.registerTask('removeWrongPathFromSourceMap', function() {
        grunt.file.recurse('target/', function(abspath, rootdir, subdir, filename) {
            if(filename.indexOf(".js.map") >- 1){
                var jsFile = filename.replace(".map", "");
                var fileContent = grunt.file.read(abspath);
                fileContent=fileContent.replace(/\"sources\"\:\[\".*\"\],\"names\"/g, '"sources":["'+jsFile+'"],"names"');
                grunt.file.write(abspath, fileContent);
            }
        });
    });

    grunt.registerTask('installAssets', function() {
        return grunt.task.run(['env:prod', 'bower:prod']);
    });
    grunt.registerTask('default', ['installAssets', 'copy:copySrc', 'uglify', 'removeWrongPathFromSourceMap', 'addImportsToLess', 'less', 'copy:copyDest']);
};