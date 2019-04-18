/**
 * 
 */

function navBarHyperlinkClick(variable) {
    var pageNumber = variable;
    document.getElementById("webPage").value = pageNumber;
    document.getElementById("redirectForm").submit();
}

