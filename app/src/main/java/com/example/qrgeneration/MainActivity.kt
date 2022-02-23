package com.example.qrgeneration

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {

    private lateinit var imageQR : ImageView
    private lateinit var editName : EditText
    private lateinit var editSurname : EditText
    private lateinit var generateButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageQR=findViewById(R.id.imageQR)
        editName=findViewById(R.id.editName)
        editSurname=findViewById(R.id.editSurname)
        generateButton=findViewById(R.id.generateQR)

        generateButton.setOnClickListener {

            val name=editName.text.toString().trim()
            val surname=editSurname.text.toString().trim()

            val person= "$name $surname"

            if(name.isEmpty() || surname.isEmpty()){
                Toast.makeText(this,"Boşlukları doldurunuz",Toast.LENGTH_SHORT).show()
            }else{
                val writer =QRCodeWriter()

                try{

                    val bitMatrix = writer.encode(person, BarcodeFormat.QR_CODE,512,512)
                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                    for(x in 0 until width){
                        for(y in 0 until height){
                            bmp.setPixel(x,y, if (bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                        }
                    }
                    imageQR.setImageBitmap(bmp)

                }catch (e:WriterException){

                }

            }

        }


    }
}