/**
 *
 *  Web Starter Kit
 *  Copyright 2016 Beuhner-Fry Inc. All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 *
 */

'use strict';

// This gulpfile makes use of new JavaScript features.
// Babel handles this without us having to do anything. It just works.
// You can read more about the new JavaScript features here:
// https://babeljs.io/docs/learn-es2015/

import path from 'path';
import gulp from 'gulp';
import gutil from 'gulp-util';
import del from 'del';
import runSequence from 'run-sequence';
import swPrecache from 'sw-precache';
import autoprefixer from 'autoprefixer';
import mqpacker from 'css-mqpacker';
import cssnano from 'cssnano';
import stylelint from 'stylelint';
import browserSync from 'browser-sync';
import postcssImport from 'postcss-import';
import postcssEach from 'postcss-each';
import postcssApply from 'postcss-apply';
import postcssSimpleVars from 'postcss-simple-vars';
import postcssCustomMedia from 'postcss-custom-media';
import postcssCustomSelectors from 'postcss-custom-selectors';
import postcssNested from 'postcss-Nested';
import postcssCssVariables from 'postcss-css-variables';
import postcssSelectorNot from 'postcss-selector-not';
import postcssCalc from 'postcss-calc';
import postcssLogicalProps from 'postcss-logical-props';
import postcssFlexbugsFixes from 'postcss-flexbugs-fixes';
import postcssReporter from 'postcss-reporter';
import mdcss from 'mdcss';
import mdcssGithub from 'mdcss-theme-github';
import gulpLoadPlugins from 'gulp-load-plugins';
import {output as pagespeed} from 'psi';
import pkg from './package.json';

const $ = gulpLoadPlugins();
const reload = browserSync.reload;

// Compile and automatically prefix stylesheets
gulp.task('styles', () => {
  const AUTOPREFIXER_BROWSERS = ['last 2 versions'];

  const PROCESSORS = [
    postcssImport({ glob: true }),
    stylelint,
    postcssEach,
    postcssApply,
    postcssSimpleVars,
    postcssCustomMedia,
    postcssCustomSelectors,
    postcssNested,
    postcssCssVariables,
    postcssSelectorNot,
    postcssCalc,
    postcssLogicalProps,
    postcssFlexbugsFixes,
    autoprefixer({browsers: AUTOPREFIXER_BROWSERS}),
    mdcss({
      theme: mdcssGithub({
        title:    'Untangled Styleguide',
        color:    '#0d2c54',
        nav:      [{name: 'Github', url: 'https://github.com/untangled-web/untangled-stylekit'},
                   {name: 'NPM', url: 'https://www.npmjs.com/package/untangled-stylekit'},
                   {name: 'Stats', url: 'http://cssstats.com/stats?link=http%3A%2F%2Funtangled-web.github.io%2Funtangled-stylekit%2Fguide.css'},
                   {name: 'Validation', url: 'http://jigsaw.w3.org/css-validator/validator?uri=http%3A%2F%2Funtangled-web.github.io%2Funtangled-stylekit%2Fguide.css&profile=css3&usermedium=all&warning=1&vextwarning=&lang=en'}],
        examples: {
          htmlcss: 'overflow: hidden;',
          bodycss: 'overflow-x: hidden; padding: 20px;',
          css:     ['guide.css',
                    'fonts/source-sans-pro/source-sans-pro.css?675981'],
          bodyjs: ['js/jquery-2.2.3.min.js?9083278132',
                   'js/d3.min.js?123080874',
                   'js/dom.js?89871234978']
        } }),
      assets: ['img', 'fonts', 'js']
    }),
    mqpacker,
    cssnano,
    postcssReporter({clearMessages: true})
  ];

  // For best performance, don't add Sass partials to `gulp.src`
  return gulp.src(['src/*.css'])
    .pipe($.sourcemaps.init())
    .pipe($.postcss(PROCESSORS)).on('error', gutil.log)
    .pipe($.sourcemaps.write('./'))
    .pipe(gulp.dest('styleguide'));
});

// Clean output directory
gulp.task('clean', () => del(['.tmp', 'resources/public/*', '!resources/public/.git', '!resources/public/js'], {dot: true}));

// Watch files for changes & reload
gulp.task('serve', ['styles'], () => {

  browserSync({
      notify: false,
      // Customize the Browsersync console logging prefix
      logPrefix: 'USK',
      // Allow scroll syncing across breakpoints
      // scrollElementMapping: ['main', '.mdl-layout'],
      // Run as an https by uncommenting 'https: true'
      // Note: this uses an unsigned certificate which on first access
      //       will present a certificate warning in the browser.
      // https: true,
      server: ['.tmp', 'styleguide'],
      reloadDelay: 1000,
      open: false,
      port: 7332
  });

  gulp.watch(['src/**/*.css', 'index.css'], ['styles', reload]);
  gulp.watch(['src/**/*.md'], ['styles', reload]);
});

// Build production files, the default task
gulp.task('default', cb =>
  runSequence(
    'styles',
    cb
  )
);

// Run PageSpeed Insights
gulp.task('pagespeed', cb =>
  // Update the below URL to the public URL of your site
  pagespeed('example.com', {
    strategy: 'mobile'
    // By default we use the PageSpeed Insights free (no API key) tier.
    // Use a Google Developer API key if you have one: http://goo.gl/RkN0vE
    // key: 'YOUR_API_KEY'
  }, cb)
);

// Load custom tasks from the `tasks` directory
// Run: `npm install --save-dev require-dir` from the command-line
// try { require('require-dir')('tasks'); } catch (err) { console.error(err); }
