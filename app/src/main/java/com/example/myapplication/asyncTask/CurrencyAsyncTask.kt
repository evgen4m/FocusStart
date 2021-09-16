package com.example.myapplication.asyncTask

import android.os.AsyncTask
import com.example.myapplication.model.CurrencyModel
import org.json.JSONArray
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONException

import org.json.JSONObject


class CurrencyAsyncTask : AsyncTask<Void, Void, List<CurrencyModel>>() {

    companion object {
        const val URL = "https://www.cbr-xml-daily.ru/daily_json.js"
    }

    override fun doInBackground(vararg p0: Void?): List<CurrencyModel>? {
        val result: MutableList<CurrencyModel> = ArrayList<CurrencyModel>()

        try {
            val u = URL(URL)
            val conn: HttpURLConnection = u.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connect()
            val inputStream = conn.inputStream

            // Read the stream
            val b = ByteArray(1024)
            val baos = ByteArrayOutputStream()
            while (inputStream.read(b) !== -1) baos.write(b)
            val jsonResp = String(baos.toByteArray())
            val arr = JSONArray(jsonResp)
            for (i in 0 until arr.length()) {
                convertContact(arr.getJSONObject(i))?.let { result.add(it) }
            }
            return result
        } catch (t: Throwable) {
            t.printStackTrace()
        }
        return result
    }

    @Throws(JSONException::class)
    private fun convertContact(obj: JSONObject): CurrencyModel? {
        val id = obj.getInt("ID")
        val numCode = obj.getString("NumCode")
        val charCode = obj.getString("CharCode")
        val nominal = obj.getString("Nominal")
        val name = obj.getString("Name")
        val value = obj.getDouble("Value")
        val previous = obj.getDouble("Previous")
        return CurrencyModel(
            id = id,
            numCode = numCode,
            charCode = charCode,
            nominal = nominal,
            name = name,
            value = value,
            previous = previous
        )
    }


}