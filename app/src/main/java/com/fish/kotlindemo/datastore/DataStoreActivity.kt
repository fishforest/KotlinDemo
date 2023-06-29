package com.fish.kotlindemo.datastore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fish.kotlindemo.databinding.ActivityDatastoreBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DataStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatastoreBinding

    private var count = 0

    val state = MutableStateFlow(null)

    private val myDataStore by lazy {
        MyDataStore(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatastoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRead.setOnClickListener {
            lifecycleScope.launch {
//                myDataStore.getNameStringData { v1, v2 ->
//                    Toast.makeText(this@DataStoreActivity, "key=$v1 value=$v2", Toast.LENGTH_SHORT)
//                        .show()
//                }

//                myDataStore.queryDataV2()

//                state.collect {
//                    println(it)
//                }

                myDataStore.getSP()
                myDataStore.observe()
            }
        }

        binding.btnWrite.setOnClickListener {
            lifecycleScope.launch {
//                myDataStore.putNameStringData("fish${count++}")
//                myDataStore.saveData()
//                myDataStore.saveData2()
//
//
//                GlobalScope.launch(Dispatchers.IO) {
//                    myDataStore.saveData2()
//                }
//
//                GlobalScope.launch(Dispatchers.Main) {
//                    myDataStore.saveData2()
//                }
                myDataStore.saveSP()
                myDataStore.updateDataStore("fish", 100)
            }
        }
//
//        GlobalScope.launch(Dispatchers.IO) {
//            myDataStore.queryDataV2()
//        }
//
//        GlobalScope.launch(Dispatchers.Main) {
//            myDataStore.saveData2()
//        }
//
//        lifecycleScope.launch {
//            MyDataStore(this@DataStoreActivity).saveData2()
//        }
    }
}