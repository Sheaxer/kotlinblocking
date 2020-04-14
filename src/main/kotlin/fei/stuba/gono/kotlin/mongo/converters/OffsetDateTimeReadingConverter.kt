package fei.stuba.gono.kotlin.mongo.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*
@ReadingConverter
class OffsetDateTimeReadingConverter : Converter<Date,OffsetDateTime> {
    override fun convert(p0: Date): OffsetDateTime? {
        return p0.toInstant().atOffset(ZoneOffset.UTC)
    }
}