package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.OrganisationUnit
import org.springframework.stereotype.Service
import java.util.*

/***
 * Interface for marshalling and de-marshalling OrganisationUnit entities.
 */
interface OrganisationUnitService {
    /***
     * Finds the entity with the given id.
     * @param id id of the entity
     * @return Optional containing the entity or Optional.empty() if no entity was found.
     */
    fun getOrganisationUnitById(id: String) : Optional<OrganisationUnit>
}