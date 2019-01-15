package startup.softflix.com.startup

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


//event sensorevent listener
class MainActivity : AppCompatActivity(), SensorEventListener {

    var sensor:Sensor?=null
    var sensorManager:SensorManager?=null

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    var isRunning=false
    override fun onSensorChanged(event: SensorEvent?) {
        //means light value in the room
        if(event!!.values[0]>40 && isRunning==false)
        {
            //playmusic
            isRunning=true
            try {
                var mp=MediaPlayer()
                mp.setDataSource("http://server6.mp3quran.net/thubti/001.mp3")
                mp.prepare()
                mp.start()

            }catch (ex:Exception){}


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor=sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)


    }

    override fun onResume() {
        super.onResume()
        //listening to the sensor
        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        //stop listengin
        sensorManager!!.unregisterListener(this)
    }
}
