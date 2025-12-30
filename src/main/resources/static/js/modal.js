function closeModal() {
    const modal = document.querySelector('.modal-overlay');
    if (modal) {
        modal.style.display = 'none';
    }
}
document.addEventListener("DOMContentLoaded", function () {
    const modal = document.getElementById("login-error-modal");
    const okBtn = document.getElementById("btn-login-error-ok");

    if (!modal || !okBtn) return;

    // ESC로 닫히지 않게 (원하면 주석 처리 가능)
    document.addEventListener("keydown", function (e) {
        if (e.key === "Escape") {
            e.preventDefault();
        }
    });

    okBtn.addEventListener("click", function () {
        modal.remove();

        // URL에서 messageKey 제거 (새로고침해도 모달 다시 안 뜸)
        const url = new URL(window.location.href);
        url.searchParams.delete("messageKey");
        history.replaceState({}, "", url.toString());
    });
});
