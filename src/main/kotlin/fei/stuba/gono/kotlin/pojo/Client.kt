package fei.stuba.gono.kotlin.pojo

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotBlank
@Document(value = "clients")
@TypeAlias(value = "client")
class Client {
    @NotBlank
    var firstName: String? = null
    @NotBlank
    var surName: String? = null
    @NotBlank
    @Id
    var id: String? = null


}