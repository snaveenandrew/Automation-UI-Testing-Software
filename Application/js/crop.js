(function(){
  $('#cropbox1').Jcrop({
		bgColor:     'black',
    bgOpacity:   .1,
	});
})();
function showCoords(c)
{
	$('#x').val(c.x);
	$('#y').val(c.y);
	$('#x2').val(c.x2);
	$('#y2').val(c.y2);
	$('#w').val(c.w);
	$('#h').val(c.h);
};
