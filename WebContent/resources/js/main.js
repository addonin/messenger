function addRelation(state, from, to) {
	$.ajax({
		type : 'POST',
		url : 'controller',
		dataType : 'text',
		data : {
			'ajaxCommand' : "addrelation",
			'fromID' : from,
			'toID' : to,
			'state' : state
		},
		success : function(response) {
			if (response == "1") {
				switch (state) {
					case "no":
						relation = "<a href=\"javascript: deleteRelation('candidate', '" + from + "', '" + to + "')\"><span id=\"" + from + "_" + to + "\" class=\"glyphicon glyphicon-minus\"></span></a>";
						$('#' + from + "_" + to).replaceWith(relation);
						break;
					case "offer":
						relation = "<a href=\"javascript: deleteRelation('friend', '" + from + "', '" + to + "')\"><span id=\"" + from + "_" + to + "\" class=\"glyphicon glyphicon-remove\"></span></a>";
						$('#' + from + "_" + to).replaceWith(relation);
						break;
				}				 
			} else {
				alert("Peristance violation");
				switch (state) {
					case "no":
						relation = "<a href=\"javascript: addRelation('offer', '" + from + "', '" + to + "')\"><span id=\"" + from + "_" + to + "\" class=\"glyphicon glyphicon-ok\"></span></a>";
						$('#' + from + "_" + to).replaceWith(relation);
						break;
					case "offer":
						relation = "<a href=\"javascript: addRelation('no', '" + from + "', '" + to + "')\"><span id=\"" + from + "_" + to + "\" class=\"glyphicon glyphicon-plus\"></span></a>";
						$('#' + from + "_" + to).replaceWith(relation);
						break;
				}
			}
		},
		error : function() {
			alert("AJAX request failed");
		}
	});	
}

function deleteRelation(state, from, to) {
	$.ajax({
		type : 'POST',
		url : 'controller',
		dataType : 'text',
		data : {
			'ajaxCommand' : "deleterelation",
			'fromID' : from,
			'toID' : to,
			'state' : state
		},
		success : function(response) {
			if (response == "1") {
				switch (state) {
					case "candidate":
						relation = "<a href=\"javascript: addRelation('no', '" + from + "', '" + to + "')\"><span id=\"" + from + "_" + to + "\" class=\"glyphicon glyphicon-plus\"></span></a>";
						$('#' + from + "_" + to).replaceWith(relation);
						break;
					case "friend":
						relation = "<a href=\"javascript: addRelation('offer', '" + from + "', '" + to + "')\"><span id=\"" + from + "_" + to + "\" class=\"glyphicon glyphicon-ok\"></span></a>";
						$('#' + from + "_" + to).replaceWith(relation);
						break;
				}				 
			} else {
				alert("Peristance violation");
				switch (state) {
					case "candidate":
						relation = "<a href=\"javascript: deleteRelation('friend', '" + from + "', '" + to + "')\"><span id=\"" + from + "_" + to + "\" class=\"glyphicon glyphicon-remove\"></span></a>";
						$('#' + from + "_" + to).replaceWith(relation);
						break;
					case "friend":
						relation = "<a href=\"javascript: deleteRelation('candidate', '" + from + "', '" + to + "')\"><span id=\"" + from + "_" + to + "\" class=\"glyphicon glyphicon-minus\"></span></a>";
						$('#' + from + "_" + to).replaceWith(relation);
						break;
				}
			}
		},
		error : function() {
			alert("AJAX request failed");
		}
	});	
}

function findRelation(from, to) {
	$.ajax({
		type : 'POST',
		url : 'controller',
		dataType : 'text',
		data : {
			'ajaxCommand' : "findrelation",
			'fromID' : from,
			'toID' : to
		},
		success : function(data) {
			return Boolean(data);
		},
		error : function() {
			alert("AJAX request failed");
		}
	});
}


$(document).ready(function() {
	
	$('#myTab a').click(function (e) {
	  e.preventDefault();
	  $(this).tab('show');
	});
	
	$('#findusers').on('keyup', function(e) {
		if (this.value.replace(/\s/g,"") != "" && !e.shiftKey) {
			$.ajax({
				type : "POST",
				url : 'controller',
				dataType: "json",
				data : {
					'ajaxCommand' : $('#findusers').attr("name"),
					'search' : this.value.trim()
				},
				success : function(data) {
					$('#findusersresult').empty();
					for (var i = 0; i < data.length; i++) {
						if (data[i].initiator != data[i].userID) {
							var relation;
							switch (data[i].relation) {
								case "no":
									relation = "<a href=\"javascript: addRelation('" + data[i].relation + "', " + data[i].initiator + ", " + data[i].userID + ")\"><span id=\"" + data[i].initiator + "_" + data[i].userID + "\" class=\"glyphicon glyphicon-plus\"></span></a>";
									break;
								case "candidate":
									relation = "<a href=\"javascript: deleteRelation('" + data[i].relation + "', " + data[i].initiator + ", " + data[i].userID + ")\"><span id=\"" + data[i].initiator + "_" + data[i].userID + "\" class=\"glyphicon glyphicon-minus\"></span></a>";								
									break;
								case "offer":
									relation = "<a href=\"javascript: addRelation('" + data[i].relation + "', " + data[i].initiator + ", " + data[i].userID + ")\"><span id=\"" + data[i].initiator + "_" + data[i].userID + "\" class=\"glyphicon glyphicon-ok\"></span></a>";								
									break;
								case "friend":
									relation = "<a href=\"javascript: deleteRelation('" + data[i].relation + "', " + data[i].initiator + ", " + data[i].userID + ")\"><span id=\"" + data[i].initiator + "_" + data[i].userID + "\" class=\"glyphicon glyphicon-remove\"></span></a>";								
									break;
								default:
									relation = "<i class=\"glyphicon glyphicon-question-sign\"></i>";
							}
							$('#findusersresult').append("<div class=\"panel panel-default panel-custom right-inner-addon\"><div class=\"panel-body resultitem\"><span>" + 
									data[i].lastname + " " + data[i].firstname + relation + "</div></div>").data("userdata", data[i]);
						} else {
							$('#findusersresult').append("<div class=\"panel panel-default panel-custom right-inner-addon resultitem\"><div class=\"panel-body\"><span>" + 
									data[i].lastname + " " + data[i].firstname + "</div></div>").data("userdata", data[i]);
						}
					}					
				},
				error : function() {
					alert("AJAX request failed");
				}
				
			});
		}
	});
	
	$(document).on('mouseover', '.resultitem', function(event) {
		userdata = $(event.target).parent().parent().data("userdata");
		$('#userinfo #userfirstlastname').html(userdata.lastname + " " + userdata.firstname);
		$('#userinfo').show();
	});
	
	$(document).on('mouseout', '.resultitem', function() {
		$('#userinfo').hide();
	});
	
});

