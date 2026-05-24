document.addEventListener("DOMContentLoaded", () => {
    const trigger = document.getElementById("userMenuTrigger");
    const dropdown = document.getElementById("userMenuDropdown");

    // 로그인 페이지가 아니면 요소가 없을 수 있음
    if (!trigger || !dropdown) return;

    const open = () => {
        dropdown.classList.add("is-open");
        trigger.setAttribute("aria-expanded", "true");
    };

    const close = () => {
        dropdown.classList.remove("is-open");
        trigger.setAttribute("aria-expanded", "false");
    };

    const toggle = () => {
        if (dropdown.classList.contains("is-open")) close();
        else open();
    };

    trigger.addEventListener("click", (e) => {
        e.stopPropagation();
        toggle();
    });

    // 드롭다운 내부 클릭은 닫히지 않게
    dropdown.addEventListener("click", (e) => {
        e.stopPropagation();
    });

    // 바깥 클릭 시 닫기
    document.addEventListener("click", () => {
        close();
    });

    // ESC로 닫기
    document.addEventListener("keydown", (e) => {
        if (e.key === "Escape") close();
    });
});
