document.addEventListener("DOMContentLoaded", () => {
    const menu = document.getElementById("menu");
    const toggleMenu = () => menu.classList.toggle("active");
    const closeMenu = () => menu.classList.remove("active");

    document.getElementById("hamburger").onclick = toggleMenu;
    document.getElementById("menu-overlay").onclick = closeMenu;
    document.getElementById("close").onclick = closeMenu;

    document.getElementById("products-link").onclick = (e) => {
        if (window.innerWidth <= 768) {
            e.preventDefault();
            const submenu = e.currentTarget.querySelector(".submenu");
            submenu.style.display = submenu.style.display === "block" ? "none" : "block";
            if (submenu.style.display === "none") window.location.href = "products.html";
        }
    };

    document.querySelectorAll("#products-link .submenu a").forEach(link => 
        link.onclick = function() {
            if (window.innerWidth <= 768) {
                closeMenu();
                window.location.href = this.href;
            }
        }
    );

    document.querySelector("#products-link > a").onclick = (e) => {
        if (window.innerWidth > 768) window.location.href = "products.html";
    };
});
