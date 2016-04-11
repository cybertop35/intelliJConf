module.exports = function(grunt) {
    require('time-grunt')(grunt);

    grunt.initConfig({
        less: {
            development: {
                options: {
                    compress: true,
                    yuicompress: true,
                    optimization: 2
                },
                files: [{
                    expand: true,
                    cwd: 'target-grunt/layouts',
                    src: ['**/main.less'],
                    dest: 'target-grunt/layouts/',
                    ext: '.css',
                    rename: function(dest, src) {
                        return dest + src.substring(0, src.indexOf('/')) +'/layout.css';
                    }
                }]
            }
        },
        copy: { //copy sources for minification and produced artifacts
            copySrc: {
                files: [
                    {expand: true, cwd:'src/main/pages/', src: ['layouts/**', '!**/*.less'], dest: 'target-grunt/', filter: 'isFile'}
                ],
                encoding: "UTF-8"
            },
            copyDest: {
                files: [
                    {expand: true, cwd:'target-grunt/', src: ['layouts/**', '!**/*.less'], dest: 'target/', filter: 'isFile'}
                ],
                encoding: "UTF-8"
            }
        },
        watch: {
            styles: {
                files: ['src/main/pages/layouts/**/*.less'], // which files to watch
                tasks: ['addImportsToLess', 'less', 'copy:copyDest'],
                options: {
                    interrupt: true,
                    nospawn: true
                }
            },
            sources: {
                files: ['src/main/pages/layouts/**', '!**/*.less'],
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
    grunt.loadNpmTasks('grunt-contrib-copy');

    grunt.registerTask('addImportsToLess', '', function() {
        grunt.file.recurse('src/main/pages/layouts', function(abspath, rootdir, subdir, filename) {
            if(filename.indexOf("styles.less")>-1){
                var mainFile=abspath.replace("src/main/pages/layouts", "target-grunt/layouts").replace("less/styles.less", "main.less");
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

    grunt.registerTask('default', ['copy:copySrc', 'addImportsToLess', 'less', 'copy:copyDest']);
};
