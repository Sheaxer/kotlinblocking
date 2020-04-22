package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.OrganisationUnit
import org.springframework.stereotype.Service
import java.util.*


interface OrganisationUnitService {

    fun getOrganisationUnitById(id: String) : Optional<OrganisationUnit>
}