package fei.stuba.gono.kotlin.blocking.mongo.services

import fei.stuba.gono.kotlin.blocking.mongo.repositories.ClientRepository
import fei.stuba.gono.kotlin.blocking.services.ClientService
import fei.stuba.gono.kotlin.pojo.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
/***
 * Implementation of ClientService for use with MongoDB.
 * @see ClientService
 */
@Service
class ClientServiceImpl @Autowired constructor(private val clientRepository: ClientRepository) : ClientService {

    override fun getClientById(id: String): Optional<Client> = clientRepository.findById(id)

}