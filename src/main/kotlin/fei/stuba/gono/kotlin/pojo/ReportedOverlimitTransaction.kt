package fei.stuba.gono.kotlin.pojo

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fei.stuba.gono.kotlin.blocking.json.ClientDeserializer
import fei.stuba.gono.kotlin.blocking.json.ClientSerializer
import fei.stuba.gono.kotlin.validation.annotations.DaysBeforeDate
import fei.stuba.gono.kotlin.validation.annotations.MaxAmount
import fei.stuba.gono.kotlin.validation.annotations.ValidAccount
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Document(value = "ReportedOverlimitTransactions")
class ReportedOverlimitTransaction {
    @Id
    var id: String?=null

    @NotNull(message = "ORDERCATEGORY_INVALID")
    var orderCategory:OrderCategory? =null

    var state:State? = null

    @NotNull(message="SOURCEACCOUNT_INVALID")
    @ValidAccount(message = "SOURCEACCOUNT_INVALID")
    var sourceAccount:Account? = null

    @DBRef
    @Valid
    @NotNull(message = "CLIENTID_NOT_VALID")
    @JsonDeserialize(using = ClientDeserializer::class)
    @JsonSerialize(using = ClientSerializer::class)
    var clientId: Client? = null

    @NotBlank(message = "IDENTIFICATIONID_INVALID")
    var identificationId: String? = null

    @NotNull
    @MaxAmount(message = "FIELD_INVALID")
    var amount: Money? = null

    @NotNull(message="VAULT_INVALID")
    @NotEmpty(message="VAULT_INVALID")
    var vault: List<Vault>? = null

    var modificationDate: OffsetDateTime?
    get() = this.modificationDate?.toInstant()?.atOffset(ZoneOffset.of(this.zoneOffset))
    set(modDate) {
        this.modificationDate = modDate
        this.zoneOffset = modDate?.offset?.id ?: "+00:00"
    }

    @NotNull(message="TRANSFERDATE_INVALID")
    @FutureOrPresent(message = "INVALID_DATE_IN_PAST")
    @DaysBeforeDate(message="FIELD_INVALID_TOO_NEAR_IN_FUTURE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    var transferDate: Date? = null

    var note:String? =null

    @DBRef
    @NotNull(message="ORGANISATIONUNITID_NOT_VALID")
    var organisationUnitID: OrganisationUnit? = null

    @DBRef
    @NotNull(message="CREATEDBY_NOT_VALID")
    var createdBy: Employee? = null

    @JsonIgnore
    var zoneOffset:String = "+00:00"
}