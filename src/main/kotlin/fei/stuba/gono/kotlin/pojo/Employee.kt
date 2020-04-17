package fei.stuba.gono.kotlin.pojo

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotBlank
@Document(collection = "employees")
class Employee {
    @Id
    var id: String? = null

    @NotBlank
    var userName:String? = null

    @NotBlank
    @JsonIgnore
    var password:String? = null
}