package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ProductAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    private val products = ArrayList<Product>()
    private val cartItems = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProductAdapter(products, this)
        recyclerView.adapter = adapter

        products.add(Product("iPad", "iPad Pro 11-inch", 400.0, R.drawable.ipad))
        products.add(Product("MacBook M3 Pro", "12-core CPU\n18-core GPU", 2500.00, R.drawable.macbook))
        products.add(Product("Dell Inspiron", "13th Gen Intel® Core™ i7", 1499.00, R.drawable.laptop))
        products.add(Product("Logitech Keyboard", "Logitech - PRO X\nTKL LIGHTSPEED Wireless", 199.00, R.drawable.keyboard))
        products.add(Product("MacBook M3 Max", "14-core CPU\n30-core GPU", 3499.00, R.drawable.macbook))

        val viewCartButton: Button = findViewById(R.id.viewCartButton)
        viewCartButton.setOnClickListener {
            viewCart()
        }
    }

    override fun onItemClick(product: Product) {
        val intent = Intent(this, ProductDetailActivity::class.java).apply {
            putExtra(ProductDetailActivity.EXTRA_PRODUCT_NAME, product.productName)
            putExtra(ProductDetailActivity.EXTRA_PRODUCT_DESCRIPTION, product.productDescription)
            putExtra(ProductDetailActivity.EXTRA_PRODUCT_PRICE, product.cost)
            putExtra(ProductDetailActivity.EXTRA_PRODUCT_IMAGE_RES_ID, product.imageResourceId)
        }
        startActivity(intent)
    }

    override fun onAddToCartClick(product: Product) {
        cartItems.add(product)
        val message = "Added ${product.productName} to cart"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun viewCart() {
        val cartItemNames = cartItems.joinToString("\n") { it.productName }
        val cartMessage = if (cartItems.isNotEmpty()) {
            "Cart items:$cartItemNames"
        } else {
            "Cart is empty"
        }
        Toast.makeText(this, cartMessage, Toast.LENGTH_LONG).show()
    }
}