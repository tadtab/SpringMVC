/**
 * 08/27/18 started this for modal functionality
 */

function closeModal(){
	var modalWindow =  document.getElementById("modal");
	modalWindow.style.display = "none";
}

  function modalManipulator(imgs){

	  document.getElementById("modal").style.display ="block";
	    var expandImg = document.getElementById("expandedImg");
	    var imgText = document.getElementById("imgtext");
	    expandImg.src = imgs.src;
	    imgText.innerHTML = imgs.alt;
	    expandImg.parentElement.style.display = "block";
	}
  
  	window.onclick = function(event){
	  var modalWindow =  document.getElementById("modal");
	  if(event.target == modalWindow){
		  modalWindow.style.display = "none";
	  }
  }
  
 