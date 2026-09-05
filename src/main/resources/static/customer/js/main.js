/* ==================================================
   Mobile Menu
================================================== */

const menuBtn = document.querySelector(".menu-btn");
const menuIcon = menuBtn.querySelector("img");
const gnb = document.querySelector(".gnb");
const gnbLinks = document.querySelectorAll(".gnb a");

// 메뉴 열기 / 닫기
menuBtn.addEventListener("click", () => {

    const isActive = gnb.classList.toggle("active");

    menuBtn.classList.toggle("active", isActive);

    menuIcon.src = isActive
        ? "./images/icon_close.png"
        : "./images/icon_menu.png";

});

// 메뉴 클릭 시 닫기
gnbLinks.forEach(link => {

    link.addEventListener("click", () => {

        gnb.classList.remove("active");
        menuBtn.classList.remove("active");
        menuIcon.src = "./images/icon_menu.png";

    });

});

// PC 화면으로 변경 시 초기화
window.addEventListener("resize", () => {

    if (window.innerWidth > 1024) {

        gnb.classList.remove("active");
        menuBtn.classList.remove("active");
        menuIcon.src = "./images/icon_menu.png";

    }

});


/* ==================================================
   Header & Active Menu
================================================== */

const header = document.querySelector(".header");
const sections = document.querySelectorAll("main section");

window.addEventListener("scroll", () => {

    /* ---------- Header ---------- */

    if (window.scrollY > 80) {
        header.classList.add("scrolled");
    } else {
        header.classList.remove("scrolled");
    }


    /* ---------- Active Menu ---------- */

    const headerHeight = header.offsetHeight;

    let current = "";

    sections.forEach(section => {

        const sectionTop = section.offsetTop - headerHeight;
        const sectionHeight = section.offsetHeight;

        if (
            window.scrollY >= sectionTop &&
            window.scrollY < sectionTop + sectionHeight
        ) {
            current = section.id;
        }

    });

    gnbLinks.forEach(link => {

        const target = link.getAttribute("href").replace("#", "");

        link.classList.toggle("active", target === current);

    });

});


/* ==================================================
    Review Swiper
================================================== */

// 리뷰 슬라이드
const reviewSwiper = new Swiper(".review-slider",{

    effect:"coverflow",

    centeredSlides:true,
    slidesPerView:"auto",

    loop:true,

    speed:700,

    autoplay:{
        delay:2500,
        disableOnInteraction:false
    },

    coverflowEffect:{
        rotate:0,
        stretch:20,
        depth:50,
        modifier:1.3,
        scale:.98,
        slideShadows:false
    }

});


/* ==================================================
   AOS
================================================== */

// 스크롤 애니메이션
AOS.init({

    duration: 800,     // 애니메이션 시간(ms)

    once: true,        // 한 번만 실행

    offset: 100        // 화면에서 100px 들어오면 실행

});

/* ==================================================
   Popup
================================================== */

MicroModal.init({
    disableScroll: true
});


function setCookie(name, value, days){

    const date = new Date();

    date.setDate(date.getDate() + days);

    document.cookie =
        `${name}=${value}; expires=${date.toUTCString()}; path=/`;

}

function getCookie(name){

    const cookies = document.cookie.split(";");

    for(let cookie of cookies){

        cookie = cookie.trim();

        if(cookie.startsWith(name + "=")){

            return cookie.substring(name.length + 1);

        }

    }

    return null;
}


const popups = [
    {
        type: "html",
        id: "salesPopup"
    },

    ...serverPopups.map(popup => ({
        type: "image",
        src: "/file/api/getImageSource.do/" + popup.puAfId
    }))
];

let current = 0;
let autoSlide;

const popupImage = document.getElementById("popupImage");
const popupHtml=document.getElementById("popupHtml");

const prevBtn = document.querySelector(".popup-prev");
const nextBtn = document.querySelector(".popup-next");
const closeBtn = document.querySelector(".popup-close-btn");
const todayClose = document.getElementById("todayClose");


function showPopup(index){

    current=index;

    const popup=popups[current];

    popupImage.classList.remove("active");
    popupHtml.classList.remove("active");

    popupImage.removeAttribute("src");

    document.querySelectorAll(".popup-page").forEach(page=>{
        page.classList.remove("active");
    });

    if(popup.type==="image"){

        popupImage.src=popup.src;
        popupImage.classList.add("active");

    }else{

        popupHtml.classList.add("active");

        const page=document.getElementById(popup.id);

        if(page){
            page.classList.add("active");

        // 매출 팝업인 경우 숫자 애니메이션 실행
        if(popup.id === "salesPopup"){
            startSalesCounter();
        }


        }

    }

}



function nextPopup(){

    current++;

    if(current >= popups.length){
        current = 0;
    }

    showPopup(current);

    restartAutoSlide();

}

function prevPopup(){

    current--;

    if(current < 0){
        current = popups.length-1;
    }

    showPopup(current);

    restartAutoSlide();

}

function startAutoSlide(){

    autoSlide = setInterval(nextPopup,3000);

}

function stopAutoSlide(){

    clearInterval(autoSlide);

}

function restartAutoSlide(){

    stopAutoSlide();

    // startAutoSlide();

}

prevBtn.addEventListener("click",prevPopup);

nextBtn.addEventListener("click",nextPopup);

closeBtn.addEventListener("click",()=>{

    if(todayClose.checked){

        setCookie("popupClose","Y",1);

    }

    stopAutoSlide();

    MicroModal.close("popup-modal");

});

window.addEventListener("load",()=>{

    if(getCookie("popupClose")) return;

    showPopup(0);

      // startAutoSlide();   // 임시 비활성화

    MicroModal.show("popup-modal");

});



/* ==================================================
   Sales Counter Animation
================================================== */

// 숫자 천 단위 콤마
function numberWithComma(number){

    return number.toLocaleString();

}

/* 숫자 카운트 애니메이션 */
function salesCountUp(element){

    const target = Number(element.dataset.price);

    let current = 0;

    function update(){

        const diff = target - current;

        // 목표값의 12%씩 접근
        current += diff * 0.10;

        // 마지막에는 정확한 값으로 고정
        if(diff < 100){

            current = target;

        }

        element.textContent =
            numberWithComma(Math.floor(current)) + "원";

        if(current < target){

            requestAnimationFrame(update);

        }else{

            element.textContent =
                numberWithComma(target) + "원";

        }

    }

    requestAnimationFrame(update);

}

// 모든 매출 숫자 애니메이션 시작
function startSalesCounter(){

    document.querySelectorAll(".sales-price").forEach(item=>{

        item.textContent = "0원";

        salesCountUp(item);

    });

}



/* ==================================================
   Smooth Scroll
================================================== */

gnbLinks.forEach(link => {

    link.addEventListener("click", (e) => {

        e.preventDefault();

        const target = document.querySelector(link.getAttribute("href"));

        if (!target) return;

        const headerHeight = header.offsetHeight;

        // 모바일 메뉴 닫기
        gnb.classList.remove("active");
        menuBtn.classList.remove("active");
        menuIcon.src = "./images/icon_menu.png";

        window.scrollTo({
            top: target.offsetTop - headerHeight,
            behavior: "smooth"
        });

    });

});
/* ==================================================
   Split Text
================================================== */

document.querySelectorAll(".split").forEach(element => {

    const text = element.textContent.trim();

    element.innerHTML = "";

    [...text].forEach((char,index)=>{

        const span = document.createElement("span");

        span.textContent = char === " " ? "\u00A0" : char;

        span.style.setProperty("--delay",`${index*0.05}s`);

        span.style.animationDelay = `${index*0.05}s`;

        element.appendChild(span);

    });

});

/* ==================================================
   Menu Tab
================================================== */

const menuTabs=document.querySelectorAll(".menu-tab-btn");
const menuGroups=document.querySelectorAll(".menu-group");

menuTabs.forEach(tab=>{

    tab.addEventListener("click",()=>{

        menuTabs.forEach(btn=>btn.classList.remove("active"));
        tab.classList.add("active");

        const category=tab.dataset.category;

        menuGroups.forEach(group=>{

            group.classList.toggle(
                "active",
                group.dataset.category===category
            );

        });

    });

});

/* ==================================================
   Menu Swiper
================================================== */

document.querySelectorAll(".menu-swiper").forEach(swiper=>{

    new Swiper(swiper,{

        loop:true,

        slidesPerView:3,
        slidesPerGroup:3,

        spaceBetween:40,

        speed:600,

        watchOverflow:true,

        navigation:{
            nextEl:swiper.querySelector(".swiper-button-next"),
            prevEl:swiper.querySelector(".swiper-button-prev")
        },

        breakpoints:{

            0:{
                slidesPerView:1,
                slidesPerGroup:1
            },

            768:{
                slidesPerView:2,
                slidesPerGroup:2
            },

            1024:{
                slidesPerView:3,
                slidesPerGroup:3
            }

        }

    });

});

