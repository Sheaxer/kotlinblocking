package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Client
import org.springframework.data.mongodb.repository.MongoRepository

interface ClientRepository : MongoRepository<Client,String> {
}