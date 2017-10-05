package tere.kotlin.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import io.reactivex.Observable
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : AppCompatActivity() {

    val url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList: RecyclerView = find(R.id.forecast_list)
        forecastList.adapter = ForecastListAdapter(forecastItems())

        Observable.fromFuture(async {
            Request(url).run()
        }).subscribe { toast("response received") }

        Forecast(Date(), 30F, "details").copy(temperature = 32F)
    }


}
