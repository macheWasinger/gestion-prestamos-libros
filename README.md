# 📚 Gestión de Préstamos de Libros

Sistema de consola para la gestión de préstamos de libros en una biblioteca.  

Permite registrar libros, controlar su disponibilidad, prestar, devolver, marcar como perdidos, eliminar y obtener estadísticas generales.  

Cuenta con un menú interactivo y validaciones para asegurar el correcto funcionamiento del sistema.

---

## 🚀 Funcionalidades implementadas

✔️ Agregar un libro con:  

- Título  
- Autor  
- Género

✔️ **Agregar libro** con título, autor y género.
✔️ **Listar libros** disponibles en la biblioteca (excluyendo los perdidos).
✔️ **Buscar libro por título**.
✔️ **Prestar libro** (solo si está disponible y no perdido).
✔️ **Devolver libro** (solo si estaba prestado).
✔️ **Listar libros prestados**.
✔️ **Devolver libros por autor**.
✔️ **Filtrar libros disponibles por género**.
✔️ **Mostrar estadísticas generales**:  
   - Total de libros cargados.  
   - Total de libros disponibles.  
   - Total de libros prestados.  
   - Total de libros perdidos.  
   - Géneros presentes en la biblioteca.
✔️ **Eliminar un libro por título** (con confirmación).
✔️ **Marcar libros como perdidos** (solo si estaban prestados y con confirmación).
✔️ **Listar libros perdidos**.
✔️ **Recuperar libros perdidos** (con confirmación, se marcan como disponibles y se eliminan de la lista de perdidos).

---

## 🛠️ Tecnologías usadas

- **Lenguaje:** Java 17  

- **IDE:** NetBeans  

- **Paradigma:** Programación Orientada a Objetos  

- **Versión de JDK:** 17  

- **Dependencias externas:** Ninguna

---

## 📁 Estructura del proyecto

El sistema está organizado en las siguientes clases:

- `Principal.java` → Menú principal, interacción con el usuario  
- `Libro.java` → Clase modelo que representa un libro  
- `Biblioteca.java` → Lógica de negocio: listado, préstamos, devoluciones, etc.  
- `MetodosAuxiliares.java` → Utilidades: menús, validaciones, ordenamientos, mensajes

---

## ▶️ Cómo ejecutar el proyecto

1. Cloná el repositorio:

```bash
git clone https://github.com/tu-usuario/gestion-prestamos-libros.git
```

2. Abrilo con NetBeans o tu IDE de preferencia

3. Ejecutá la clase Principal.java

---

## 👤 Autor

**Marcelo Wasinger**

---

Gracias por visitar este repositorio.  

¡Cualquier sugerencia o feedback es más que bienvenido!