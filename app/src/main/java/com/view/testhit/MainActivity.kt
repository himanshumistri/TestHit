package com.view.testhit

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.JsonObject
import com.view.testhit.api.ApiClient
import com.view.testhit.ui.theme.TestHitTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Author Himanshu Mistri
 */
class MainActivity : ComponentActivity() {

    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestHitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }

        mHandler.postDelayed(
            {
                hitApi()

            }, 500L
        )
    }
}


private fun hitApi(){
    Log.i("TAG","API Called")

    val apiObject = ApiClient.getApiInterface()?.getAccessToken("GranType","ClientId",
    "ClientSecret","Resouce")

    apiObject?.enqueue(object : Callback<JsonObject>{
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

            Log.i("TAG","Response Body ${response.body()}")

        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            Log.e("TAG","onFailure error $t")
            t.printStackTrace()
        }


    })


}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestHitTheme {
        Greeting("Android")
    }
}