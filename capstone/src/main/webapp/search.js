function autocomplete(data) {
    var inputElt = document.getElementById("search"); 
    inputElt.addEventListener("input", function(e) {
        var val = this.value;
        if(!val) {
            return false;
        }
    });
}