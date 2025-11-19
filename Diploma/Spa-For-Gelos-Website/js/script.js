const hamburger = document.querySelector('.hamburger');
const nav = document.querySelector('.nav');
hamburger.addEventListener('click', () => {
    hamburger.classList.toggle('active');
    nav.classList.toggle('active');
    const isExpanded = hamburger.getAttribute('aria-expanded') === 'true';
    hamburger.setAttribute('aria-expanded', !isExpanded);
});



document.querySelector('.contact-form').addEventListener('submit', function(event) {
    let form = this;
    let errorMessage = document.getElementById('error-message');
    
    
    if (!form.checkValidity()) {
        event.preventDefault(); 
        errorMessage.style.display = 'block'; 
        form.reportValidity(); 
    } else {
        errorMessage.style.display = 'none'; 
    }
});





// JavaScript for the Carousel
const slides = document.querySelectorAll('.slide');
const indicators = document.querySelectorAll('.indicator');
const nextBtn = document.querySelector('.next');
const prevBtn = document.querySelector('.prev');
let currentSlide = 0;

function updateIndicators() {
    indicators.forEach((indicator, index) => {
        if (index === currentSlide) {
            indicator.style.backgroundColor = 'rgb(7, 122, 153)';
        } else {
            indicator.style.backgroundColor = 'transparent'; // reset others
        }
    });
}

function showSlide(index) {
    const totalSlides = slides.length;
    if (index >= totalSlides) currentSlide = 0;
    else if (index < 0) currentSlide = totalSlides - 1;
    else currentSlide = index;

    // Update slide position
    document.querySelector('.slides').style.transform = `translateX(-${currentSlide * 100}%)`;

    // Update indicators
    updateIndicators();
}

// Add event listeners for the arrows
nextBtn.addEventListener('click', () => showSlide(currentSlide + 1));
prevBtn.addEventListener('click', () => showSlide(currentSlide - 1));

// Initialize
updateIndicators();  // Set initial indicator style
