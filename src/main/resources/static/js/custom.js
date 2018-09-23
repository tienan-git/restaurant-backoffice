// scrollUp full options
$(function () {
	$.scrollUp({
		scrollName: 'scrollUp', // Element ID
		scrollDistance: 180, // Distance from top/bottom before showing element (px)
		scrollFrom: 'top', // 'top' or 'bottom'
		scrollSpeed: 300, // Speed back to top (ms)
		easingType: 'linear', // Scroll to top easing (see http://easings.net/)
		animation: 'fade', // Fade, slide, none
		animationSpeed: 200, // Animation in speed (ms)
		scrollTrigger: false, // Set a custom triggering element. Can be an HTML string or jQuery object
		//scrollTarget: false, // Set a custom target element for scrolling to the top
		scrollText: '<i class="icon-flight"></i>', // Text for element, can contain HTML // Text for element, can contain HTML
		scrollTitle: false, // Set a custom <a> title if required.
		scrollImg: false, // Set true to use image
		activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
		zIndex: 2147483647 // Z-Index for the overlay
	});
});

// Material Button
var element, circle, d, x, y;
$(".btn").click(function(e) {
	element = $(this);
	if(element.find(".circless").length == 0)
	element.prepend("<span class='circless'></span>");
	circle = element.find(".circless");
	circle.removeClass("animate");
	if(!circle.height() && !circle.width())
	{
		d = Math.max(element.outerWidth(), element.outerHeight());
		circle.css({height: d, width: d});
	}
	x = e.pageX - element.offset().left - circle.width()/2;
	y = e.pageY - element.offset().top - circle.height()/2;
	
	circle.css({top: y+'px', left: x+'px'}).addClass("animate");
});

// Loading
$(function() {
	$(".loading-wrapper").fadeOut(2000);
});

//Todo List
$(function() {
	$( '.task-list' ).on( 'click', 'li.task', function() {
		$(this).toggleClass('completed' );
	});
});

// Todo list
$('.todo-list').on('click', 'li.list', function() {
	$(this).toggleClass('completed');
});


// Loading
$(function() {
	$("#loading-wrapper").fadeOut(5000);
});

//Bank Copy
$(document).ready(function() {
  var arrayLen = $(".form_block").length;
  var frm_cnt = arrayLen - 1;
  var original = $('#form_block\\[' + frm_cnt + '\\]');
  var originCnt = frm_cnt;
  $('#addBank').click(function() {
    var clone = $('#form_block\\[' + frm_cnt + '\\]');
    frm_cnt++;

    original
    .clone()
    .hide()
    .insertAfter(clone)
    .attr('id', 'form_block[' + frm_cnt + ']') // クローンのid属性を変更。
    .find('span.bank_no').text(frm_cnt + 1)
    .end() // 一度適用する
    .find('input, textarea').each(function(idx, obj) {
        $(obj).attr({
            id: $(obj).attr('id').replace(/\[[0-9]\]+$/, '[' + frm_cnt + ']'),
            name: $(obj).attr('name').replace(/\[[0-9]\]+$/, '[' + frm_cnt + ']')
        });
        if ($(obj).attr('type') == 'text') {
          $(obj).val('');
        }
    });

    // clone取得
    clone = $('#form_block\\[' + frm_cnt + '\\]');
    clone.find('span.text-danger').each(function(idx, obj) {
        $(obj).remove()
    });
// clone.children('span.close').show();
    clone.slideDown('slow');
  });
});

//利回り Copy
$(document).ready(function() {
  var arrayLen = $(".yield_block").length;
  var frm_cnt = arrayLen - 1;
  var original = $('#yield_block\\[' + frm_cnt + '\\]');
  var originCnt = frm_cnt;
  $('#addYield').click(function() {
    var clone = $('#yield_block\\[' + frm_cnt + '\\]');
    frm_cnt++;

    original
    .clone()
    .hide()
    .insertAfter(clone)
    .attr('id', 'yield_block[' + frm_cnt + ']') // クローンのid属性を変更。
    .find('span.yield_no').text(frm_cnt + 1)
    .end() // 一度適用する
    .find('input, textarea').each(function(idx, obj) {
        $(obj).attr({
            id: $(obj).attr('id').replace(/\[[0-9]\]+$/, '[' + frm_cnt + ']'),
            name: $(obj).attr('name').replace(/\[[0-9]\]+$/, '[' + frm_cnt + ']')
        });
        if ($(obj).attr('type') == 'text') {
          $(obj).val('');
        }
    });

    // clone取得
    clone = $('#yield_block\\[' + frm_cnt + '\\]');
// clone.children('span.close').show();
    clone.slideDown('slow');
  });
});

//利息支払日 Copy
$(document).ready(function() {
  var arrayLen = $(".paymentDate_block").length;
  var frm_cnt = arrayLen - 1;
  var original = $('#paymentDate_block\\[' + frm_cnt + '\\]');
  var originCnt = frm_cnt;
  $('#addPaymentDate').click(function() {
    var clone = $('#paymentDate_block\\[' + frm_cnt + '\\]');
    frm_cnt++;

    original
    .clone()
    .hide()
    .insertAfter(clone)
    .attr('id', 'paymentDate_block[' + frm_cnt + ']') // クローンのid属性を変更。
    .find('span.paymentDate_no').text(frm_cnt + 1)
    .end() // 一度適用する
    .find('input, textarea').each(function(idx, obj) {
        $(obj).attr({
            id: $(obj).attr('id').replace(/\[[0-9]\]+$/, '[' + frm_cnt + ']'),
            name: $(obj).attr('name').replace(/\[[0-9]\]+$/, '[' + frm_cnt + ']')
        });
        if ($(obj).attr('type') == 'text') {
          $(obj).val('');
        }
    });

    // clone取得
    clone = $('#paymentDate_block\\[' + frm_cnt + '\\]');
// clone.children('span.close').show();
    clone.slideDown('slow');
  });
});

// 新規申込の場合、継続元一覧を非表示
$(document).ready(function() {	
  $('[name="purchaseType"]:radio').change( function() {
    if($('[id=purchaseType1]').prop('checked')){
      $('[id=purchaseTypeLabel]').hide();
      $('[id=purchaseTypeList]').hide();
    } else if ($('[id=purchaseType2]').prop('checked')) {
      $('[id=purchaseTypeLabel]').fadeIn();
      $('[id=purchaseTypeList]').fadeIn();
    } 
  });
});
