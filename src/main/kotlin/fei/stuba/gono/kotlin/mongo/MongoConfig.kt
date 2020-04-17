package fei.stuba.gono.kotlin.mongo

import fei.stuba.gono.kotlin.mongo.converters.OffsetDateTimeReadingConverter
import fei.stuba.gono.kotlin.mongo.converters.OffsetDateTimeWritingConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import java.util.*
@Configuration
class MongoConfig {

    @Bean
    fun customConversions() : MongoCustomConversions {
        val converters: MutableList<Converter<*, *>> = mutableListOf()
        converters.add(OffsetDateTimeReadingConverter())
        converters.add(OffsetDateTimeWritingConverter())
        return MongoCustomConversions(converters)
    }
}