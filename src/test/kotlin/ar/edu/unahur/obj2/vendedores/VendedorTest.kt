package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
  }

  describe("Viajante") {
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(listOf(misiones))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }
  }
})

class VendedoresInfluyente : DescribeSpec({
  val santaFe = Provincia(3194537)
  val buenosAires = Provincia(15625083)
  val entreRios = Provincia(1235994)
  val cordoba = Provincia(3308876)
  val chivilcoy = Ciudad(buenosAires)
  val diamante = Ciudad(entreRios)
  val bragado = Ciudad(buenosAires)
  val lobos = Ciudad(buenosAires)
  val pergamino = Ciudad(buenosAires)
  val zarate = Ciudad(buenosAires)
  val rosario = Ciudad(santaFe)
  val rafaela = Ciudad(santaFe)
  val sanFrancisco = Ciudad(cordoba)
  val amstrong = Ciudad(santaFe)



  describe("Vendedor Fijo:") {
    val vendedorFijo = VendedorFijo(chivilcoy)
    val vendedorFijo2 = VendedorFijo(diamante)

    vendedorFijo.esInfluyente().shouldBeFalse()
    vendedorFijo2.esInfluyente().shouldBeFalse()
  }

  describe("Vendedor viajante:") {
    val viajante = Viajante(listOf(santaFe, entreRios))
    val viajante1 = Viajante(listOf(buenosAires, santaFe))

    viajante.esInfluyente().shouldBeFalse()
    viajante1.esInfluyente().shouldBeTrue()
  }

  describe("Comercio corresponsal") {
    val comercioCorresponsal = ComercioCorresponsal(listOf(chivilcoy, bragado, lobos, pergamino, zarate))
    val comercioCorresponsal2 = ComercioCorresponsal(listOf(rosario, rafaela, sanFrancisco, diamante))
    val comercioCorresponsal3 = ComercioCorresponsal(listOf(rosario, rafaela, amstrong, diamante))

    comercioCorresponsal.esInfluyente().shouldBeTrue()
    comercioCorresponsal2.esInfluyente().shouldBeTrue()
    comercioCorresponsal3.esInfluyente().shouldBeFalse()
  }

  describe("Centro:") {
    val centro = CentroDeDistribucion(chivilcoy)
    val comercioCorresponsal = ComercioCorresponsal(listOf(chivilcoy))
    val vendedorFijo = VendedorFijo(chivilcoy)
    val vendedorViajante = Viajante(listOf(buenosAires, santaFe))
    val certificacion1 = Certificacion(false, 70)
    val certificacion2 = Certificacion(false, 90)
    val certificacion3 = Certificacion(true, 50)

    vendedorFijo.agregarCertificacion(certificacion1)
    comercioCorresponsal.agregarCertificacion(certificacion2)
    vendedorViajante.agregarCertificacion(certificacion3)
    centro.agregarVendedor(vendedorFijo)
    centro.agregarVendedor(comercioCorresponsal)
    centro.agregarVendedor(vendedorViajante)
    centro.vendedorEstrella().shouldBe(comercioCorresponsal)
    centro.puedeCubrir(rosario).shouldBeTrue()
    centro.puedeCubrir(diamante).shouldBeFalse()
    centro.vendedoresGenéricos().should { vendedorViajante }
  }

})
