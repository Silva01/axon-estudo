package br.net.silca.daniel.demo.command

import br.net.silca.daniel.demo.api.EventTest
import br.net.silca.daniel.demo.api.TesteCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

@Aggregate
class Teste {

    @AggregateIdentifier
    private lateinit var testeId: ResponseDados
    private lateinit var nameTeste: String

    private constructor()

    @CommandHandler
    constructor(testeCommand: TesteCommand) {
        AggregateLifecycle.apply(EventTest(testeCommand.name))
    }

    @EventSourcingHandler
    fun on(eventTest: EventTest) {
        this.testeId = ResponseDados()
        this.testeId.name = eventTest.name
    }

}