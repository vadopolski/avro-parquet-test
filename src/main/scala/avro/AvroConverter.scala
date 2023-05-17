package avro

import example.Customer
import org.apache.avro.Schema
import org.apache.avro.file.DataFileWriter
import org.apache.avro.generic.{GenericDatumWriter, GenericRecord}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path

import java.io.File

object AvroConverter extends App {
  val path = new Path("/test3/target.parquet")

  val configuration = new Configuration()

  val customers: List[Customer] = List.fill(100) {
    Customer.newBuilder()
      .setFirstName("test")
      .setLastName("test2")
      .setHeight(2f)
      .setWeight(3f)
      .build()
  }

  customers.foreach(println)

  val schema: Schema = Customer.SCHEMA$

  val file = new File("file.avro")

  val datumWriter = new GenericDatumWriter[GenericRecord](schema)
  val dataFileWriter = new DataFileWriter[GenericRecord](datumWriter)

  dataFileWriter.create(schema, file)

  customers.foreach(dataFileWriter.append(_))
  dataFileWriter.flush()
  dataFileWriter.close()
}
