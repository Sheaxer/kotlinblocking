package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.OrganisationUnit
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
/***
 * Interface extending CrudRepository for OrganisationUnit.
 * Rozhranie rozširujúce CrudRepository pre entity tried OrganisationUnit.
 * @see OrganisationUnit
 * @see CrudRepository
 */
@Repository
interface OrganisationUnitRepository : CrudRepository<OrganisationUnit,String> {
}