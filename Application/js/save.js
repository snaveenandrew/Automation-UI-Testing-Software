function save(){
  alert("Hello! I am an alert box!!");
  html2canvas(document.querySelector('cropbox1'), {
  onrendered: function(canvas) {
  // document.body.appendChild(canvas);
  return Canvas2Image.saveAsPNG(canvas);
}
}}
