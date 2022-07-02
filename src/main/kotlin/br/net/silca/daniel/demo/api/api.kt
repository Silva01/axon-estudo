package br.net.silca.daniel.demo.api

import br.net.silca.daniel.demo.command.TesteId

//command
data class TesteCommand(val name: String)

//event
data class EventTest(val name: String)