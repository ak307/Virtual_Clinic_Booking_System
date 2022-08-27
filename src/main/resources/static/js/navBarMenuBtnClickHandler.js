

    const mobileBtn = document.getElementById('mobile-cta-')
    nav = document.querySelector('nav')
    mobileBtnExist = document.getElementById('mobile-exit');

    mobileBtn.addEventListener('click', () => {
        nav.classList.add('menu-btn')
    })

    mobileBtnExist.addEventListener('click', () => {
        nav.classList.remove('menu-btn')
    })
