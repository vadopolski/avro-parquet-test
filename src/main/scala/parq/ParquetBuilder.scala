package parq

//import example.Customer
import com.example.Customer
import org.apache.avro.Schema
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.parquet.avro.AvroParquetWriter
import org.apache.parquet.hadoop.ParquetWriter
import org.apache.parquet.hadoop.metadata.CompressionCodecName

object ParquetBuilder extends App {
  val path = new Path("/test4/target.parquet")

  val configuration = new Configuration()

  val customers: List[Customer] = List.fill(100) (Customer("test", Option(2), 3f))

  customers.foreach(println)

  val schema: Schema = Customer.SCHEMA$

  def createTypeWriter[T]: ParquetWriter[T] = AvroParquetWriter
    .builder[T](path)
    .withConf(configuration)
    .withCompressionCodec(CompressionCodecName.SNAPPY)
    .withSchema(schema)
    .build()

  @transient val parquetWriter: ParquetWriter[Customer] = createTypeWriter[Customer]

  customers.foreach { r =>
    println(s"!!!!!   R is ${r.toString}")
    parquetWriter.write(r)
  }

  parquetWriter.close()


}
