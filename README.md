# Veterinaria D'Pelos

## Descripción del Proyecto

La clínica D' pelos se especializa en el cuidado de perros que requieren hospitalización. La aplicación web permitirá a los usuarios:

- Promocionar los servicios de la clínica.
- Consultar el estado de las mascotas hospitalizadas.
- Registrar y gestionar las atenciones médicas realizadas por el personal veterinario.
- Proporcionar al administrador una visión analítica del negocio.

## Características

- **Registro de Mascotas y Clientes**: Los datos de las mascotas y sus propietarios se registran al ingresar a la clínica.
- **Gestión de Atenciones**: Los veterinarios pueden registrar y gestionar los tratamientos y atenciones que realizan.
- **Portal de Administración**: Los administradores pueden analizar datos del negocio, gestionar médicos veterinarios y más.
- **Historial de Tratamientos**: Se guarda un historial de los tratamientos administrados a cada mascota.

## Requerimientos

### Funcionalidades Principales

1. **Registro de Mascotas y Clientes**:
   - Registro de datos del cliente (cédula, nombre, correo, celular).
   - Registro de datos de la mascota (nombre, raza, edad, peso, enfermedad, foto).

2. **Gestión de Atenciones**:
   - Registro de datos de los veterinarios (cédula, contraseña, especialidad, foto URL, número de atenciones, nombre).
   - Registro de tratamientos (nombre de droga, precio de compra, precio de venta, unidades disponibles y vendidas).
   - Asociación de veterinarios y mascotas a través de tratamientos.

3. **Portal de Administrador**:
   - Análisis de datos: cantidad de mascotas en tratamiento, cantidad de atenciones, precio total de los tratamientos.
   - Gestión de veterinarios: agregar, eliminar o modificar información.

### Requerimientos Adicionales

- **Estado de las Mascotas**: Las mascotas curadas deben cambiar a un estado "inactivo" en el sistema.
- **Relaciones**: Un veterinario puede tratar a varias mascotas y una mascota puede ser tratada por diferentes veterinarios.
- **Historial**: Guardar el historial de tratamientos realizados, pero no el historial de variación en el estado de las mascotas.

## Diagramas y Diseño

- **Diagrama de Clases**: Describe la estructura del sistema, incluyendo clases, atributos, y relaciones.
- **Diagrama Entidad-Relación**: Muestra la estructura de la base de datos, incluyendo claves primarias (PK), claves foráneas (FK), campos que pueden ser nulos, cardinalidad y campos únicos.
- **Mockups**: Diseños de la interfaz de usuario, con navegación interactiva o descripciones de cómo se navegará entre las pantallas.

## Desarrollo

- **Diagrama de Clases y Entidad-Relación**: Se deben generar diagramas que reflejen la estructura y relaciones del sistema.
- **Mockups y Navegación**: Crear mockups interactivos que muestren el diseño y la navegación del sitio web.
- **Logo y Paleta de Colores**: Diseñar un logo para la clínica y definir una paleta de colores para la aplicación.
