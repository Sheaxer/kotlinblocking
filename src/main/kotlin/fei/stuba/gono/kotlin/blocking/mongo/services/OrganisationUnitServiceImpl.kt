package fei.stuba.gono.kotlin.blocking.mongo.services

import fei.stuba.gono.kotlin.blocking.mongo.repositories.OrganisationUnitRepository
import fei.stuba.gono.kotlin.blocking.services.OrganisationUnitService
import fei.stuba.gono.kotlin.pojo.OrganisationUnit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrganisationUnitServiceImpl @Autowired constructor(
        private val organisationUnitRepository: OrganisationUnitRepository
):OrganisationUnitService {
    override fun getOrganisationUnitById(id: String): OrganisationUnit? {
        return organisationUnitRepository.findById(id).orElse(null)
    }

}