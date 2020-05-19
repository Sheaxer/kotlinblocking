package fei.stuba.gono.kotlin.blocking.mongo.services

import fei.stuba.gono.kotlin.blocking.mongo.repositories.OrganisationUnitRepository
import fei.stuba.gono.kotlin.blocking.services.OrganisationUnitService
import fei.stuba.gono.kotlin.pojo.OrganisationUnit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
/***
 * Implementation of OrganisationUnitService using CRUD operations and auto generated instance
 * of OrganisationUnitRepository.
 * Implementácia rozhrania OrganisationUnitService pomocou CRUD operácií a
 * automaticky generovanej inštancie rozhrania OrganisationUnitRepository
 * @see OrganisationUnitRepository
 */
@Service
class OrganisationUnitServiceImpl @Autowired constructor(
        private val organisationUnitRepository: OrganisationUnitRepository
):OrganisationUnitService {
    override fun getOrganisationUnitById(id: String):
            Optional<OrganisationUnit> = organisationUnitRepository.findById(id)


}