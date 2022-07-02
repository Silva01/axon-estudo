package br.net.silca.daniel.demo.command

import java.util.UUID


data class TesteId(val id: UUID = UUID.randomUUID(), val name: String) {
    override fun toString(): String = id.toString()
}