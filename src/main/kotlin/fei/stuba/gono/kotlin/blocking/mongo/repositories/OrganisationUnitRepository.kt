package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.OrganisationUnit
import org.springframework.data.mongodb.repository.MongoRepository

interface OrganisationUnitRepository : MongoRepository<OrganisationUnit,String> {
}