
 enum class Rol {
    ADMIN, EDITOR, VISITANTE
}

interface Autenticable {
    fun iniciarSesion(): Boolean
}

data class Usuario(
    val id: Int,
    val nombre: String,
    val email: String,
    val rol: Rol,
) : Autenticable {
    override fun iniciarSesion(): Boolean {
        println("Usuario $nombre con el Rol $rol ha iniciado sesión correctamente.")
        return true
    }
}

object GestorUsuarios {
    private val usuarios = mutableListOf<Usuario>()

    fun registrarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
        println("Usuario ${usuario.nombre} registrado con éxito.")
    }

    fun verificarPermisos(usuario: Usuario) {
        when (usuario.rol) {
            Rol.ADMIN -> {
                println("[ADMIN] ${usuario.nombre} tiene acceso total al sistema (Crear, Editar, Eliminar).")
            }
            Rol.EDITOR -> {
                println("[EDITOR] ${usuario.nombre} puede crear y modificar contenido.")
            }
            Rol.VISITANTE -> {
                println("[VISITANTE] ${usuario.nombre} solo tiene permisos de lectura.")
            }
        }
    }
    
   fun listarUsuarios() {
        println("\n--- Lista de Usuarios en el Sistema ---")
        for (u in usuarios) {
            println("ID: ${u.id} | Nombre: ${u.nombre} | Rol: ${u.rol}")
        }
    }
}


fun main() {
    val admin = Usuario(1, "Carlos Pérez", "carlos@admin.com", Rol.ADMIN)
    val editor = Usuario(2, "Ana Gómez", "ana@editor.com", Rol.EDITOR)
    val visitante = Usuario(3, "Luis Soto", "luis@visitante.com", Rol.VISITANTE)

    admin.iniciarSesion()
    editor.iniciarSesion()
    

    GestorUsuarios.registrarUsuario(admin)
    GestorUsuarios.registrarUsuario(editor)
    GestorUsuarios.registrarUsuario(visitante)

    GestorUsuarios.listarUsuarios()

    println("\n--- Verificando Permisos ---")
    GestorUsuarios.verificarPermisos(admin)
    GestorUsuarios.verificarPermisos(editor)
    GestorUsuarios.verificarPermisos(visitante)
}

