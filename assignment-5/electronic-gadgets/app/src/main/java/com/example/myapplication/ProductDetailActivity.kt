package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var productNameTextView: TextView
    private lateinit var productDescriptionTextView: TextView
    private lateinit var productPriceTextView: TextView
    private lateinit var productImageView: ImageView
    private lateinit var addToCartButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        productNameTextView = findViewById(R.id.productNameTextView)
        productDescriptionTextView = findViewById(R.id.productDescriptionTextView)
        productPriceTextView = findViewById(R.id.productPriceTextView)
        productImageView = findViewById(R.id.productImageView)
        addToCartButton = findViewById(R.id.addToCartButton)

        val productName = intent.getStringExtra(EXTRA_PRODUCT_NAME)
        val productDescription = intent.getStringExtra(EXTRA_PRODUCT_DESCRIPTION)
        val productPrice = intent.getDoubleExtra(EXTRA_PRODUCT_PRICE, 0.0)
        val productImageResId = intent.getIntExtra(EXTRA_PRODUCT_IMAGE_RES_ID, 0)

        productNameTextView.text = productName
        productDescriptionTextView.text = productDescription
        productPriceTextView.text = "Price: $$productPrice"
        productImageView.setImageResource(productImageResId)

        addToCartButton.setOnClickListener {
            val message = "Added $productName to cart"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val EXTRA_PRODUCT_NAME = "extra_product_name"
        const val EXTRA_PRODUCT_DESCRIPTION = "extra_product_description"
        const val EXTRA_PRODUCT_PRICE = "extra_product_price"
        const val EXTRA_PRODUCT_IMAGE_RES_ID = "extra_product_image_res_id"
    }
}