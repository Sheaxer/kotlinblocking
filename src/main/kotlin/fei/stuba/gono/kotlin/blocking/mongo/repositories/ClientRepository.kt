package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Client
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
/***
 * Interface extending CrudRepository for Client.
 * @see Client
 * @see CrudRepository
 */
@Repository
interface ClientRepository : CrudRepository<Client,String> {
}