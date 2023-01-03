 //header sticky
  const headerWrap = document.querySelector("#headerWrap");
  const pageSelector = document.querySelector(".mainslider");

  window.addEventListener("scroll", function () {
    const maxHeight = document.body.scrollHeight - window.innerHeight;
    let currentHeight = (window.pageYOffset * 100) / maxHeight;
    if (currentHeight > 3) {
      headerWrap.classList.add("enabled");
    } else {
      headerWrap.classList.remove("enabled");
    }
  });

 
  