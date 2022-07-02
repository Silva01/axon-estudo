package br.net.silca.daniel.demo.gui

import br.net.silca.daniel.demo.api.TesteCommand
import br.net.silca.daniel.demo.command.Teste
import br.net.silca.daniel.demo.command.TesteId
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.BiConsumer

@RestController
@RequestMapping("/teste")
class TesteController {

    @Autowired private lateinit var commandGateway: CommandGateway

    private val success = AtomicInteger()
    private val error = AtomicInteger()
    private val remaining = AtomicInteger()

    @GetMapping
    public fun testar(): CompletableFuture<TesteId>? = commandGateway.send<TesteId>(TesteCommand(TesteId(UUID.randomUUID(), "Teste"), "Teste"))
        .whenComplete(BiConsumer { result: Any?, throwable: Throwable? ->
            if (throwable == null) {
                success.incrementAndGet()
            } else {
                error.incrementAndGet()
            }
            remaining.decrementAndGet()
        })

}