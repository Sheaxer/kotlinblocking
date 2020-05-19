package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.OrganisationUnit
import org.springframework.stereotype.Service
import java.util.*

/***
 * Interface for marshalling and de-marshalling OrganisationUnit entities.
 * Rozhranie pre marshalling a de-marshalling entít tried OrganisationUnit.
 */
interface OrganisationUnitService {
    /***
     * Finds the entity with the given id.
     * Nájde entitu so zadaným id
     * @param id id of the entity.
     *           id entity.
     * @return Optional containing the entity or Optional.empty()
     * if no entity was found.
     * Optional obsahujúci entitu alebo Optional.empty(), ak entita neexsistuje.
     */
    fun getOrganisationUnitById(id: String) : Optional<OrganisationUnit>
}