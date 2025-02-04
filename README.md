# 🏗️ Estructura del Proyecto

Este proyecto implementa un servidor que maneja coches y usuarios, permitiendo operaciones como agregar, eliminar, actualizar y listar coches, además de gestionar autenticación de usuarios.

---

## 📂 client
- **Coches.java** → Cliente que interactúa con el servidor para enviar y recibir información sobre coches.

---

## 📂 server

### 📁 application
- **Routing.java** → Define las rutas del servidor, dirigiendo las solicitudes entrantes a los casos de uso correspondientes.

### 📁 data/repository
- **Repository.java** → Implementación del repositorio que maneja la persistencia de datos en memoria o en una base de datos.

### 📁 domain

#### 📂 interfaces
- **GenericRepositoryInterface.java** → Interfaz genérica que define métodos básicos para los repositorios (CRUD).
- **RestInterface.java** → Interfaz que deben implementar los casos de uso para ser ejecutados desde el servidor.

#### 📂 model
- **Coche.java** → Modelo de datos para representar un coche con atributos como matrícula, modelo y marca.
- **Usuario.java** → Modelo de datos para representar un usuario con atributos como nombre, email y contraseña.

#### 📂 usecase (Casos de Uso)
Cada archivo en esta carpeta representa una acción específica que el servidor puede ejecutar en respuesta a una solicitud del cliente.

- **CocheByMatriculaUseCase.java** → Busca un coche específico por su matrícula.
- **CocheByModelUseCase.java** → Busca coches por modelo.
- **CocheDelUseCase.java** → Elimina un coche de la base de datos si el usuario está autenticado.
- **CocheListUseCase.java** → Recupera y muestra la lista de todos los coches almacenados.
- **CochePostUseCase.java** → Agrega un nuevo coche si el usuario está autenticado.
- **CochePutUseCase.java** → Permite actualizar la información de un coche existente.
- **LogInUseCase.java** → Maneja el inicio de sesión de usuarios mediante email y contraseña.
- **LogOutUseCase.java** → Cierra la sesión del usuario y lo desloguea del sistema.
- **RegisterUseCase.java** → Permite a nuevos usuarios registrarse en el sistema.

---

### 📁 infrastructure/server
- **DataThread.java** → Maneja la comunicación con cada cliente en un hilo separado, permitiendo conexiones simultáneas y asegurando que solo los usuarios autenticados puedan ejecutar ciertas acciones.

---

### 📁 main
- **Server.java** → Programa principal que inicializa el servidor, escucha las conexiones entrantes y asigna un hilo (`DataThread`) para manejar cada cliente.

---

## 🚀 Funcionamiento General

1. **El servidor se inicia** con `Server.java`, abriendo un socket en un puerto específico.
2. **Los clientes se conectan** y envían solicitudes (por ejemplo, agregar un coche, eliminar un usuario, listar coches).
3. **Cada solicitud es gestionada** por `Routing.java`, que la envía al caso de uso correspondiente en `usecase/`.
4. **Los casos de uso acceden al repositorio** (`Repository.java`) para manejar la información solicitada.
5. **La respuesta se envía** de vuelta al cliente a través de `DataThread.java`.

---

💡 **Este diseño sigue el patrón de arquitectura hexagonal, separando claramente la lógica de negocio, los modelos, la infraestructura y la aplicación principal.**
