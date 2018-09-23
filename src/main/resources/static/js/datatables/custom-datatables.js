// Basic DataTable
$(function(){
	$('#basicExample').DataTable({
		'iDisplayLength': 25,
		// 件数切替機能 無効
	    lengthChange: false,
	    // 検索機能 無効
	    searching: false,
		'language': {
			"paginate": {
				"sFirst": "先頭",
				"sLast": "後尾",
				"sNext": "次",
				"sPrevious": "前"
			},
			"sEmptyTable": "対象データがありません",
			'sInfo': '_START_ から _END_ 件 表示 (_TOTAL_ 件中)',
			"sInfoEmpty": "0 から 0 件 表示 (0 件中)",
		}
	});
});

// Autofill DataTable
$(function(){
	$('#autoFill').DataTable({
		autoFill: true,
		'iDisplayLength': 25,
	});
});

// Fixed Header DataTable
$(function(){
	var table = $('#fixedHeader').DataTable( {
		fixedHeader: true,
		'iDisplayLength': 25,
	});
});

// Responsive Table
$(function(){
	$('#responsiveTable').DataTable({
		responsive: true,
		'iDisplayLength': 25,
	});
});

$(function(){
  $('#scrollTable').DataTable({
    "scrollY": "200px",
    "scrollCollapse": true,
    "paging": false,
    'iDisplayLength': 25,
  });
});
