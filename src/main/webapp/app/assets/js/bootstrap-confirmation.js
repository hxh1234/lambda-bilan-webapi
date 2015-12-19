////////// Simple usage //////////
$(".popconfirm").popConfirm();

////////// Complete usage //////////

// (example jquery click event)
$('#important_action').click(function() {
    alert('You clicked, and valided this button !');
});

// Full featured example
$("[data-toggle='confirmation']").popConfirm({
    title: "Really ?",
    content: "I have warned you !",
    placement: "bottom" // (top, right, bottom, left)
});