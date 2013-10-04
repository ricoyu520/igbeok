$(document).ready(function() {
    initMenuAction();
    articalDisplayControl();
    articalCategoryControl();
});

function initMenuAction() {
    var $navTopMenu = $('.navbar.navbar-inverse.navbar-fixed-top');
    //Add remove active class
    $navTopMenu.click(function(event) {
        $navTopMenu.find('li').removeClass("active");
        $(event.target).parent().addClass("active");
    });
    
    //Show register form
    $navTopMenu.find('a[href="#register"]').click(function() {
        $('#register').modal();
    });
    $navTopMenu.find('.navbar-form .btn').click(function(event) {
        event.preventDefault();
        var formData = $navTopMenu.find('navbar-form').serialize();
        /**
        $.post(
            $('.navbar navbar-form').attr('action'),
            formData,
            function() {
                //login success code
            }
        );
        */
        hideLoginForm();
    });
    
    $('#logout').click(function(event) {
        event.preventDefault();
        showLoginForm();
    });
}

function hideLoginForm() {
    $('.navbar.navbar-inverse.navbar-fixed-top .navbar-form').hide('normal');
    $('.navbar.navbar-inverse.navbar-fixed-top p.pull-right').show('normal');
}

function showLoginForm() {
    $('.navbar.navbar-inverse.navbar-fixed-top .navbar-form').show('normal');
    $('.navbar.navbar-inverse.navbar-fixed-top p.pull-right').hide('normal');    
}

//鼠标移动到tab上就切换
/**
$('#navTabs .nav.nav-tabs a').hover(function() {
     $('#navTabs .nav.nav-tabs li').removeClass('active');
     $(this).parent().addClass('active');
     
     $('#navTabs .tab-content div').hide();
     $('#navTabs .tab-content').find($(this).attr('href')).show();
});
*/

function articalCategoryControl() {
    $('#navTabs .tab-content .tab-pane li a').each( function(index, linkElement) {
        var text = $(linkElement).text();
        if(text.length > 25) {
            $(linkElement).text(text.slice(0, 23) + '...');
        }
    });
}

//For Articals
function articalDisplayControl() {
    $('.taichiArticals h4').each(function() {
        if($(this).text().length > 28 ){
            var textShort = $(this).text().slice(0, 26) + '...';
            $(this).text(textShort);
        }
    });
    $('.taichiArticals h4+p').each(function() {
        if($(this).text().length > 100 ){
            var textShort = $(this).text().slice(0, 103) + '...';
            $(this).text(textShort);
        }
    });
}
