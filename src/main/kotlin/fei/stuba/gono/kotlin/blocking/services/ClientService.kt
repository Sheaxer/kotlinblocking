package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.Client
import org.springframework.stereotype.Service
import java.util.*

/***
 * Interface for marshalling and de-marshalling Client entities.
 * Rozhranie na marhalling a de-marshalling entít triedy Client.
 */
interface ClientService {

    /***
     * Finds the entity with the given id.
     * Nájde entitu so zadaným id.
     * @param id id of entity.
     *           id entity.
     * @return Optional containing the entity or Optional.empty() if no entity was found.
     *
     * Optional obsahujúci entitu alebo Optional.empty(), ek entita nebola nájdená.
     */
    fun getClientById(id: String) : Optional<Client>
}