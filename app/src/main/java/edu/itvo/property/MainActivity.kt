package edu.itvo.property

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import edu.itvo.property.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val properties= ArrayList<Property>()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val zones = listOf(
            Zone("MAR","Zona Marginada", 2.00),
            Zone("RUR","Zona Rural", 5.00),
            Zone("URB","Zona Urbuna", 10.00),
            Zone("RES","Zona Residencial", 20.00),
        )
        val adapter = ArrayAdapter(
            this, android.R.layout.simple_dropdown_item_1line, zones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spZones.setSelection(0, true)
        binding.spZones.adapter = adapter

        binding.btnCalculateTotal.isEnabled = false

        binding.btnAddProperty.setOnClickListener {
            val zone = binding.spZones.selectedItem as Zone
            val property = Property(
                zone = zone,
                areaInSquareMeter = binding.etAreaProperty.text.toString().toDouble()
            )
            properties.add(property)
            Toast.makeText(this, R.string.add_property, Toast.LENGTH_LONG).show()
            binding.etAreaProperty.setText("0")
            binding.etAreaProperty.requestFocus()
            binding.edProperties.setText(properties.toList().toString())
            binding.btnCalculateTotal.isEnabled=true

        }
        binding.btnCalculateTotal.setOnClickListener {
            val genre:String = when (binding.rbGenreH.isChecked){
                true->"H"
                else ->"M"
            }

            val person = Person(
                fullName = binding.etName.text.toString(),
                dateOfBirth = LocalDate.parse(binding.etDateOfBirth.text.toString()),
                genre = genre,
                isSingleMother = binding.chkSingleMother.isChecked
            )
            val tax = Tax.Builder(folio = 1, dateOfPayment = LocalDate.now(), owner = person)
                .addAllProperties(properties)
                .build()

            val decimalFormat = DecimalFormat("#,###.00")

            Toast.makeText(
                this,
                "El impuesto a pagar de ${person.fullName} " +
                        "es de:  $${decimalFormat.format(tax.getTotal())}", Toast.LENGTH_LONG
            ).show()
            properties.clear()
        }
        with (binding){
            rgGenre.setOnCheckedChangeListener { _, itemSelectedId ->
               chkSingleMother.isEnabled= when (itemSelectedId){
                    rbGenreM.id -> true
                    else->false
                }
            }
        }


    }
}