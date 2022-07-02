package br.net.silca.daniel.demo.command

import br.net.silca.daniel.demo.api.EventTest
import br.net.silca.daniel.demo.api.TesteCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class Teste {

    @AggregateIdentifier
    private lateinit var testeId: TesteId
    private lateinit var nameTeste: String

    private constructor()

    @CommandHandler
    constructor(testeCommand: TesteCommand) {
        AggregateLifecycle.apply(EventTest(testeCommand.id, testeCommand.name))
    }

    @EventSourcingHandler
    fun on(eventTest: EventTest) {
        this.testeId = eventTest.id
        this.nameTeste = eventTest.name
    }

}