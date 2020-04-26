package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.Client
import org.springframework.stereotype.Service
import java.util.*

/***
 * Interface for marshalling and de-marshalling Client entities.
 */
interface ClientService {
    /***
     * Finds the entity with the given id.
     * @param id id of entity.
     * @return Optional containing the entity or Optional.empty() if no entity was found.
     */
    fun getClientById(id: String) : Optional<Client>
}