// JavaScript Document

	
	/*----------------------------------------------------*/
	/*	Scroll Navbar
	/*----------------------------------------------------*/
	
	$(window).scroll(function(){	

		"use strict";	
	
		var b = $(window).scrollTop();
		
		if( b > 200 ){		
			$(".navbar.navbar-fixed-top").addClass("scroll-fixed-navbar");	
		} else {
			$(".navbar.navbar-fixed-top").removeClass("scroll-fixed-navbar");
		}
		
	});	
	
	$(window).resize(function(){	

		"use strict";	
	
		var b = $(window).height();
		var b1 = $('header').height();
		var b2 = $('footer').height();
		
		var c=b-(b1+b2);
		$('#content_wrapper').css('min-height',c);
		
	});	
	
	$(document).ready(function(){
		var b = $(window).height();
		var b1 = $('header').height();
		var b2 = $('footer').height();
		
		var c=b-(b1+b2);
		$('#content_wrapper').css('min-height',c);
	});
	
	
	
	
	
	
	/*----------------------------------------------------*/
	/*	Mobile Menu Toggle
	/*----------------------------------------------------*/
	
	$(document).ready(function() {
		
		"use strict";	

		$('.navbar-nav li a').click(function() {				
			$('#navigation-menu').css("height", "1px").removeClass("in").addClass("collapse");
			$('#navigation-menu').removeClass("open");				
		});			
		
		/*$('.flexslider').flexslider({
		 	animation: "slide",
	     	animationLoop: true,
			animationSpeed: 1000,
		});*/
		
		$('.intro_slider').flexslider({
			animation: "slide",
			controlNav: true,
			directionNav: false,  
			slideshowSpeed: 6000,   
			animationSpeed: 1000,
	     	animationLoop: true,
			start: function(slider){
				$('body').removeClass('loading');
			}
		});
	});

	
	
	/*----------------------------------------------------*/
	/*	OnScroll Animation
	/*----------------------------------------------------*/
	/*
	$(document).ready(function(){
	
		"use strict";
	
    	$('.animated').appear(function() {

	        var elem = $(this);
	        var animation = elem.data('animation');

	        if ( !elem.hasClass('visible') ) {
	        	var animationDelay = elem.data('animation-delay');
	            if ( animationDelay ) {
	                setTimeout(function(){
	                    elem.addClass( animation + " visible" );
	                }, animationDelay);

	            } else {
	                elem.addClass( animation + " visible" );
	            }
	        }
	    });
	
	});
	*/
	
	
	/*----------------------------------------------------*/
	/*	Parallax
	/*----------------------------------------------------*/
	
	$(window).bind('load', function() {
	
		"use strict";	
		parallaxInit();
		
	});

	function parallaxInit() {
		if( !/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
			/*$('#skills').parallax("30%", 0.3);
			$('#features').parallax("30%", 0.3);
			$('#statistic_banner').parallax("30%", 0.3);
			$('#promo_line').parallax("30%", 0.3);	*/	
		}	
	}
	
	
	/*----------------------------------------------------*/
	/*	Chat
	/*----------------------------------------------------*/
	if ($(window).width() >= 959) {
		$('#chat').html('<script type="text/javascript">'+
				"var $_Tawk_API={},$_Tawk_LoadStart=new Date();(function(){var s1=document.createElement('script'),s0=document.getElementsByTagName('script')[0];"+
				"s1.async=true;"+
				"s1.src='https://embed.tawk.to/5549e372f82948372f459a25/default';"+
				"s1.charset='UTF-8';"+
				"s1.setAttribute('crossorigin','*');"+
				"s0.parentNode.insertBefore(s1,s0);"+
				"})();"+
				"</script>");
		}
	
	/*----------------------------------------------------*/
	/*	ScrollUp
	/*----------------------------------------------------*/
	/**
	* scrollUp v1.1.0
	* Author: Mark Goodyear - http://www.markgoodyear.com
	* Git: https://github.com/markgoodyear/scrollup
	*
	* Copyright 2013 Mark Goodyear
	* Licensed under the MIT license
	* http://www.opensource.org/licenses/mit-license.php
	*/

	
$(document).ready(function(){
    $(function () {
        $.scrollUp({
            scrollName: 'scrollUp', // Element ID
            scrollDistance: 300, // Distance from top/bottom before showing element (px)
            scrollFrom: 'top', // 'top' or 'bottom'
            scrollSpeed: 300, // Speed back to top (ms)
            easingType: 'linear', // Scroll to top easing (see http://easings.net/)
            animation: 'fade', // Fade, slide, none
            animationSpeed: 200, // Animation in speed (ms)
            scrollTrigger: false, // Set a custom triggering element. Can be an HTML string or jQuery object
            //scrollTarget: false, // Set a custom target element for scrolling to the top
            scrollText: '<i class="fa fa-angle-up"></i>', // Text for element, can contain HTML
            scrollTitle: false, // Set a custom <a> title if required.
            scrollImg: false, // Set true to use image
            activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
            zIndex: 2147483647 // Z-Index for the overlay
        });
    });
});
	
	
	
	/*----------------------------------------------------*/
	/*	Current Menu Item
	/*----------------------------------------------------*/
	/*
	$(document).ready(function() {
		
		//Bootstraping variable
		headerWrapper		= parseInt($('#navigation-menu').height());
		offsetTolerance	= 300;
		
		//Detecting user's scroll
		$(window).scroll(function() {
		
			//Check scroll position
			scrollPosition	= parseInt($(this).scrollTop());
			
			//Move trough each menu and check its position with scroll position then add selected-nav class
			$('.navbar-nav > li > a').each(function() {

				thisHref				= $(this).attr('href');
				thisTruePosition	= parseInt($(thisHref).offset().top);
				thisPosition 		= thisTruePosition - headerWrapper - offsetTolerance;
				
				if(scrollPosition >= thisPosition) {
					
					$('.selected-nav').removeClass('selected-nav');
					$('.navbar-nav > li > a[href='+ thisHref +']').addClass('selected-nav');
					
				}
			});
			
			
			//If we're at the bottom of the page, move pointer to the last section
			bottomPage	= parseInt($(document).height()) - parseInt($(window).height());
			
			if(scrollPosition == bottomPage || scrollPosition >= bottomPage) {
			
				$('.selected-nav').removeClass('selected-nav');
				$('navbar-nav > li > a:last').addClass('selected-nav');
			}
		});
		
	});
	
	*/
	
	/*----------------------------------------------------*/
	/*	Animated Scroll To Anchor
	/*----------------------------------------------------*/
	/**
	 * Animated Scroll To Anchor v0.3
	 * Author: David Vogeleer
	 * http://www.individual11.com/
	 *
	 * THANKS:
	 *
	 * -> solution for setting the hash without jumping the page -> Lea Verou : http://leaverou.me/2011/05/change-url-hash-without-page-jump/
	 * -> Add stop  - Joe Mafia
	 * -> add some easing - Daniel Garcia
	 * -> added use strict, cleaned up some white space adn added conditional for anchors without hashtag -> Bret Morris, https://github.com/bretmorris
	 *
	 * TODO:
	 * -> Add hashchange support, but make it optional http://leaverou.me/2011/05/get-your-hash-the-bulletproof-way/
	 *
	 * Licensed under the MIT license.
	 * http://www.opensource.org/licenses/mit-license.php
	 * 
	 */
	 
	$(document).ready(function(){


		"use strict";
		$.fn.scrollTo = function( options ) {

			var settings = {
				offset : -60,       //an integer allowing you to offset the position by a certain number of pixels. Can be negative or positive
				speed : 'slow',   //speed at which the scroll animates
				override : null,  //if you want to override the default way this plugin works, pass in the ID of the element you want to scroll through here
				easing : null //easing equation for the animation. Supports easing plugin as well (http://gsgd.co.uk/sandbox/jquery/easing/)
			};

			if (options) {
				if(options.override){
					//if they choose to override, make sure the hash is there
					options.override = (override('#') != -1)? options.override:'#' + options.override;
				}
				$.extend( settings, options );
			}

			return this.each(function(i, el){
				$(el).click(function(e){
					var idToLookAt;
					if ($(el).attr('href').match(/#/) !== null) {
						e.preventDefault();
						idToLookAt = (settings.override)? settings.override:$(el).attr('href');//see if the user is forcing an ID they want to use
						//if the browser supports it, we push the hash into the pushState for better linking later
						if(history.pushState){
							history.pushState(null, null, idToLookAt);
							$('html,body').stop().animate({scrollTop: $(idToLookAt).offset().top + settings.offset}, settings.speed, settings.easing);
						}else{
							//if the browser doesn't support pushState, we set the hash after the animation, which may cause issues if you use offset
							$('html,body').stop().animate({scrollTop: $(idToLookAt).offset().top + settings.offset}, settings.speed, settings.easing,function(e){
								//set the hash of the window for better linking
								window.location.hash = idToLookAt;
							});
						}
					}
				});
			});
		};
		  
		$('#GoToHome, #GoToAbout, #GoToFeatures, #GoToWorks, #GoToTeam, #GoToPricing, #GoToBlog, #GoToContacts,#EnrollNow,#RegisterNow' ).scrollTo({ speed: 1400 });

	});
	
	
	
	/*----------------------------------------------------*/
	/*	Circle Progress Bars
	/*----------------------------------------------------*/
	
/*	$(window).scroll(function() {
	
		"use strict";

		if ($().easyPieChart) {
			var count = 0 ;
			var colors = ['#ffc400'];
			$('.percentage').each(function(){

					
				var imagePos = $(this).offset().top;
				var topOfWindow = $(window).scrollTop();
				if (imagePos < topOfWindow+600) {

					$(this).easyPieChart({
						barColor: colors[count],
						trackColor: '#202020',
						scaleColor: false,
						scaleLength: false,
						lineCap: 'butt',
						lineWidth: 8,
						size: 130,
						rotate: 0,
						animate: 2000,
						onStep: function(from, to, percent) {
								$(this.el).find('.percent').text(Math.round(percent));
							}
					});
				}

				count++;
				if (count >= colors.length) { count = 0};
			});
		}

	});*/

	
	
	/*----------------------------------------------------*/
	/*	Statistic Counter
	/*----------------------------------------------------*/
	
	$(document).ready(function($) {
	
		"use strict";
	
		$('.statistic-block').each(function() {
			$(this).appear(function() {
				var $endNum = parseInt($(this).find('.statistic-number').text());
				$(this).find('.statistic-number').countTo({
					from: 0,
					to: $endNum,
					speed: 3000,
					refreshInterval: 30
				});
			},{accX: 0, accY: 0});
		});

	});
	
	
	
	/*----------------------------------------------------*/
	/*	Testimonials Counter
	/*----------------------------------------------------*/
	
	$(document).ready(function($) {
	
		"use strict";
	
		$('#testimonials').each(function() {
			$(this).appear(function() {
				var $endNum = parseInt($(this).find('.clients-counter').text());
				$(this).find('.clients-counter').countTo({
					from: 0,
					to: $endNum,
					speed: 1800,
					refreshInterval: 30
				});
			},{accX: 0, accY: 0});
		});

	});
	
	
	
	/*----------------------------------------------------*/
	/*	Portfolio Lightbox
	/*----------------------------------------------------*/
	/*
	$(document).ready(function(){
	
		"use strict";
		
		$("a[class^='prettyPhoto']").prettyPhoto();

	});
	*/
	
	
	/*----------------------------------------------------*/
	/*	Filterable Portfolio
	/*----------------------------------------------------*/
/*
	$(document).ready(function(){
	
		"use strict";

		$("#portfolio .row").mixitup({
			targetSelector: '.portfolio-item',
		});

	});
	*/
	
	
	/*----------------------------------------------------*/
	/*	Our Clients Carousel
	/*----------------------------------------------------*/
	/*
	$(document).ready(function(){

		"use strict";
				
		$("#our-customers").owlCarousel({
					  
			slideSpeed : 600,
			items : 6,
			itemsDesktop : [1199,5],
			itemsDesktopSmall : [960,4],
			itemsTablet: [768,3],
			itemsMobile : [480,2],
			navigation:true,
			pagination:false,
			navigationText : false
					  
		});
				
		// Carousel Navigation
		$(".next").click(function(){
			$("#our-customers").trigger('owl.next');
		})
		
		$(".prev").click(function(){
			$("#our-customers").trigger('owl.prev');
		})
		
	});
	*/
	
	
	/*----------------------------------------------------*/
	/*	Newsletter Subscribe Form
	/*----------------------------------------------------*/	
	
	/*$(document).ready(function() {
	
		"use strict";
	
		$('#newsletter_form').submit(function() {
			if (!valid_email_address($("#s_email").val()))
				{
					$(".message").html("<span style='color:red;'>The email address you entered was invalid. Please make sure you enter a valid email address to subscribe.</span>");
				}
			else
				{
					$(".message").html("<span style='color:#19acca;'>Adding your email address...</span>");
						$.ajax({
						url: 'subscribe.php',
						data: $('#newsletter_form').serialize(),
						type: 'POST',
						success: function(msg) {
							if(msg=="success")
								{
									$("#s_email").val("");
									$(".message").html('<span style="color:#19acca;">You have successfully subscribed to our mailing list.</span>');
								}
							else
								{
									$(".message").html("<span style='color:red;'>The email address you entered was invalid. Please make sure you enter a valid email address to subscribe.</span>");
								}
						}
					});
				}
		 
				return false;			
		});
		
	});
	
	function valid_email_address(email) {
		var pattern = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
		return pattern.test(email);		
	}
	*/
	
	
	/*----------------------------------------------------*/
	/*	Register Form Validation
	/*----------------------------------------------------*/
	/*
	$(document).ready(function(){
	
		"use strict";

		$(".form_register form").validate({
			rules:{ 
				livedemo_name:{
					required: true,
					minlength: 2,
					maxlength: 30,
					},
					livedemo_email:{
						required: true,
						email: true,
					},
					livedemo_mobile:{
						required: true,
						minlength: 6,
						digits: true,
						}
					},
					messages:{
							email:{
								required: "We need your email address to contact you",
								email: "Your email address must be in the format of name@domain.com"
							}, 
							phone:{
								required: "Please enter only digits",
								digits: "Please enter a valid number"
							}, 
						}
		});		
		$("#submit").removeAttr('disabled');
	});
	*/
	