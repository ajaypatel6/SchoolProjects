<script>

 var adidas= 0;
 var nike= 0;
 var vans= 0;

 function addAdidas(){
	if (localStorage.getItem("Adidas") == null){
		localStorage.setItem("Adidas",0);
	} 
	adidas = parseInt(localStorage.getItem('Adidas'));
	adidas = adidas+1;
	localStorage.setItem("Adidas",adidas);
	alert("1 Added to wishlist");
	alert(adidas + " Adidas shoes in wishlist");
 }
 
 function addNike(){
	if (localStorage.getItem("Nike") == null){
		localStorage.setItem("Nike",0);
	} 
	nike = parseInt(localStorage.getItem('Nike'));
	nike = nike+1;
	localStorage.setItem("Nike",nike);
	alert("1 Nike added to wishlist");
	alert(nike + " Nike shoes in wishlist");
 }
 
 function addVans(){
	if (localStorage.getItem("Vans") == null){
		localStorage.setItem("Vans",0);
	} 
	vans = parseInt(localStorage.getItem('Vans'));
	vans = vans+1;
	localStorage.setItem("Vans",vans);
	alert("1 vans added to wishlist");

	alert(vans + " Vans shoes in wishlist");
 }
 
 function removeAdidas(){
 	adidas = parseInt(localStorage.getItem('Adidas'));
 	adidas = adidas - 1;
	localStorage.setItem("Adidas",adidas);
	alert("1 Adidas removed from wishlist");
	alert(adidas + " Adidas shoes in wishlist");
 }
 
 function removeNike(){
 	nike = parseInt(localStorage.getItem('Nike'));
 	nike = nike - 1;
	localStorage.setItem("Nike",nike);
	alert("1 Nike removed from wishlist");
	alert(nike + " Nike shoes in wishlist");;
 }
 
 function removeVans(){
 	vans = parseInt(localStorage.getItem('Vans'));
 	vans = vans - 1;
	localStorage.setItem("Vans",vans);
	alert("1 Vans removed from wishlist");
	alert(vans + " Vans shoes in wishlist");
 }

</script>