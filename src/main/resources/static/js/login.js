/**
 * Created by a.lam.tuan on 31. 5. 2018.
 */



$( document ).ready(function() {
        $("#login-form-link").click(function(e) {
            $("#login-form").delay(100).fadeIn(100);
            $("#register-form").fadeOut(100);
            $('#register-form-link').removeClass("active");
            $(this).addClass('active');
            e.preventDefault();
        });
        $("#register-form-link").click(function(e) {
            $("#register-form").delay(100).fadeIn(100);
            $("#login-form").fadeOut(100);
            $("#login-form-link").removeClass("active");
            $(this).addClass("active");
            e.preventDefault();
        });
});

/*
$("#login-form-link").click(function() {
    console.log("login clicked")
      $("#login-form").delay(100).fadeIn(100);
     $("#register-form").fadeOut(100);
     $("#register-form-link").removeClass('active');
     $(this).addClass('active');
     e.preventDefault();

});
$("#register-form-link").click(function(e) {
    console.log("register clicked")

    $("#register-form").delay(100).fadeIn(100);
    $("#login-form").fadeOut(100);
    $("#login-form-link").removeClass('active');
    $(this).addClass('active');
    e.preventDefault();
});
*/