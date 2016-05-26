package monad.uk.kotlin_weather_app

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import android.os.AsyncTask
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import android.util.Log

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tst = test()
        tst.execute()
        val btn =  findViewById(R.id.search) as Button
        btn.setOnClickListener({
            val EditText = findViewById(R.id.editText) as EditText
            val textView = findViewById(R.id.mainText) as TextView
        })
    }

    fun isValid(postCode:String):Boolean {
      val bool:Boolean
      if(postCode.length <= 8 &&  postCode.length >= 8 ) bool = true
      else bool = false
      return  bool
    }

    public fun Stringer(str:HttpEntity<String>){

       val body = str.getBody()
       val textView = findViewById(R.id.mainText) as TextView
       textView.setText(body)
    }

    internal class test : AsyncTask<Void, Void, HttpEntity<String>>(){

        override fun doInBackground(vararg params: Void?): HttpEntity<String> {
            val  url = "http://api.apixu.com/v1/current.json?key=2478ffc0bd704a6eb65102010162605&q=BN228RU"
            val restTemplate =  RestTemplate()
            restTemplate.getMessageConverters().add(MappingJackson2HttpMessageConverter())
            val headers = HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            val entity = HttpEntity<String>(url, headers)
            return entity
        }

        override fun onPostExecute(result: HttpEntity<String>) {
            try {
                Log.d("Request", "THISFAR")
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

    }
}
