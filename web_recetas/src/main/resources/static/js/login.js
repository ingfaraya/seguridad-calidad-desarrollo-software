document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("login-form");
    const logoutButton = document.getElementById("logout-button");

    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        try {
            const loginResponse = await fetch("http://localhost:8080/auth/token", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username, password }),
            });

            if (loginResponse.ok) {
                const { token } = await loginResponse.json();
                localStorage.setItem("token", token);

                const userResponse = await fetch("http://localhost:8080/api/usuarios", {
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${token}`,
                    },
                });

                if (userResponse.ok) {
                    const users = await userResponse.json();
                    const currentUser = users.find((user) => user.username === username);

                    if (currentUser) {
                        localStorage.setItem("role", currentUser.role);
                        localStorage.setItem("username", currentUser.username);
                        $('#loginModal').modal('hide');
                        window.location.reload();
                    }
                }
            } else {
                alert("Error al iniciar sesión. Verifica tus credenciales.");
            }
        } catch (error) {
            console.error("Error durante el inicio de sesión:", error);
        }
    });

    logoutButton.addEventListener("click", () => {
        localStorage.clear();
        window.location.reload();
    });
});
