// Grid Demo

var cols = $(".selector").parent().siblings().length - 1;

var literalColumn = "<div class='u-column--1'><select class='selector'> <option class='c1'>col-1</option> <option class='c2'>col-2</option> <option class='c3'>col-3</option> <option class='c4'>col-4</option> <option class='c5'>col-5</option> <option class='c6'>col-6</option><option class='c7'>col-7</option> <option class='c8'>col-8</option> <option class='c9'>col-9</option> <option class='c10'>col-10</option> <option class='c11'>col-11</option> <option class='c12'>col-12</option> </select></div></div>"

$('.o-add-column').on('click', function() {
  $('.u-row').append( literalColumn );
  cols++;
  //alert(cols);
});

$(".o-column-switcher-row div").each(function (index, value) { 
  colClass = $(this).attr('class');
  $(this).find(".o-column-switcher").val(colClass);
});

$('.o-column-switcher').change(function() {
  rowColumns = 12;
  colNum = $(this).find("option:selected").text(); // col-3
  stripColNum = Math.abs(colNum.replace("u-column--", "") - rowColumns); // 5
  invNum =  Math.floor(Math.abs(stripColNum / cols));
  invColNum = "u-column--" + invNum;
  otherCol = $(this).parent().siblings();
  otherColNum = otherCol.find(".o-column-switcher");

  $(this).parent().removeClass().addClass(colNum);
  $(otherCol).removeClass().addClass(invColNum);
  $(otherColNum).val(invColNum);
});
//alert(colClass);

$(function() {
    // Dropdown Demo
    $('.js-dropdown-toggle').click(function() {
        $(this).next('.c-dropdown__menu').toggleClass('is-active');
    });

    // Menu Demo
    $('.c-menu__link').click(function() {
        $('.c-menu__link').removeClass('is-active');
        $(this).addClass('is-active');
    });

    // Notification Demo
    $('.c-notification .c-button--icon').click(function() {
        $(this).parent().addClass('u-fade-out');
    });

    // Accordion Demo
    $('.o-accordion .is-nested').click(function() {
      const $accordContent = $(this).next('.c-accordion__content');
      const $accordNested = $(this).siblings().not($accordContent);
      $accordNested.removeClass('is-active');
      $(this).toggleClass('is-active');
      $accordContent.toggleClass('is-active');
    });

    // Iconbar Demo
    $('.o-iconbar__item').click(function() {
        $('.o-iconbar__item').removeClass('is-active');
        $(this).addClass('is-active');
    });

    // Modal Demo
    $('.o-modal__close').click(function() {
        $(this).parents('.o-modal').toggleClass('is-active');
    });

    $('.js-fade-control').click(function() {
        $('.js-fade-example').toggleClass('u-fade-in');
    });

    // Editor
    $('.o-editor__edit').click(function() {
        $(this).parent().parent().parent().parent().find('.is-editable').each(function() {
            var str = $(this).text();
            $(this).replaceWith('<input class="c-input" value="' + str + '" >');
        });
        $(this).parent().parent().parent().parent().addClass('is-active');
    });

    $('.o-editor__done').click(function() {
        $(this).parent().parent().parent().parent().find('.c-input').each(function() {
            $(this).replaceWith("<p class='is-editable'>" + this.value + "</p>");
        });
        $(this).parent().parent().parent().parent().removeClass('is-active');
    });

    $('.js-add-conditions').click(function() {
        $(this).text('Add');
    });

    // Tables
    $('.js-view--swipe').click(function() {
        $('.js-table-view').removeClass('c-table--swipe').removeClass('c-table--toggle').removeClass('c-table--stacked');
        $('.js-table-view').addClass('c-table--swipe');
        $('.js-view--swipe, .js-view--toggle, .js-view--stacked').removeClass('is-active');
        $(this).addClass('is-active');
    });
    $('.js-view--toggle').click(function() {
        $('.js-table-view').removeClass('c-table--swipe').removeClass('c-table--toggle').removeClass('c-table--stacked');
        $('.js-table-view').addClass('c-table--toggle');
        $('.js-view--swipe, .js-view--toggle, .js-view--stacked').removeClass('is-active');
        $(this).addClass('is-active');
    });
    $('.js-view--stacked').click(function() {
        $('.js-table-view').removeClass('c-table--swipe').removeClass('c-table--toggle').removeClass('c-table--stacked');
        $('.js-table-view').addClass('c-table--stacked');
        $('.js-view--swipe, .js-view--toggle, .js-view--stacked').removeClass('is-active');
        $(this).addClass('is-active');
    });
});
