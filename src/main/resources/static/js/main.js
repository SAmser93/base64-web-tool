function process(type) {

    var text = jQuery('#text').val().trim();
    var result = jQuery('#result');
    var executionTime = jQuery('#txtExecutionTime');

    if(text===''){
        alert("Введена пустая строка");
        return;
    }

    var data = JSON.stringify(
        { data : text });

    jQuery.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        cache: false,
        url: 'rest/' + type + '/',
        data: data,
        async: true,
        success: function (data) {
            result.val(data.resultData);
            executionTime.text(data.elapsedTime);
        },
        error: function (data) {
            alert(data.responseJSON.errorMessage);
        }
    });

}