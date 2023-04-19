package com.example.converter

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.content.Intent
import android.text.Editable
import android.text.TextUtils.substring
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import org.jsoup.Jsoup
import java.io.IOException
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)


        AsyncTaskExample(this).execute()

        val date: String = SimpleDateFormat("dd.MM.yy").format(Date())

        fun toastMe() {
            val myToast = Toast.makeText(this, "Double Tap to exchange!", Toast.LENGTH_LONG)
            myToast.show()
        }

        fun Cur_data() {
            cur_data.text = date
        }

        Cur_data()

        toastMe()

        dev.setOnClickListener {
            if (dev.text.toString() == ">Powered by Kotlin®") {
                dev.setText(R.string.dev)
            }

            if (dev.text.toString() == "Developed by Alexander Marjin©") {
                dev.setText(R.string.pow)
            }
        }

    }

    class AsyncTaskExample(@SuppressLint("StaticFieldLeak") private var activity: MainActivity?) :
        AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: String?): String? {
            var result = ""

            try {

                val doc: Document =
                    Jsoup.connect("https://www.nbrb.by/statistics/rates/ratesdaily.asp").get()
                val tables: Elements = doc.getElementsByTag("tbody")
                val my_table: Element = tables.get(0)
                val elements_from_table: Elements = my_table.children()
                val dol: Element = elements_from_table.get(5)
                val dols: Elements = dol.children()
                val usd_str = dols.get(2).text()
                val eur_str = elements_from_table.get(6).child(2).text()
                val rub_str = elements_from_table.get(17).child(2).text()
                val uah_str = elements_from_table.get(3).child(2).text()
                val pln_str = elements_from_table.get(7).child(2).text()
                val kzt_str = elements_from_table.get(21).child(2).text()
                val jpy_str = elements_from_table.get(8).child(2).text()
                result =
                    usd_str + "/" + eur_str + "/" + rub_str + "/" + uah_str + "/" + pln_str + "/" + kzt_str + "/" + jpy_str

            } catch (e: IOException) {
                e.printStackTrace()
            }

            return result

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var result = result?.replace(",", ".")

            val usd = substring(result, 0, 6).replace(",", ".").toDouble()
            val eur = substring(result, 7, 13).replace(",", ".").toDouble()
            val rub = substring(result, 14, 20).replace(",", ".").toDouble() / 100
            val uah = substring(result, 21, 27).replace(",", ".").toDouble() / 100
            val pln = substring(result, 28, 34).replace(",", ".").toDouble() / 10
            val kzt = substring(result, 35, 41).replace(",", ".").toDouble() / 1000
            val jpy = substring(result, 42, 48).replace(",", ".").toDouble() / 100

            fun Double.round(decimals: Int = 4): String = "%.${decimals}f".format(this)

            activity?.user_enter_1?.setText((usd / usd).roundToInt().toString().replace(",", "."))
            activity?.user_enter_2?.setText((usd / eur).round().toString().replace(",", "."))
            activity?.user_enter_3?.setText((usd / 1).round().toString().replace(",", "."))
            activity?.user_enter_4?.setText((usd / rub).round().toString().replace(",", "."))
            activity?.user_enter_5?.setText((usd / uah).round().toString().replace(",", "."))
            activity?.user_enter_6?.setText((usd / pln).round().toString().replace(",", "."))
            activity?.user_enter_7?.setText((usd / kzt).round().toString().replace(",", "."))
            activity?.user_enter_8?.setText((usd / jpy).round().toString().replace(",", "."))

            val editTexts: ArrayList<EditText?> = ArrayList() // Container list

            editTexts.add(activity?.user_enter_1)

            editTexts.add(activity?.user_enter_2)

            editTexts.add(activity?.user_enter_3)

            editTexts.add(activity?.user_enter_4)

            editTexts.add(activity?.user_enter_5)

            editTexts.add(activity?.user_enter_6)

            editTexts.add(activity?.user_enter_7)

            editTexts.add(activity?.user_enter_8)


            for (editText in editTexts) {
                editText?.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (editText === editTexts[0]) {
                            if (editText.getText().toString() != "") {
                                val num = editText.getText().toString().toDouble()
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_2s?.setText(
                                    (num * usd / eur).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_3s?.setText(
                                    (num * usd / 1).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_4s?.setText(
                                    (num * usd / rub).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_5s?.setText(
                                    (num * usd / uah).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_6s?.setText(
                                    (num * usd / pln).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_7s?.setText(
                                    (num * usd / kzt).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_8s?.setText(
                                    (num * usd / jpy).round().toString().replace(",", ".")
                                )

                            } else {
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_2s?.setText("")
                                activity?.user_enter_3s?.setText("")
                                activity?.user_enter_4s?.setText("")
                                activity?.user_enter_5s?.setText("")
                                activity?.user_enter_6s?.setText("")
                                activity?.user_enter_7s?.setText("")
                                activity?.user_enter_8s?.setText("")
                            }

                        }

                        if (editText === editTexts[1]) {
                            if (editText.getText().toString() != "") {
                                val num =
                                    editText.getText().toString().toDouble()

                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.GONE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.GONE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.GONE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.GONE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.GONE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.GONE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.GONE)

                                activity?.user_enter_1s?.setText(
                                    (num * eur / usd).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_3s?.setText(
                                    (num * eur / 1).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_4s?.setText(
                                    (num * eur / rub).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_5s?.setText(
                                    (num * eur / uah).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_6s?.setText(
                                    (num * eur / pln).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_7s?.setText(
                                    (num * eur / kzt).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_8s?.setText(
                                    (num * eur / jpy).round().toString().replace(",", ".")
                                )
                            } else {
                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_1s?.setText("")
                                activity?.user_enter_3s?.setText("")
                                activity?.user_enter_4s?.setText("")
                                activity?.user_enter_5s?.setText("")
                                activity?.user_enter_6s?.setText("")
                                activity?.user_enter_7s?.setText("")
                                activity?.user_enter_8s?.setText("")
                            }

                        }

                        if (editText === editTexts[2]) {
                            if (editText.getText().toString() != "") {
                                val num =
                                    editText.getText().toString().toDouble()

                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.GONE)
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.GONE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.GONE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.GONE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.GONE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.GONE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.GONE)

                                activity?.user_enter_1s?.setText(
                                    (num / usd).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_2s?.setText(
                                    (num / eur).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_4s?.setText(
                                    (num / rub).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_5s?.setText(
                                    (num / uah).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_6s?.setText(
                                    (num / pln).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_7s?.setText(
                                    (num / kzt).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_8s?.setText(
                                    (num / jpy).round().toString().replace(",", ".")
                                )
                            } else {
                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_1s?.setText("")
                                activity?.user_enter_2s?.setText("")
                                activity?.user_enter_4s?.setText("")
                                activity?.user_enter_5s?.setText("")
                                activity?.user_enter_6s?.setText("")
                                activity?.user_enter_7s?.setText("")
                                activity?.user_enter_8s?.setText("")
                            }

                        }

                        if (editText === editTexts[3]) {
                            if (editText.getText().toString() != "") {
                                val num =
                                    editText.getText().toString().toDouble()

                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.INVISIBLE)

                                activity?.user_enter_1s?.setText(
                                    (num * rub / usd).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_2s?.setText(
                                    (num * rub / eur).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_3s?.setText(
                                    (num * rub).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_5s?.setText(
                                    (num * rub / uah).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_6s?.setText(
                                    (num * rub / pln).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_7s?.setText(
                                    (num * rub / kzt).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_8s?.setText(
                                    (num * rub / jpy).round().toString().replace(",", ".")
                                )
                            } else {
                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_1s?.setText("")
                                activity?.user_enter_2s?.setText("")
                                activity?.user_enter_4s?.setText("")
                                activity?.user_enter_5s?.setText("")
                                activity?.user_enter_6s?.setText("")
                                activity?.user_enter_7s?.setText("")
                                activity?.user_enter_8s?.setText("")
                            }

                        }

                        if (editText === editTexts[4]) {
                            if (editText.getText().toString() != "") {
                                val num =
                                    editText.getText().toString().toDouble()

                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.INVISIBLE)

                                activity?.user_enter_1s?.setText(
                                    (num * uah / usd).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_2s?.setText(
                                    (num * uah / eur).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_3s?.setText(
                                    (num * uah).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_4s?.setText(
                                    (num * uah / rub).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_6s?.setText(
                                    (num * uah / pln).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_7s?.setText(
                                    (num * uah / kzt).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_8s?.setText(
                                    (num * uah / jpy).round().toString().replace(",", ".")
                                )
                            } else {
                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_1s?.setText("")
                                activity?.user_enter_2s?.setText("")
                                activity?.user_enter_4s?.setText("")
                                activity?.user_enter_5s?.setText("")
                                activity?.user_enter_6s?.setText("")
                                activity?.user_enter_7s?.setText("")
                                activity?.user_enter_8s?.setText("")
                            }

                        }

                        if (editText === editTexts[5]) {
                            if (editText.getText().toString() != "") {
                                val num =
                                    editText.getText().toString().toDouble()

                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.INVISIBLE)

                                activity?.user_enter_1s?.setText(
                                    (num * pln / usd).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_2s?.setText(
                                    (num * pln / eur).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_3s?.setText(
                                    (num * pln).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_4s?.setText(
                                    (num * pln / rub).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_5s?.setText(
                                    (num * pln / uah).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_7s?.setText(
                                    (num * pln / kzt).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_8s?.setText(
                                    (num * pln / jpy).round().toString().replace(",", ".")
                                )
                            } else {
                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_1s?.setText("")
                                activity?.user_enter_2s?.setText("")
                                activity?.user_enter_4s?.setText("")
                                activity?.user_enter_5s?.setText("")
                                activity?.user_enter_6s?.setText("")
                                activity?.user_enter_7s?.setText("")
                                activity?.user_enter_8s?.setText("")
                            }

                        }

                        if (editText === editTexts[6]) {
                            if (editText.getText().toString() != "") {
                                val num =
                                    editText.getText().toString().toDouble()

                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.INVISIBLE)

                                activity?.user_enter_1s?.setText(
                                    (num * kzt / usd).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_2s?.setText(
                                    (num * kzt / eur).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_3s?.setText(
                                    (num * kzt).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_4s?.setText(
                                    (num * kzt / rub).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_5s?.setText(
                                    (num * kzt / uah).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_6s?.setText(
                                    (num * kzt / pln).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_8s?.setText(
                                    (num * kzt / jpy).round().toString().replace(",", ".")
                                )
                            } else {
                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_8s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_8?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_1s?.setText("")
                                activity?.user_enter_2s?.setText("")
                                activity?.user_enter_4s?.setText("")
                                activity?.user_enter_5s?.setText("")
                                activity?.user_enter_6s?.setText("")
                                activity?.user_enter_7s?.setText("")
                                activity?.user_enter_8s?.setText("")
                            }

                        }

                        if (editText === editTexts[7]) {
                            if (editText.getText().toString() != "") {
                                val num =
                                    editText.getText().toString().toDouble()

                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.INVISIBLE)

                                activity?.user_enter_1s?.setText(
                                    (num * jpy / usd).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_2s?.setText(
                                    (num * jpy / eur).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_3s?.setText(
                                    (num * jpy).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_4s?.setText(
                                    (num * jpy / rub).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_5s?.setText(
                                    (num * jpy / uah).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_6s?.setText(
                                    (num * jpy / pln).round().toString().replace(",", ".")
                                )
                                activity?.user_enter_7s?.setText(
                                    (num * jpy / kzt).round().toString().replace(",", ".")
                                )
                            } else {
                                activity?.user_enter_1s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_1?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_2s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_2?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_3s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_3?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_4s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_4?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_5s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_5?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_6s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_6?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_7s?.setVisibility(View.VISIBLE)
                                activity?.user_enter_7?.setVisibility(View.INVISIBLE)
                                activity?.user_enter_1s?.setText("")
                                activity?.user_enter_2s?.setText("")
                                activity?.user_enter_4s?.setText("")
                                activity?.user_enter_5s?.setText("")
                                activity?.user_enter_6s?.setText("")
                                activity?.user_enter_7s?.setText("")
                                activity?.user_enter_8s?.setText("")
                            }

                        }
                    }

                    override fun afterTextChanged(s: Editable) {
                    }
                })

            }
            activity?.user_enter_2s?.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    activity?.user_enter_1!!.setText("")
                    activity?.user_enter_2!!.setText("")
                    activity?.user_enter_3!!.setText("")
                    activity?.user_enter_4!!.setText("")
                    activity?.user_enter_5!!.setText("")
                    activity?.user_enter_6!!.setText("")
                    activity?.user_enter_7!!.setText("")
                    activity?.user_enter_8!!.setText("")
                    activity?.user_enter_1?.setVisibility(View.VISIBLE)
                    activity?.user_enter_1s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_2?.setVisibility(View.VISIBLE)
                    activity?.user_enter_2s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_3?.setVisibility(View.VISIBLE)
                    activity?.user_enter_3s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_4?.setVisibility(View.VISIBLE)
                    activity?.user_enter_4s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_5?.setVisibility(View.VISIBLE)
                    activity?.user_enter_5s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_6?.setVisibility(View.VISIBLE)
                    activity?.user_enter_6s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_7?.setVisibility(View.VISIBLE)
                    activity?.user_enter_7s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_8?.setVisibility(View.VISIBLE)
                    activity?.user_enter_8s?.setVisibility(View.INVISIBLE)
                }
            }

            activity?.user_enter_1s?.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    activity?.user_enter_1!!.setText("")
                    activity?.user_enter_2!!.setText("")
                    activity?.user_enter_3!!.setText("")
                    activity?.user_enter_4!!.setText("")
                    activity?.user_enter_5!!.setText("")
                    activity?.user_enter_6!!.setText("")
                    activity?.user_enter_7!!.setText("")
                    activity?.user_enter_8!!.setText("")
                    activity?.user_enter_1?.setVisibility(View.VISIBLE)
                    activity?.user_enter_1s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_2?.setVisibility(View.VISIBLE)
                    activity?.user_enter_2s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_3?.setVisibility(View.VISIBLE)
                    activity?.user_enter_3s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_4?.setVisibility(View.VISIBLE)
                    activity?.user_enter_4s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_5?.setVisibility(View.VISIBLE)
                    activity?.user_enter_5s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_6?.setVisibility(View.VISIBLE)
                    activity?.user_enter_6s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_7?.setVisibility(View.VISIBLE)
                    activity?.user_enter_7s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_8?.setVisibility(View.VISIBLE)
                    activity?.user_enter_8s?.setVisibility(View.INVISIBLE)
                }
            }

            activity?.user_enter_3s?.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    activity?.user_enter_1!!.setText("")
                    activity?.user_enter_2!!.setText("")
                    activity?.user_enter_3!!.setText("")
                    activity?.user_enter_4!!.setText("")
                    activity?.user_enter_5!!.setText("")
                    activity?.user_enter_6!!.setText("")
                    activity?.user_enter_7!!.setText("")
                    activity?.user_enter_8!!.setText("")
                    activity?.user_enter_1?.setVisibility(View.VISIBLE)
                    activity?.user_enter_1s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_2?.setVisibility(View.VISIBLE)
                    activity?.user_enter_2s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_3?.setVisibility(View.VISIBLE)
                    activity?.user_enter_3s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_4?.setVisibility(View.VISIBLE)
                    activity?.user_enter_4s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_5?.setVisibility(View.VISIBLE)
                    activity?.user_enter_5s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_6?.setVisibility(View.VISIBLE)
                    activity?.user_enter_6s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_7?.setVisibility(View.VISIBLE)
                    activity?.user_enter_7s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_8?.setVisibility(View.VISIBLE)
                    activity?.user_enter_8s?.setVisibility(View.INVISIBLE)
                }
            }

            activity?.user_enter_4s?.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    activity?.user_enter_1!!.setText("")
                    activity?.user_enter_2!!.setText("")
                    activity?.user_enter_3!!.setText("")
                    activity?.user_enter_4!!.setText("")
                    activity?.user_enter_5!!.setText("")
                    activity?.user_enter_6!!.setText("")
                    activity?.user_enter_7!!.setText("")
                    activity?.user_enter_8!!.setText("")
                    activity?.user_enter_1?.setVisibility(View.VISIBLE)
                    activity?.user_enter_1s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_2?.setVisibility(View.VISIBLE)
                    activity?.user_enter_2s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_3?.setVisibility(View.VISIBLE)
                    activity?.user_enter_3s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_4?.setVisibility(View.VISIBLE)
                    activity?.user_enter_4s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_5?.setVisibility(View.VISIBLE)
                    activity?.user_enter_5s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_6?.setVisibility(View.VISIBLE)
                    activity?.user_enter_6s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_7?.setVisibility(View.VISIBLE)
                    activity?.user_enter_7s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_8?.setVisibility(View.VISIBLE)
                    activity?.user_enter_8s?.setVisibility(View.INVISIBLE)
                }
            }

            activity?.user_enter_5s?.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    activity?.user_enter_1!!.setText("")
                    activity?.user_enter_2!!.setText("")
                    activity?.user_enter_3!!.setText("")
                    activity?.user_enter_4!!.setText("")
                    activity?.user_enter_5!!.setText("")
                    activity?.user_enter_6!!.setText("")
                    activity?.user_enter_7!!.setText("")
                    activity?.user_enter_8!!.setText("")
                    activity?.user_enter_1?.setVisibility(View.VISIBLE)
                    activity?.user_enter_1s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_2?.setVisibility(View.VISIBLE)
                    activity?.user_enter_2s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_3?.setVisibility(View.VISIBLE)
                    activity?.user_enter_3s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_4?.setVisibility(View.VISIBLE)
                    activity?.user_enter_4s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_5?.setVisibility(View.VISIBLE)
                    activity?.user_enter_5s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_6?.setVisibility(View.VISIBLE)
                    activity?.user_enter_6s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_7?.setVisibility(View.VISIBLE)
                    activity?.user_enter_7s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_8?.setVisibility(View.VISIBLE)
                    activity?.user_enter_8s?.setVisibility(View.INVISIBLE)
                }
            }

            activity?.user_enter_6s?.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    activity?.user_enter_1!!.setText("")
                    activity?.user_enter_2!!.setText("")
                    activity?.user_enter_3!!.setText("")
                    activity?.user_enter_4!!.setText("")
                    activity?.user_enter_5!!.setText("")
                    activity?.user_enter_6!!.setText("")
                    activity?.user_enter_7!!.setText("")
                    activity?.user_enter_8!!.setText("")
                    activity?.user_enter_1?.setVisibility(View.VISIBLE)
                    activity?.user_enter_1s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_2?.setVisibility(View.VISIBLE)
                    activity?.user_enter_2s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_3?.setVisibility(View.VISIBLE)
                    activity?.user_enter_3s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_4?.setVisibility(View.VISIBLE)
                    activity?.user_enter_4s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_5?.setVisibility(View.VISIBLE)
                    activity?.user_enter_5s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_6?.setVisibility(View.VISIBLE)
                    activity?.user_enter_6s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_7?.setVisibility(View.VISIBLE)
                    activity?.user_enter_7s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_8?.setVisibility(View.VISIBLE)
                    activity?.user_enter_8s?.setVisibility(View.INVISIBLE)
                }
            }

            activity?.user_enter_7s?.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    activity?.user_enter_1!!.setText("")
                    activity?.user_enter_2!!.setText("")
                    activity?.user_enter_3!!.setText("")
                    activity?.user_enter_4!!.setText("")
                    activity?.user_enter_5!!.setText("")
                    activity?.user_enter_6!!.setText("")
                    activity?.user_enter_7!!.setText("")
                    activity?.user_enter_8!!.setText("")
                    activity?.user_enter_1?.setVisibility(View.VISIBLE)
                    activity?.user_enter_1s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_2?.setVisibility(View.VISIBLE)
                    activity?.user_enter_2s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_3?.setVisibility(View.VISIBLE)
                    activity?.user_enter_3s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_4?.setVisibility(View.VISIBLE)
                    activity?.user_enter_4s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_5?.setVisibility(View.VISIBLE)
                    activity?.user_enter_5s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_6?.setVisibility(View.VISIBLE)
                    activity?.user_enter_6s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_7?.setVisibility(View.VISIBLE)
                    activity?.user_enter_7s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_8?.setVisibility(View.VISIBLE)
                    activity?.user_enter_8s?.setVisibility(View.INVISIBLE)
                }
            }

            activity?.user_enter_8s?.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    activity?.user_enter_1!!.setText("")
                    activity?.user_enter_2!!.setText("")
                    activity?.user_enter_3!!.setText("")
                    activity?.user_enter_4!!.setText("")
                    activity?.user_enter_5!!.setText("")
                    activity?.user_enter_6!!.setText("")
                    activity?.user_enter_7!!.setText("")
                    activity?.user_enter_8!!.setText("")
                    activity?.user_enter_1?.setVisibility(View.VISIBLE)
                    activity?.user_enter_1s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_2?.setVisibility(View.VISIBLE)
                    activity?.user_enter_2s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_3?.setVisibility(View.VISIBLE)
                    activity?.user_enter_3s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_4?.setVisibility(View.VISIBLE)
                    activity?.user_enter_4s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_5?.setVisibility(View.VISIBLE)
                    activity?.user_enter_5s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_6?.setVisibility(View.VISIBLE)
                    activity?.user_enter_6s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_7?.setVisibility(View.VISIBLE)
                    activity?.user_enter_7s?.setVisibility(View.INVISIBLE)
                    activity?.user_enter_8?.setVisibility(View.VISIBLE)
                    activity?.user_enter_8s?.setVisibility(View.INVISIBLE)
                }
            }
        }
    }

}





