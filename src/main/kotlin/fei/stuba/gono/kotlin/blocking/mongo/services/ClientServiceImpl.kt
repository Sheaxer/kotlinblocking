package fei.stuba.gono.kotlin.blocking.mongo.services

import fei.stuba.gono.kotlin.blocking.mongo.repositories.ClientRepository
import fei.stuba.gono.kotlin.blocking.services.ClientService
import fei.stuba.gono.kotlin.pojo.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
/***
 * Implementation of ClientService using CRUD operations with auto generated instance of
 * ClientRepository.
 * Implementácia ClientService rozhrania pomocou CRUD operácií a automaticky generovanej inštancie
 * rozhrania ClientRepository.
 * @see ClientRepository
 */
@Service
class ClientServiceImpl @Autowired constructor(private val clientRepository: ClientRepository) : ClientService {

    override fun getClientById(id: String): Optional<Client> = clientRepository.findById(id)

}