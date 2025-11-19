// Function to update the clock display
function updateClock() {
    // Get the clock element from the DOM
    const clockElement = document.getElementById("clock");
    // Get the current date and time
    const now = new Date();
    // Extract hours, minutes, and seconds, ensuring two-digit format
    const hours = now.getHours().toString().padStart(2, "0");
    const minutes = now.getMinutes().toString().padStart(2, "0");
    const seconds = now.getSeconds().toString().padStart(2, "0");
    // Update the text content of the clock element
    clockElement.textContent = `${hours}:${minutes}:${seconds}`;
}

// Update the clock every second using setInterval
setInterval(updateClock, 1000);
// Initialize the clock immediately when the script is loaded
updateClock();

document.addEventListener("DOMContentLoaded", () => {
    // Initialize variables for tracking recycling progress
    let recycledCount = 0; // Number of recycled items
    const totalItems = 100; // Total items to recycle

    // Get the relevant DOM elements
    const recycledCountElement = document.getElementById("recycledCount");
    const progressFillElement = document.getElementById("progressFill");
    const addRecycleButton = document.getElementById("addRecycleButton");

    // Add a click event listener to the "Add Recycled Item" button
    addRecycleButton.addEventListener("click", () => {
        // Only increment if recycled count is less than total items
        if (recycledCount < totalItems) {
            recycledCount++; // Increment the count
            // Update the displayed recycled count
            recycledCountElement.textContent = recycledCount;
            // Calculate the progress percentage
            const progressPercentage = (recycledCount / totalItems) * 100;
            // Update the width of the progress bar to reflect progress
            progressFillElement.style.width = `${progressPercentage}%`;
        }
    });
});

document.addEventListener("DOMContentLoaded", () => {
    // Get the hamburger menu and mobile navigation elements
    const hamburger = document.querySelector('.hamburger');
    const mobileNav = document.querySelector('.mobile-nav');

    // Get the navigation links from the desktop menu
    const navLinks = document.querySelector('.nav-links');
    // Populate the mobile menu with the desktop navigation links
    mobileNav.innerHTML = navLinks.innerHTML;

    // Add a click event listener to the hamburger menu
    hamburger.addEventListener('click', () => {
        // Toggle the 'active' class on the mobile navigation to show/hide it
        mobileNav.classList.toggle('active');
    });

    // Add a resize event listener to the window
    window.addEventListener('resize', () => {
        // Automatically hide the mobile menu when the screen width is 768px or larger
        if (window.innerWidth >= 768) {
            mobileNav.classList.remove('active');
        }
    });
});
