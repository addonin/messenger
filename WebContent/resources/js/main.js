var userId = "";
var sender = "";
var receiver = "";

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
					relation = "<a href=\"javascript: deleteRelation('candidate', '"
							+ from
							+ "', '"
							+ to
							+ "')\"><span id=\""
							+ from
							+ "_"
							+ to
							+ "\" class=\"glyphicon glyphicon-minus\"></span></a>";
					$('#' + from + "_" + to).replaceWith(relation);
					break;
				case "offer":
					relation = "<a href=\"javascript: deleteRelation('friend', '"
							+ from
							+ "', '"
							+ to
							+ "')\"><span id=\""
							+ from
							+ "_"
							+ to
							+ "\" class=\"glyphicon glyphicon-remove\"></span></a>";
					$('#' + from + "_" + to).replaceWith(relation);
					break;
				}
			} else {
				alert("Peristance violation");
				switch (state) {
				case "no":
					relation = "<a href=\"javascript: addRelation('offer', '"
							+ from
							+ "', '"
							+ to
							+ "')\"><span id=\""
							+ from
							+ "_"
							+ to
							+ "\" class=\"glyphicon glyphicon-ok\"></span></a>";
					$('#' + from + "_" + to).replaceWith(relation);
					break;
				case "offer":
					relation = "<a href=\"javascript: addRelation('no', '"
							+ from
							+ "', '"
							+ to
							+ "')\"><span id=\""
							+ from
							+ "_"
							+ to
							+ "\" class=\"glyphicon glyphicon-plus\"></span></a>";
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
					relation = "<a href=\"javascript: addRelation('no', '"
							+ from
							+ "', '"
							+ to
							+ "')\"><span id=\""
							+ from
							+ "_"
							+ to
							+ "\" class=\"glyphicon glyphicon-plus\"></span></a>";
					$('#' + from + "_" + to).replaceWith(relation);
					break;
				case "friend":
					relation = "<a href=\"javascript: addRelation('offer', '"
							+ from
							+ "', '"
							+ to
							+ "')\"><span id=\""
							+ from
							+ "_"
							+ to
							+ "\" class=\"glyphicon glyphicon-ok\"></span></a>";
					$('#' + from + "_" + to).replaceWith(relation);
					break;
				}
			} else {
				alert("Peristance violation");
				switch (state) {
				case "candidate":
					relation = "<a href=\"javascript: deleteRelation('friend', '"
							+ from
							+ "', '"
							+ to
							+ "')\"><span id=\""
							+ from
							+ "_"
							+ to
							+ "\" class=\"glyphicon glyphicon-remove\"></span></a>";
					$('#' + from + "_" + to).replaceWith(relation);
					break;
				case "friend":
					relation = "<a href=\"javascript: deleteRelation('candidate', '"
							+ from
							+ "', '"
							+ to
							+ "')\"><span id=\""
							+ from
							+ "_"
							+ to
							+ "\" class=\"glyphicon glyphicon-minus\"></span></a>";
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
	alert("sendMessage");
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

function openChat(sender_id, receiver_id) {
	$.ajax({
		type : 'POST',
		url : 'controller',
		dataType : 'text',
		data : {
			'ajaxCommand' : "getconversation",
			'senderId' : sender_id,
			'receiverId' : receiver_id
		},
		success : function(data) {
			$('#messageArea ul').empty();
			$("#chatWindow").show();
			var jsonMessages = $.parseJSON(data);
			for (var i = 0; i < jsonMessages.length; i++) {
				var timestamp = new Date(jsonMessages[i].timestamp);
				timestamp = timestamp.getFullYear() + "-"
						+ (timestamp.getMonth() + 1) + "-"
						+ timestamp.getDate() + " "
						+ timestamp.getHours() + ":"
						+ timestamp.getMinutes() + ":"
						+ timestamp.getSeconds();
				if (jsonMessages[i].receiverId === receiver_id) {
					$('#messageArea ul').append('<li class="left clearfix"><div class="chat-body clearfix"><div class="header"><strong class="primary-font">You</strong><small class="pull-right text-muted"><span class="glyphicon glyphicon-time"></span>'
											+ timestamp
											+ '</small></div><p>'
											+ jsonMessages[i].text
											+ '</p></div></li>');
				} else if (jsonMessages[i].receiverId === sender_id) {
					$('#messageArea ul').append('<li class="right clearfix"><div class="chat-body clearfix"><div class="header"><strong class="pull-right primary-font">'
											+ jsonMessages[i].senderId
											+ '</strong><small class=" text-muted"><span class="glyphicon glyphicon-time"></span>'
											+ timestamp
											+ '</small></div><p>'
											+ jsonMessages[i].text
											+ '</p></div></li>');
				}
			}
		},
		error : function() {
			alert("AJAX request failed");
		}
	});

	$("#from-to").html("From " + sender_id + " to " + receiver_id);
	sender = sender_id;
	receiver = receiver_id;
}

$(document).ready(function() {

	userId = $('#userId').val();

	var interval = setInterval(function() {
		$.ajax({
			type : 'POST',
			url : 'controller',
			dataType : 'text',
			data : {
				'ajaxCommand' : "getupdates",
				'userId' : userId
			},
			success : function(data) {
				var result = eval(data);
				if (result instanceof Object) {
					$('#unreadMessages').empty();
					for (var i = 0; i < result.length; i++) {
						$('#unreadMessages').append('<div class="alert alert-success"><a href="#" class="close" data-dismiss="alert" data-message_id="'
												+ result[i].messageId
												+ '">&times;</a><strong>From '
												+ result[i].senderId
												+ '</strong>'
												+ result[i].timestamp
												+ '<br/>'
												+ result[i].text
												+ '</div>');
					}
				} else if (result > 0) {
					$('#messagesButton').html('Messages <span class="badge">'
											+ result
											+ '</span>');
				}
			},
			error : function() {
				alert("AJAX request failed");
			}
		});
	}, 10000);

	$('#myTab a').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});

	$('#findusers').on('keyup',	function(e) {
		if (this.value.replace(/\s/g, "") != ""	&& !e.shiftKey) {
			$('#userinfo').hide();
			$.ajax({
				type : "POST",
				url : 'controller',
				dataType : "json",
				data : {
					'ajaxCommand' : $('#findusers').attr("name"),
					'search' : this.value.trim()
				},
				success : function(data) {
					$('#findusersresult').empty();
					for (var i = 0; i < data.length; i++) {
						if (data[i].initiator !== data[i].userID) {
							var relation;
							switch (data[i].relation) {
							case "no":
								relation = "<a href=\"javascript: addRelation('"
										+ data[i].relation
										+ "', "
										+ data[i].initiator
										+ ", "
										+ data[i].userID
										+ ")\"><span id=\""
										+ data[i].initiator
										+ "_"
										+ data[i].userID
										+ "\" class=\"glyphicon glyphicon-plus\"></span></a>";
								break;
							case "candidate":
								relation = "<a href=\"javascript: deleteRelation('"
										+ data[i].relation
										+ "', "
										+ data[i].initiator
										+ ", "
										+ data[i].userID
										+ ")\"><span id=\""
										+ data[i].initiator
										+ "_"
										+ data[i].userID
										+ "\" class=\"glyphicon glyphicon-minus\"></span></a>";
								break;
							case "offer":
								relation = "<a href=\"javascript: addRelation('"
										+ data[i].relation
										+ "', "
										+ data[i].initiator
										+ ", "
										+ data[i].userID
										+ ")\"><span id=\""
										+ data[i].initiator
										+ "_"
										+ data[i].userID
										+ "\" class=\"glyphicon glyphicon-ok\"></span></a>";
								break;
							case "friend":
								relation = "<a href=\"javascript: deleteRelation('"
										+ data[i].relation
										+ "', "
										+ data[i].initiator
										+ ", "
										+ data[i].userID
										+ ")\"><span id=\""
										+ data[i].initiator
										+ "_"
										+ data[i].userID
										+ "\" class=\"glyphicon glyphicon-remove\"></span></a>";
								break;
							default:
								relation = "<i class=\"glyphicon glyphicon-question-sign\"></i>";
							}
							$('#findusersresult').append("<div class=\"panel panel-default panel-custom right-inner-addon\"><div class=\"panel-body resultitem\" data-firstname="
													+ data[i].firstname
													+ " data-lastname="
													+ data[i].lastname
													+ " data-email="
													+ data[i].email
													+ " data-info=\""
													+ data[i].info
													+ "\" data-photo="
													+ data[i].photo
													+ "><span>"
													+ data[i].lastname
													+ " "
													+ data[i].firstname
													+ relation
													+ "</div></div>");
						} else {
							// $('#findusersresult').append("<div
							// class=\"panel
							// panel-default
							// panel-custom
							// right-inner-addon
							// resultitem\"><div
							// class=\"panel-body\"><span>"
							// +
							// data[i].lastname
							// + " " +
							// data[i].firstname
							// +
							// "</div></div>").data("userdata",
							// data[i]);
						}
					}
				},
				error : function() {
					alert("AJAX request failed");
				}

			});
		}
	});

	$(document).on('mouseover',	'.resultitem', function(event) {
		var firstname = $(this).data("firstname");
		var lastname = $(this).data("lastname");
		var email = $(this).data("email");
		var info = $(this).data("info");
		var photo = $(this).data("photo");
		$('#userinfo #userfirstlastname').html(
				lastname + " " + firstname);
		$('#userinfo #useremail').html(email);
		$('#userinfo #info').html(info);
		$('#userinfo #userimage img')
				.attr("src", photo);
		$('#userinfo').show();
	});

	$(document).on('mouseout', '.resultitem', function() {
		$('#userinfo').hide();
	});

	$(document).mouseup(function(e) {
		var button = $(".send-message");
		var chatWindow = $("#chatWindow");

		if (!button.is(e.target)
				&& !chatWindow.is(e.target)
				&& button.has(e.target).length === 0
				&& chatWindow.has(e.target).length === 0) {
			$("#chatWindow").hide();
		}
	});

	$("#btn-chat").on("click", function() {
		$.ajax({
			type : 'POST',
			url : 'controller',
			dataType : 'text',
			data : {
				'ajaxCommand' : "sendmessage",
				'fromID' : sender,
				'toID' : receiver,
				'message' : $('#btn-input').val(),
				'timestamp' : new Date().getTime()
			},
			success : function(data) {
				$('#messageArea ul').append('<li class="left clearfix"><div class="chat-body clearfix"><div class="header"><strong class="primary-font">You</strong><small class="pull-right text-muted"><span class="glyphicon glyphicon-time"></span>'
										+ new Date()
										+ '</small></div><p>'
										+ $('#btn-input').val()
										+ '</p></div></li>');
				$('#btn-input').val('');
				return Boolean(data);
			},
			error : function() {
				alert("AJAX request failed");
			}
		});
	});

	$('#btn-input').keypress(function(e) {
		if (e.which === 13) {
			jQuery(this).blur();
			jQuery('#btn-chat').focus().click();
			return false;
		}
	});

	$('.close').click(function() {
		$.ajax({
			type : 'POST',
			url : 'controller',
			dataType : 'text',
			data : {
				'ajaxCommand' : "readstatus",
				'messageId' : $(this).data("message_id")
			},
			success : function(data) {
				return Boolean(data);
			},
			error : function() {
				alert("AJAX request failed");
			}
		});
	});

	// $('.send-message').on('click', function(event) {
	// alert($(this).data("sender"), $(this).data("receiver"));
	// openChat($(this).data("sender"),
	// $(this).data("receiver"));
	// });

});