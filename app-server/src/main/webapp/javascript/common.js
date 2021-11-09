(function(){
    "use strict"

    const qs = x => document.querySelector(x);

    // Selectors
    const $sideMenu = qs(".side-menu");
    const $sideMenuBar = qs(".side-menu-bar");
    const $sideMenuCloseBtn = qs(".close-button");
  
    //EventListener
    $sideMenu.addEventListener('click', ()=>$sideMenuBar.style.display = 'flex' ); 
    $sideMenuCloseBtn.addEventListener('click', ()=>$sideMenuBar.style.display = 'none');

})();