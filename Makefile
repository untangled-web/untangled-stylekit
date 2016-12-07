bin = node_modules/.bin/gulp
files=$(filter-out Makefile, $(wildcard *))

install:; @npm install

$(files) %:;@$(bin) $@

count:; find . -name '*.css' -not -path './styleguide*' -not -path './node_modules*' | xargs wc -l

.PHONY: install $(files) count
