package fei.stuba.gono.kotlin.blocking.mongo.services

import fei.stuba.gono.kotlin.blocking.mongo.repositories.ClientRepository
import fei.stuba.gono.kotlin.blocking.services.ClientService
import fei.stuba.gono.kotlin.pojo.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientServiceImpl @Autowired constructor(private val clientRepository: ClientRepository) : ClientService {

    override fun getClientById(id: String): Client? {
        return clientRepository.findById(id).orElse(null)
    }
}