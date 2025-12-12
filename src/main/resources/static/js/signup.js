document.addEventListener("DOMContentLoaded", function () {
    const checkButton = document.getElementById("btn-email-check");
    const emailInput = document.getElementById("email");
    const messageBox = document.getElementById("email-check-message");

    // 방어: 이 페이지가 아닐 수도 있으니
    if (!checkButton || !emailInput || !messageBox) {
        return;
    }

    checkButton.addEventListener("click", async function () {
        const email = emailInput.value.trim();

        // 메시지 초기화
        messageBox.textContent = "";
        messageBox.classList.remove("hint-success", "hint-error");

        // 비어있는지 검사
        if (!email) {
            messageBox.textContent = "이메일을 입력하세요";
            messageBox.classList.add("hint-error");
            emailInput.focus();
            return;
        }

        // 이메일 형식 검사
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (!emailPattern.test(email)) {
            messageBox.textContent = "이메일 형식이 올바르지 않습니다";
            messageBox.classList.add("hint-error");
            emailInput.focus();
            return;
        }

        // 여기까지 통과하면 서버에 중복 체크 요청
        checkButton.disabled = true;
        checkButton.textContent = "확인중...";

        try {
            const response = await fetch(`/api/users/${encodeURIComponent(email)}`);

            if (response.ok) {
                // ★ 사용 가능 응답 (available = false 라는 현재 구조 유지)
                const data = await response.json();

                if (data.available === false) {
                    messageBox.textContent = "사용 가능한 이메일입니다.";
                    messageBox.classList.add("hint-success");
                } else {
                    messageBox.textContent = "이미 사용 중인 이메일입니다.";
                    messageBox.classList.add("hint-error");
                }
            } else {
                // ★ 예외 발생 → 이메일 중복 또는 기타 오류
                const error = await response.json();

                if (error.code === "EMAIL_DUPLICATED") {
                    messageBox.textContent = "이미 사용 중인 이메일입니다.";
                } else {
                    messageBox.textContent = error.message || "중복 확인 중 오류가 발생했습니다.";
                }

                messageBox.classList.add("hint-error");
            }

        } catch (e) {
            console.error(e);
            messageBox.textContent = "서버와 연결할 수 없습니다. 잠시 후 다시 시도해 주세요.";
            messageBox.classList.add("hint-error");
        } finally {
            checkButton.disabled = false;
            checkButton.textContent = "중복확인";
        }
    });
});
