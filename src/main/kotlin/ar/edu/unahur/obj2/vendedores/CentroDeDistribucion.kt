package ar.edu.unahur.obj2.vendedores

class CentroDeDistribucion(val ciudad: Ciudad) {
    val vendedores = mutableListOf<Vendedor>()

    fun agregarVendedor(vendedor : Vendedor) {
        if(!vendedores.contains(vendedor)) {
            vendedores.add(vendedor)
        }
        else {
            Error()
        }
    }

    fun vendedorEstrella() = vendedores.maxBy{ v -> v.puntajeCertificaciones() }

    fun puedeCubrir(ciudad : Ciudad) = vendedores.any{ v -> v.puedeTrabajarEn(ciudad) }

    fun vendedoresGenéricos() = vendedores.filter { v -> v.esGenerico() }

    fun esRobusto() = vendedores.filter { v -> v.esFirme() }.size >= 3
}
