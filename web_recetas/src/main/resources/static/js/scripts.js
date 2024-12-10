document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("login-form");
    const logoutButton = document.getElementById("logout-button");
    const recetasContainer = document.getElementById("recetas-container");
    const prevPageButton = document.getElementById("prev-page");
    const nextPageButton = document.getElementById("next-page");
    const createRecipeButton = document.getElementById("create-recipe-button"); // Botón para crear receta
    const createRecipeForm = document.getElementById("create-recipe-form");
    const modalCreateRecipe = new bootstrap.Modal(document.getElementById("createRecipeModal"));
    let currentPage = 0;
    const pageSize = 6;

    const renderRecetas = async () => {
        const token = localStorage.getItem("token");
        if (!token) {
            $('#loginModal').modal('show');
            return;
        }

        const role = localStorage.getItem("role");
        const username = localStorage.getItem("username");

        // Mostrar el botón "Crear receta" solo si el usuario es ADMIN
        if (role === "ADMIN") {
            createRecipeButton.style.display = "block";
        } else {
            createRecipeButton.style.display = "none";
        }

        try {
            const response = await fetch(`http://localhost:8081/api/recetas`, {
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                }
            });

            if (response.ok) {
                const recetas = await response.json();
                const start = currentPage * pageSize;
                const end = Math.min(start + pageSize, recetas.length);
                recetasContainer.innerHTML = "";

                recetas.slice(start, end).forEach(receta => {
                    recetasContainer.innerHTML += `
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${receta.nombre}</h5>
                                    <p class="card-text">Tipo de cocina: ${receta.tipoCocina}</p>
                                    <p class="card-text">Tiempo de cocción: ${receta.tiempoCoccion}</p>
                                    ${role === "ADMIN" ? `
                                        <p class="card-text">Ingredientes: ${receta.ingredientes}</p>
                                        <p class="card-text">Instrucciones: ${receta.instrucciones}</p>
                                        <button class="btn btn-primary mb-2" onclick="editReceta(${receta.id})">Editar</button>
                                        <button class="btn btn-danger" onclick="deleteReceta(${receta.id})">Eliminar</button>
                                    ` : role === "USER" && receta.usuario === username ? `
                                        <button class="btn btn-primary mb-2" onclick="editReceta(${receta.id})">Editar</button>
                                    ` : ""}
                                </div>
                            </div>
                        </div>
                    `;
                });

                prevPageButton.disabled = currentPage === 0;
                nextPageButton.disabled = end >= recetas.length;
            } else {
                alert("Error al cargar recetas.");
            }
        } catch (error) {
            console.error("Error al cargar recetas:", error);
        }
    };

    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        try {
            const loginResponse = await fetch("http://localhost:8080/auth/token", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ username, password })
            });

            if (loginResponse.ok) {
                const { token } = await loginResponse.json();
                localStorage.setItem("token", token);

                const userResponse = await fetch("http://localhost:8080/api/usuarios", {
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${token}`
                    }
                });

                if (userResponse.ok) {
                    const users = await userResponse.json();
                    const currentUser = users.find(user => user.username === username);

                    if (currentUser) {
                        localStorage.setItem("role", currentUser.role);
                        localStorage.setItem("username", currentUser.username);
                        $('#loginModal').modal('hide');
                        renderRecetas();
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
        $('#loginModal').modal('show');
    });

    prevPageButton.addEventListener("click", () => {
        if (currentPage > 0) {
            currentPage--;
            renderRecetas();
        }
    });

    nextPageButton.addEventListener("click", () => {
        currentPage++;
        renderRecetas();
    });

    createRecipeForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const token = localStorage.getItem("token");
        const username = localStorage.getItem("username");

        const recipeData = {
            nombre: document.getElementById("nombre").value,
            ingredientes: document.getElementById("ingredientes").value,
            instrucciones: document.getElementById("instrucciones").value,
            tipoCocina: document.getElementById("tipoCocina").value,
            paisOrigen: document.getElementById("paisOrigen").value,
            tiempoCoccion: document.getElementById("tiempoCoccion").value,
            dificultad: document.getElementById("dificultad").value,
            usuario: username
        };

        try {
            const response = await fetch("http://localhost:8082/api/recetas", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(recipeData)
            });

            if (response.ok) {
                alert("Receta creada con éxito.");
                modalCreateRecipe.hide();
                renderRecetas();
            } else {
                alert("Error al crear receta.");
            }
        } catch (error) {
            console.error("Error al crear receta:", error);
        }
    });

    renderRecetas();
});

const editReceta = (id) => {
    alert(`Editar receta con ID: ${id}`);
    // Implementar lógica de edición
};

const deleteReceta = async (id) => {
    const token = localStorage.getItem("token");

    try {
        const response = await fetch(`http://localhost:8081/api/recetas/${id}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }
        });

        if (response.ok) {
            alert("Receta eliminada con éxito.");
            renderRecetas();
        } else {
            alert("Error al eliminar la receta.");
        }
    } catch (error) {
        console.error("Error al eliminar la receta:", error);
    }
};
