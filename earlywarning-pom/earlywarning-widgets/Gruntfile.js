module.exports = function(grunt) {
    require('time-grunt')(grunt);

    grunt.initConfig({
        bower: { //js dependency copy
            install: {
                options: {
                    targetDir: './target-grunt/assets/src',
                    layout: 'byComponent',
                    cleanTargetDir: true,
                    cleanBowerDir: false,
                    verbose: false,
                    bowerOptions: {
                        offline: true,
                        forceLatest: true, // Force latest version on conflict
                        production: true
                    }
                }
            },
            test: {
                options: {
                    targetDir: './target-grunt/assets/test',
                    layout: 'byComponent',
                    cleanTargetDir: true,
                    cleanBowerDir: false,
                    verbose: false,
                    bowerOptions: {
                        offline: true,
                        forceLatest: true, // Force latest version on conflict
                        production: false
                    }
                }
            }
        },
        uglify: { //js minification
            minify: {
                options: {
                    mangle: false,  // Use if you want the names of your functions and variables unchanged
                    compress: true,
                    sourceMap: true,
                    sourceMapIncludeSources: true
                },
                files: [
                    //ext: '.min.js' //rename dest file, cuts the source file name after the first .
                    {expand: true, cwd:'target-grunt/assets/src', src: ['**/*.js'], dest: 'target-grunt/assets', filter: 'isFile'}
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
                    {expand: true, cwd:'src/main/webapp/', src: ['widgets/**', '!**/*.less'], dest: 'target-grunt/', filter: 'isFile'},
                    {expand: true, cwd:'src/main/webapp/', src: ['assets/**', '!**/*.less'], dest: 'target-grunt/', filter: 'isFile'},
                    {expand: true, cwd:'target-grunt/assets/src/', src: ['**/*.src.js'], dest: 'target-grunt/assets/src/', filter: 'isFile', rename: function(dest, src) {return dest + src.replace('.src.js','.js');}},//rename files downloaded from Bower
                    {expand: true, cwd:'target-grunt/assets/src/', src: ['**/*-*.js'], dest: 'target-grunt/assets/src/', filter: 'isFile', rename: function(dest, src) {return dest + src.replace(/-\d+(\.\d+)+.js/g,'.js');}}//rename files downloaded from Bower
                ],
                encoding: "UTF-8"
            },
            copyDest: {
                files: [
                    {expand: true, cwd:'target-grunt/assets/src', src: ['**', '!**/*.js', '!**/*-[0-9.]*.js'], dest: 'target/assets/', filter: 'isFile'},
                    {expand: true, cwd:'target-grunt/', src: ['assets/**', '!src/**', '!test/**', '!**/*.src.js', '!**/*.src.js.map', '!**/*-[0-9.]*.js', '!**/*-[0-9.]*.js.map'], dest: 'target/'}, //assets
                    {expand: true, cwd:'target-grunt/', src: ['widgets/**', '!**/*.less'], dest: 'target/'} //widgets
                ],
                encoding: "UTF-8"
            }
        },
        jasmine: { //js test execution
            test: {
                options: {
                    keepRunner: true,
                    specs: 'src/test/javascript/**/*.js',
                    template: require('grunt-template-jasmine-requirejs'),
                    templateOptions: {
                        requireConfig: {
                            paths: {
                                'jquery': 'target-grunt/assets/test/jquery/jquery',
                                'underscore': 'target-grunt/assets/test/underscore/underscore',
                                'backbone': 'target-grunt/assets/test/backbone/backbone',
                                'bootstrap': 'target-grunt/assets/test/bootstrap/bootstrap',
                                'blockUI' : 'target-grunt/assets/test/blockui/jquery.blockUI',
                                'mCustomScroller': 'target-grunt/assets/test/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min',
                                'jquery-text-fill': 'target-grunt/assets/test/jquery-textfill/jquery.textfill',
                                'text': 'target-grunt/assets/test/text/text',
                                'moment': 'target-grunt/assets/test/moment/moment',
                                'qtip' : 'target-grunt/assets/test/qtip2/jquery.qtip',
                                'squire': 'target-grunt/assets/test/squire/Squire'
                            },
                            shim: {
                                'underscore': {
                                    exports: '_'
                                },
                                'backbone': {
                                    deps: ["underscore", "jquery"],
                                    exports: 'Backbone'
                                },
                                "mCustomScroller" : {
                                    deps : ["jquery"]
                                },
                                "bootstrap": {
                                    deps: ["jquery"]
                                }
                            }
                        }
                    }
                }
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
    grunt.loadNpmTasks('grunt-contrib-jasmine');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-bower-task');
    grunt.loadNpmTasks('grunt-contrib-copy');

    grunt.registerTask('checkIfExecuteTests', 'Check from Maven if tests must be executed.', function() {
        var specs = grunt.file.expand(grunt.config.get('jasmine.test.options.specs'));
        grunt.log.writeln('SPECS: '+specs.length);
        if(specs.length > 0) {
            grunt.task.run('jasmine:test');
        }
    });

    grunt.registerTask('addImportsToLess', '', function() {
        grunt.file.recurse('src/main/webapp/widgets', function(abspath, rootdir, subdir, filename) {
            if(filename.indexOf("styles.less")>-1){
                var mainFile=abspath.replace("src/main/webapp/widgets","target-grunt/widgets").replace("less/styles.less", "main.less");
                var includeTemplate = '@import (reference) "../../../target/less/main";\n';
                grunt.file.recurse(rootdir+"/"+subdir, function(innerabspath, innerrootdir, innersubdir, innerfilename) {
                    if(innerfilename!="main.less"){
                        includeTemplate+='@import "' + innerabspath + '";\n';
                    }
                });
                grunt.file.write(mainFile, includeTemplate);
            }
        });
    });

    grunt.registerTask('default', ['bower:install', 'copy:copySrc', 'uglify', 'addImportsToLess', 'less', 'bower:test', 'checkIfExecuteTests', 'copy:copyDest']);
};
