package fei.stuba.gono.kotlin.mongo

import fei.stuba.gono.kotlin.mongo.converters.OffsetDateTimeReadingConverter
import fei.stuba.gono.kotlin.mongo.converters.OffsetDateTimeWritingConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import java.util.*
/***
 * Configuration class that modifies default configuration of MongoDB.
 * Konfiguračná trieda, ktorá modifikuje predvolenú konfiguráciu MongoDB.
 */
@Configuration
class MongoConfig {
    /***
     * Adds custom converters for converting Java Classes that are unable to be serialized
     * / deserialized by MongoDB to classes that are able to be serialized / deserialized.
     * Trieda ktorá pridáva vlastné prevodníky pre MongoDB na konverziu Java tried ktoré nie je možné
     * priamo serializovať v MongoDB.
     * @return Instance of class MongoCustomConversions instantiated by list of
     * custom MongoDB converters.
     * Inštancia triedy MongoCustomConvertions vytvorená zoznamom vlastných MongoDB prevodníkov.
     * @see MongoCustomConversions
     * @see org.springframework.data.convert.WritingConverter
     * @see org.springframework.data.convert.ReadingConverter
     */
    @Bean
    fun customConversions() : MongoCustomConversions {
        val converters: MutableList<Converter<*, *>> = mutableListOf()
        converters.add(OffsetDateTimeReadingConverter())
        converters.add(OffsetDateTimeWritingConverter())
        return MongoCustomConversions(converters)
    }
}