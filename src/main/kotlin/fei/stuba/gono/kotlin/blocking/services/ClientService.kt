package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.Client
import org.springframework.stereotype.Service


interface ClientService {

    fun getClientById(id: String) : Client?
}