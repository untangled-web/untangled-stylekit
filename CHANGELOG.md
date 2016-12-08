# Change Log
All notable changes to this project will be documented in this file.
This project adheres to [Semantic Versioning](http://semver.org/).

## [Unreleased]

## [1.6.0] - 2016-09-30

## Changed
- Alignment with responsive small leader/trailer classes
- Colour palettes
- Composition -> Typography
- hide-a11y in motion.css
- Bug fixes and improvements

## Added
- Center-middle property
- Responsive button classes
- Input object
- Button small, block and informative
- Card modifiers and elements
- Label actions and large
- Accordion name
- Modal section, heading, toolbar, and footer
- Line graph, pie chart and stacked bar in visualizations

## Removed
- Cursor rules from major components
- All references to gold colors
- Accordion actions

## [1.5.10] - 2016-08-23

## Changed
- README.md
- Separate settings from utilities layer
- Moved utilities layer to the bottom of the cascade
- Visualizations are now in objects
- Field inputs
- Badge inside a button
- Icon inside a button
- Form settings
- Page font family fallbacks
- Button group box margins
- Responsive `.o-modal__card--wide`

## Added
- core.css
- all.css for layers
- `.c-label:empty`
- Menu sections
- Grid docs
- Fullscreen styles
- `.c-dropdown__select--small`
- Font adjustment for page font family fallbacks

## Removed
- a11y.css
- size.css
- syntax.css
- `.c-dropdown__select--xlarge`
- `.u-column--icons`

## [1.5.9] - 2016-08-10

## Fixed
- Toolbar experimental feature media

## [1.5.8] - 2016-08-10

## Removed
-- Height media queries

## [1.5.7] - 2016-08-10

## Fixed
- Icon sizes by merging `.c-icon--auto`
- Media queries `--sm` and `--md-up` use OR

## [1.5.6] - 2016-08-10

## Changed
- Card actions for better interaction
- `.c-icon--auto` with max dimensions
- Dropdown toggle to truncate longer text
- Label dark theme
- Small media query
- Select height
- `--global-radial` is now `100px`

## Added
- Print styles for simple layouts
- `.c-button--small`
- `.c-dropdown--wide`
- `.o-toolbar__view--[handheld/desktop]`
- `.o-toolbar__actions--[handheld/desktop]`

## Fixed
- Typo in arrow property

## [1.5.5] - 2016-08-03

## Changed
- Buttons shouldn't have a hand cursor in a webapp
- `--orange-800` to match new orange brand
- `.has-toolbar` to target only medium and larger devices
- `.o-drawer__backdrop` higher z-index
- `.o-drawer__brand` wider
- `.u-fixed--top-center` z-index

## Added
- A `/dist` folder for an easy way to get running with a standalone app
- `.o-accordion__content--bleed`
- `.c-icon--auto` for fluid layouts
- `.o-toolbar__info` inline headings
- `.u-text-overlay` for image editing interactions

## Fixed
- `.c-card--row` `:active` border

## [1.5.4] - 2016-07-18

## Changed
- Monospaced font stack
- Modal
- Icon radius classes
- Input z-index
- Label box-model
- Sidebar hidden scrollbar in WebKit
- Toolbar
- Global border-radius
- Spectrum colors
- Card box-model and dark mode
- Gulp + Babel.js
- Full codebase pass on CSS linting
- NPM devDependencies
- Node version of gitignore

## Added
- IE Hack Media Queries
- Fullscreen Media Query (Experimental)
- Legacy Browser Media Queries
- Standalone Media Query
- Field classes
- Message cap
- Dark Theme
- Border Radius Properties
- Pulse Animation
- Menu child classes
- Accordion info padding
- Editor margins
- Active listing z-index
- `.o-modal__card--third`
- `.o-modal--content`
- `.c-card--bleed`
- `.o-accordion--inline`

## Fixed
- Legacy browser bug in article class

## Removed
- Mobile toolbar margin
- Field min-width
- Card overflow
- Accordion fade

## [1.5.3] - 2016-06-27

## Changed
- Relative `.o-button-group` with better borders
- `.c-button--dropdown` utilizes the box model
- `.c-dropdown__menu` uses percentage (%) based positioning
- `.c-dropdown__menu` animates with just a fade now
- Cards now use the same padding with overflows visible.
- Compact `.c-menu` inside a `.c-card`
- Modal doesn't inherit styles from card.
- Modal uses flexbox now.
- Moved `.o-modal__backdrop` to a new component called `.c-backdrop`
- Dropdown has a more semantic layout.
- Improved listing design based on design spec.
- Re-factored button-group style logic

## Added
- `.o-accordion__info--title` for better grouped content

## Fixed
- Sidebar slide animations
- Blink bug with translate for full positioning [Chromium bug](https://goo.gl/vgxEdT)
- Deprecated validation modifiers work again

## [1.5.1] - 2016-06-23

## Changed
- Typos
- Color names
- --positive-copy color-weight
- NPM-related updates
- Browsersync support

## Fixed
- Modal object placement

## [1.5.0] - 2016-06-15

## Changed
- [Breaking] Accordion Refactor
- Colour scales now range from 50 to 900 weight
- Stylelint now uses config-guidelines
- Count command in make
- size.css is a utility group
- Custom Properties all around
- Lowered `.c-dropdown__list` specificity

## Added
- Sidebar object
- Aside object
- Some `.t-dark` theme styles
- `.c-icon--round`
- Message color transition
- Styleguide resource caching
- Slide in/out animations

## Removed
- Nav object in favor of new sidebar

## [1.4.0] - 2016-06-02

## Changed
- Radio control is spec compliant
- Dropdown selector naming in dom.js
- dom.js hide to fade-out
- Button appearance for anchors
- Toolbar dynamic height
- Styleguide

## Added
- orange-700 color value
- Dropdown state colors and modifier classes
- Icon is-selectable state class
- loader-secondary with neutral modifier class
- u-rotate--cw and u-rotate--ccw

## Removed
- Material Design font reference in README.md

## Fixed
- Checkbox for multi-line labels
- Radio for multi-line labels

## [1.3.5] - 2016-05-27

## Changed
- Left align listing name
- Styleguide
- Upgraded mdcss-theme-github

## Added
- postcss-each
- Global disable attribute styles

## Removed
- postcss-contrast
- contrast function instances

## Fixed
- Type scale classes
- function-url-quotes in stylelint

## [1.3.4] - 2016-05-23

## Changed
- Badge icon absolute dimensions

## Removed
- Editor functional active state styles


## [1.3.3] - 2016-05-20

## Changed
- Less-specific :--disabled

## [1.3.2] - 2016-05-18

## Added
- d3 to styleguide
- font-size utility classes
- card--ruled
- table logic to styleguide

## Changed
- menu state selectors
- toolbar disabled state
- wider wrapper
- --arrow svg white
- :--disabled is now more specific
- button icon height for IE changed button text active color

## [1.3.1] - 2016-05-16

## Added
- gitignore log files

## Removed
- Container selector docs
- .o-drawer__account-avatar .c-icon -> .c-icon--large

## Changed
- dropdown--picker -> dropdown--large
- dropdown__picker -> dropdown__viewer
- dropdown__text-toggle -> dropdown__toggle--large
- modal--help -> modal--small
- Simpler modal selectors
- Editor spec compliance
- Iconbar dynamic color
- label--positive -> label--green
- label--informative -> label --blue
- label--informative-alt -> label--magenta
- label--neutral -> label--grey
- label--live -> label--gold
- label--alterable -> label--orange
- label--negative -> label--red

## Fixed
- Drawer shadow value uses global now
- Card active state
- Checkbox and radio pseudo control alignment
- Header fixed positioning
- Reverted back to using --triangle instead of --arrow

## [1.3.0] - 2016-05-13

## Added
- postcss-css-variables
- postcss-apply
- Form elements

## Removed
- doiuse
- csswring
- gulp-util
- postcss-conditionals
- postcss-custom-properties
- postcss-mixins
- stylep-button
- stylep-dropdown
- Button component mixins

### Changed
- All mixins are now apply definitions
- Updated css-mqpacker, glob, mdcss, stylelint
- Updated gulp and stylelint configs
- Restructure assets for guide
- Moved devcards styles into an unused partial
- Update button docs for custom properties

## [1.2.1] - 2016-05-09
### Added
- This change log

### Changed
- stylep-dropdown version bump

## [1.2.0] - 2016-05-06
### Added
- Loader component for a simple branded animation for view loading.
- xlarge modifier for buttons
- wide modifier for notifications
- Input validation element

### Removed
- Custom stylesheet support
- notification-fixed mixin

### Changed
- Deprecated validation component
- Install documentation
- Tooltips match spec
- New modifier argument in triangle mixin

### Fixed
- Modal code style and element naming
- Less strict column bar modifier
- Position fixed top-center without spacing
- row-collapse important
- Color scale classes are important
