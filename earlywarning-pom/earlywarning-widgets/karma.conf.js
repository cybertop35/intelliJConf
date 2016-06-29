// Karma configuration
// http://karma-runner.github.io/0.13/config/configuration-file.html

module.exports = function(config) {
    config.set({
        // base path, that will be used to resolve files and exclude
        basePath: '',

        frameworks: ['jasmine', 'requirejs'],

        // list of files / patterns to load in the browser
        files: [
            { pattern: 'target-grunt/assets/test/jquery/jquery.js' },
            { pattern: 'target-grunt/assets/**/*.js', included: false, served: true },
            { pattern: 'src/main/webapp/assets/**/*.js', included: false, served: true },
            { pattern: 'src/main/webapp/widgets/**/*.js', included: false, served: true },
            { pattern: 'src/main/webapp/widgets/**/*.html', included: false, served: true },
            { pattern: 'src/test/javascript/**/*.js', included: false, served: true },
            //{ pattern: 'target-grunt/requireAllTest.js', included: false, served: true },
            { pattern: 'src/test/main.js' }
        ],
        proxies: {},
        exclude: [  ],

        port: 9876,
        hostname: 'localhost',

        logLevel: config.LOG_WARN, // level of logging: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG

        autoWatch: false,// enable / disable watching file and executing tests whenever any file changes
        browsers: ['PhantomJS'],// Chrome || ChromeCanary || Firefox || Opera || Safari (only Mac) || PhantomJS || IE (only Windows)
        captureConsole: true,
        captureTimeout: 3000,

        preprocessors: {
            // source files, that you wanna generate coverage for, do not include tests or libraries (these files will be instrumented by Istanbul)
            'src/main/webapp/widgets/**/*.js': ['coverage'],
            'src/main/webapp/assets/**/*.js': ['coverage']
        },

        coverageReporter: {
            includeAllSources: true,
            reporters: [
                { type : 'html', dir : 'coverage' },
                { type: 'text-summary', subdir: '.', file: 'text-summary.txt'},
                { type: 'cobertura', subdir: '.', file: 'cobertura.txt' }
            ],
            instrumenterOptions: {
                istanbul: { noCompact: true }
            }
        }
    });
};
